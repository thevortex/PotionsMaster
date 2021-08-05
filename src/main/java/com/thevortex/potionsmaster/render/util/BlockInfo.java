package com.thevortex.potionsmaster.render.util;


import net.minecraft.core.Vec3i;

public class BlockInfo extends Vec3i {
    public int[] color;
    public double alpha;

    public BlockInfo(int x, int y, int z, int[] color, double alpha) {
        super(x, y, z);
        this.color = color;
        this.alpha = 1;
    }

    public BlockInfo(Vec3i pos, int[] color, double alpha) {
        this((int)pos.getX(), (int)pos.getY(), (int)pos.getZ(), color, alpha);
    }

}
