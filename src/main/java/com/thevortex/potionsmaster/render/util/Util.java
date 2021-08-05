package com.thevortex.potionsmaster.render.util;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.world.phys.Vec3;

public class Util {


    public static void renderBlock(PoseStack stack, VertexConsumer buffer, BlockInfo blockinfo) {
        if (blockinfo == null)
            return;
        final float size = 1.0f;
        float red = (blockinfo.color[0] >> 16 & 0xff) / 255f;
        float green = (blockinfo.color[1] >> 8 & 0xff) / 255f;
        float blue = (blockinfo.color[2] & 0xff) / 255f;
        float opacity = (float)blockinfo.alpha;
        float x = blockinfo.getX();
        float y = blockinfo.getY();
        float z = blockinfo.getZ();

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
        buffer.vertex(x + size, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x + size, y, z).color(red, green, blue, opacity).endVertex();

        // side 1
        buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x + size, y + size, z + size).color(red, green, blue, opacity).endVertex();

        // side 2
        buffer.vertex(x + size, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x + size, y + size, z).color(red, green, blue, opacity).endVertex();

        // side 3
        buffer.vertex(x, y, z + size).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x, y + size, z + size).color(red, green, blue, opacity).endVertex();

        // side 4
        buffer.vertex(x, y, z).color(red, green, blue, opacity).endVertex();
        buffer.vertex(x, y + size, z).color(red, green, blue, opacity).endVertex();
    }


}
