package com.thevortex.potionsmaster.render.util.xray;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.thevortex.potionsmaster.render.util.BlockInfo;
import com.thevortex.potionsmaster.render.util.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
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
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.pipeline.IVertexProducer;
import net.minecraftforge.client.model.pipeline.VertexBufferConsumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Render {
    private static final int GL_FRONT_AND_BACK = 1032;
    private static final int GL_LINE = 6913;
    private static final int GL_FILL = 6914;
    private static final int GL_LINES = 1;
    public static List<BlockInfo> ores = Collections.synchronizedList(new ArrayList<>()); // this is accessed by threads

    @OnlyIn(Dist.CLIENT)
    public static void drawOres(RenderWorldLastEvent event) {

        Vec3 view = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();

        PoseStack stack = event.getMatrixStack();
        stack.pushPose();
        RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
        Profile.BLOCKS.apply(); // Sets GL state for block drawing

        ores.forEach(b -> {
            if (b == null) {
                return;
            }
            VertexConsumer vcon = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.LINES);
            stack.pushPose();
            //Util.renderBlock(stack,vcon,b);
            LevelRenderer.renderShape(stack,vcon,Shapes.block(),b.getX()-view.x,b.getY()-view.y,b.getZ()-view.z,b.color[0]/255.0F,b.color[1]/255.0F,b.color[2]/255.0F,1.0F);
            stack.popPose();

        });
        Profile.BLOCKS.clean();
        stack.popPose();
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
                RenderSystem.disableBlend();
                RenderSystem.disableDepthTest();
                //RenderSystem.polygonMode(GL_FRONT_AND_BACK, GL_LINE);
                RenderSystem.lineWidth((float) 3.0);


            }

            @Override
            public void clean() {

                //RenderSystem.polygonMode(GL_FRONT_AND_BACK, GL_FILL);
                RenderSystem.enableDepthTest();
                RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                RenderSystem.enableBlend();
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
