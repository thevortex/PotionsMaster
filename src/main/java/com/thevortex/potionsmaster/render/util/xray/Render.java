package com.thevortex.potionsmaster.render.util.xray;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Vector3d;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockInfo;
import com.thevortex.potionsmaster.render.util.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Render {
    private static final int GL_FRONT_AND_BACK = 1032;
    private static final int GL_LINE = 6913;
    private static final int GL_FILL = 6914;
    //private static final int GL_LINES = 1;
    private static final VertexFormat.Mode GL_LINES = VertexFormat.Mode.LINES;
    public static List<BlockInfo> ores = Collections.synchronizedList(new ArrayList<>()); // this is accessed by threads

    @OnlyIn(Dist.CLIENT)
    public static void drawOres(RenderWorldLastEvent event) {

        Vec3 view = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        PoseStack stack = event.getMatrixStack();
        PoseStack.Pose pose = stack.last();


            Tesselator tessellator = Tesselator.getInstance();
            Profile.BLOCKS.apply(); // Sets GL state for block drawing
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            ores.forEach(b -> {
                if (b == null) {
                    return;
                }
                VertexConsumer buffer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.LINES);
                stack.pushPose();
                stack.translate(-b.getX(),-b.getY(),-b.getZ());
                Util.renderBlock(stack, buffer, b, (int) b.alpha);
                stack.popPose();
                RenderSystem.lineWidth(1.0F);
                RenderSystem.enableDepthTest();
                RenderSystem.enableBlend();
                RenderSystem.enableTexture();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            });
            //Profile.BLOCKS.clean();

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
                RenderSystem.depthMask(false);
                //RenderSystem.polygonMode(GL_FRONT_AND_BACK, GL_LINE);
                //RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                //RenderSystem.enableBlend();
                RenderSystem.lineWidth((float) 3.0);
            }

            @Override
            public void clean() {

              //  RenderSystem.polygonMode(GL_FRONT_AND_BACK, GL_FILL);
              //  RenderSystem.disableBlend();
                RenderSystem.enableDepthTest();
                RenderSystem.depthMask(true);
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
