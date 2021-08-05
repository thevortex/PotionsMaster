package com.thevortex.potionsmaster.render.util.xray;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.renderer.*;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;


public class Render {
    public static class OreList {
        protected List<BlockInfo> oreList = Collections.synchronizedList(new ArrayList<>());

        public void add(BlockInfo info) {
            oreList.add(info);
            INSTANCE.dataAvailable.set(true);
        }

        public void remove(BlockInfo info) {
            oreList.remove(info);
            INSTANCE.dataAvailable.set(true);
        }

        public boolean isEmpty() {
            return oreList.isEmpty();
        }

        public void clear() {
            oreList.clear();
            INSTANCE.dataAvailable.set(true);
        }

        public void addAll(Collection<? extends BlockInfo> list) {
            oreList.addAll(list);
            INSTANCE.dataAvailable.set(true);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static final Render INSTANCE = new Render();

    private static RenderType XRAY_TYPE = null;
    private VertexBuffer vertexBuf = null;
    private final AtomicBoolean dataAvailable = new AtomicBoolean(false);
    public static OreList ores = new OreList();

    public static RenderType buildRenderType() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeLinesShader))
                .setDepthTestState(new RenderStateShard.DepthTestStateShard("always",GL11.GL_ALWAYS))
                .setCullState(new RenderStateShard.CullStateShard(false))
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

        return (RenderType) fn.invoke(null,"xray",DefaultVertexFormat.POSITION_COLOR_NORMAL,VertexFormat.Mode.LINES,256,compositeState);

    }

    @OnlyIn(Dist.CLIENT)
    public synchronized void rebuildBuffer() {
        var stack = new PoseStack();
        var builder = new BufferBuilder(XRAY_TYPE.bufferSize() * ores.oreList.size());
        builder.begin(XRAY_TYPE.mode(), XRAY_TYPE.format());
        for (var b : ores.oreList) {
            if (b != null) {
                renderShape(stack,builder,Shapes.block(),b.getX(),b.getY(),b.getZ(),
                        b.color[0]/255.0F,b.color[1]/255.0F,b.color[2]/255.0F,1.0F);
            }
        }
        builder.end();
        var vbuf = new VertexBuffer();
        vbuf.upload(builder);
        if (vertexBuf != null) vertexBuf.close();
        vertexBuf = vbuf;
    }

    @OnlyIn(Dist.CLIENT)
    public void drawOres(RenderWorldLastEvent event) {
        if (XRAY_TYPE == null) {
            try {
                XRAY_TYPE = buildRenderType();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (dataAvailable.get()) {
            rebuildBuffer();
            dataAvailable.set(false);
        }
        if (vertexBuf == null) return;

        Vec3 view = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        PoseStack stack = event.getMatrixStack();

        stack.pushPose();
        stack.translate(-view.x,-view.y,-view.z);
        Profile.BLOCKS.apply(); // Sets GL state for block drawing

        RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
        RenderSystem.disableCull();
        RenderSystem.disableTexture();
        vertexBuf.drawWithShader(stack.last().pose(), event.getProjectionMatrix(), GameRenderer.getRendertypeLinesShader());
        RenderSystem.enableCull();
        RenderSystem.enableTexture();

        Profile.BLOCKS.clean();
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
