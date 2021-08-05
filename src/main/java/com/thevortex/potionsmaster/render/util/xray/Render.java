package com.thevortex.potionsmaster.render.util.xray;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.thevortex.potionsmaster.render.util.BlockInfo;
import com.thevortex.potionsmaster.render.util.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.RenderProperties;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.pipeline.IVertexProducer;
import net.minecraftforge.client.model.pipeline.VertexBufferConsumer;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Render {
    private static final int GL_FRONT_AND_BACK = 1032;
    private static final int GL_LINE = 6913;
    private static final int GL_FILL = 6914;
    private static final int GL_LINES = 1;
    private static RenderType XRAY_TYPE = null;
    public static List<BlockInfo> ores = Collections.synchronizedList(new ArrayList<>()); // this is accessed by threads

    public static RenderType buildRenderType() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getPositionColorShader))
                .setDepthTestState(new RenderStateShard.DepthTestStateShard("always",GL11.GL_ALWAYS))
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("xray",() -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                },() ->{
                    RenderSystem.disableBlend();
                })).createCompositeState(true);
                var fn = RenderType.class.getDeclaredMethod("create",
                        String.class, VertexFormat.class, VertexFormat.Mode.class,
                        Integer.TYPE, RenderType.CompositeState.class);
                fn.setAccessible(true);

        return (RenderType) fn.invoke(null,"xray",DefaultVertexFormat.POSITION_COLOR,VertexFormat.Mode.LINES,256,compositeState);

    }
    @OnlyIn(Dist.CLIENT)
    public static void drawOres(RenderWorldLastEvent event) {

        Vec3 view = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        PoseStack stack = event.getMatrixStack();

        if (XRAY_TYPE == null) {
            try {
                XRAY_TYPE = buildRenderType();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        stack.pushPose();
        stack.translate(-view.x,-view.y,-view.z);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        var bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        Profile.BLOCKS.apply(); // Sets GL state for block drawing

        for(var b : ores) {

            var vcon = bufferSource.getBuffer(XRAY_TYPE);
            if (b == null) {
                return;
            }
            stack.pushPose();
            //LevelRenderer.renderShape(stack,vcon,Shapes.block(),b.getX()-view.x,b.getY()-view.y,b.getZ()-view.z,b.color[0]/255.0F,b.color[1]/255.0F,b.color[2]/255.0F,1.0F);
            //renderShape(stack,vcon,Shapes.block(),b.getX()-view.x,b.getY()-view.y,b.getZ()-view.z,b.color[0]/255.0F,b.color[1]/255.0F,b.color[2]/255.0F,1.0F);
            Util.renderBlock(stack,vcon,b);
            stack.popPose();

        }
        Profile.BLOCKS.clean();
        bufferSource.endBatch();
        stack.popPose();
    }
    public static void renderShape(PoseStack pose, VertexConsumer vcon, VoxelShape shape, double x, double y, double z, float r, float g, float b, float a) {
        PoseStack.Pose posestack$pose = pose.last();
        shape.forAllEdges((x1, y1, z1, x2, y2, z2) -> {
            float f = (float)(x2 - x1);
            float f1 = (float)(y2 - y1);
            float f2 = (float)(z2 - z1);
            float f3 = Mth.sqrt(f * f + f1 * f1 + f2 * f2);
            f = f / f3;
            f1 = f1 / f3;
            f2 = f2 / f3;
            vcon.vertex(posestack$pose.pose(), (float)(x1 + x), (float)(y1 + y), (float)(z1 + z)).color(r, g, b, a).normal(posestack$pose.normal(), f, f1, f2).endVertex();
            vcon.vertex(posestack$pose.pose(), (float)(x2 + x), (float)(y2 + y), (float)(z2 + z)).color(r, g, b, a).normal(posestack$pose.normal(), f, f1, f2).endVertex();

        });
    }


    /**
     * OpenGL Profiles used for rendering blocks and entities
     */
    @OnlyIn(Dist.CLIENT)
    private enum Profile {
        BLOCKS {
            @Override
            public void apply() {
                RenderSystem.disableTexture();
                RenderSystem.disableDepthTest();
                RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                RenderSystem.enableBlend();
                //RenderSystem.polygonMode(GL_FRONT_AND_BACK, GL_LINE);
                RenderSystem.lineWidth(3.0f);


            }

            @Override
            public void clean() {

                //RenderSystem.polygonMode(GL_FRONT_AND_BACK, GL_FILL);
                RenderSystem.enableTexture();

            }
        },
        ENTITIES {
            @Override
            public void apply() {

            }

            @Override
            public void clean() {

            }
        };

        Profile() {
        }

        public abstract void apply();

        public abstract void clean();
    }

}
