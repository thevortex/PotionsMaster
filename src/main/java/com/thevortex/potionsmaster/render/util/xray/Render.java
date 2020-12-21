package com.thevortex.potionsmaster.render.util.xray;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thevortex.potionsmaster.render.util.BlockInfo;
import com.thevortex.potionsmaster.render.util.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Vector3d;
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
    private static final int GL_LINES = 1;
    public static List<BlockInfo> ores = Collections.synchronizedList(new ArrayList<>()); // this is accessed by threads

    @OnlyIn(Dist.CLIENT)
    public static void drawOres(RenderWorldLastEvent event) {

        Vector3d view = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();

        MatrixStack stack = event.getMatrixStack();
        stack.push();
        stack.translate(-view.x, -view.y, -view.z); // translate
        RenderSystem.pushMatrix();
        RenderSystem.multMatrix(stack.getLast().getMatrix());

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Profile.BLOCKS.apply(); // Sets GL state for block drawing
        ores.forEach(b -> {
            if (b == null) {
                return;
            }
            RenderSystem.pushMatrix();
            buffer.begin(GL_LINES, DefaultVertexFormats.POSITION_COLOR);
            Util.renderBlock(buffer, b, (int) b.alpha);

            tessellator.draw();
            RenderSystem.popMatrix();
        });
        Profile.BLOCKS.clean();
        stack.pop();
        RenderSystem.popMatrix();


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
                RenderSystem.polygonMode(GL_FRONT_AND_BACK, GL_LINE);
                RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                RenderSystem.enableBlend();
                RenderSystem.lineWidth((float) 3.0);
            }

            @Override
            public void clean() {

                RenderSystem.polygonMode(GL_FRONT_AND_BACK, GL_FILL);
                RenderSystem.disableBlend();
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
