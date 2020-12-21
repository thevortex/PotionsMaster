package com.thevortex.potionsmaster.render.util;

import net.minecraft.util.math.vector.Vector3i;

public class BlockInfo extends Vector3i {
    public int[] color;
    public double alpha;

    public BlockInfo(int x, int y, int z, int[] color, double alpha) {
        super(x, y, z);
        this.color = color;
        this.alpha = alpha;
    }

    public BlockInfo(Vector3i pos, int[] color, double alpha) {
        this(pos.getX(), pos.getY(), pos.getZ(), color, alpha);
    }

}
