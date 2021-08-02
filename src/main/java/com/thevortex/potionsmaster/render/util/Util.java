package com.thevortex.potionsmaster.render.util;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;

public class Util {


    public static void renderBlock(PoseStack stack, BufferBuilder buffer, BlockInfo blockinfo, int opacity) {
        if (blockinfo == null)
            return;
        final float size = 1.0f;
        float red = blockinfo.color[0] * 255;
        float green = blockinfo.color[1] * 255;
        float blue = blockinfo.color[2] * 255;

        int x = (int)blockinfo.getX();
        int y = (int)blockinfo.getY();
        int z = (int)blockinfo.getZ();
        Matrix4f matrix4f = stack.last().pose();

        // top
        buffer.vertex(matrix4f,x, y + size, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y + size, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y + size, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y + size, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y + size, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y + size, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y + size, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y + size, z).color(red, green, blue, opacity).endVertex();

        // bottom
        buffer.vertex(matrix4f,x + size, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y, z).color(red, green, blue, opacity).endVertex();

        // side 1
        buffer.vertex(matrix4f,x + size, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y + size, z + size).color(red, green, blue, opacity).endVertex();

        // side 2
        buffer.vertex(matrix4f,x + size, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x + size, y + size, z).color(red, green, blue, opacity).endVertex();

        // side 3
        buffer.vertex(matrix4f,x, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y + size, z + size).color(red, green, blue, opacity).endVertex();

        // side 4
        buffer.vertex(matrix4f,x, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(matrix4f,x, y + size, z).color(red, green, blue, opacity).endVertex();
    }


}
