package com.thevortex.potionsmaster.render.util;

import com.mojang.math.Vector3d;

/**
 * A bounding box representing a world 3D area in both world and chunk coords.
 */
public class WorldRegion {

    public int minX, minY, minZ, maxX, maxY, maxZ;
    public int minChunkX, minChunkY, minChunkZ, maxChunkX, maxChunkY, maxChunkZ;

    /**
     * Constructs a world region from a player location and a radius.
     * Vertical extend is 92 blocks down and 32 blocks up
     *
     * @param pos    a world position
     * @param radius a block radius
     */
    public WorldRegion(Vector3d pos, int radius,int minBuild, int maxBuild) {
        minX = (int)pos.x - radius;
        maxX = (int)pos.x + radius;
        minY = (int)Math.max(minBuild,pos.y - 16);
        maxY = (int)Math.min(maxBuild,pos.y + 16);
        minZ = (int)pos.z - radius;
        maxZ = (int)pos.z + radius;
        minChunkX = minX >> 4;
        maxChunkX = maxX >> 4;
        minChunkY = minY >> 4;
        maxChunkY = maxY >> 4;
        minChunkZ = minZ >> 4;
        maxChunkZ = maxZ >> 4;
    }
}
