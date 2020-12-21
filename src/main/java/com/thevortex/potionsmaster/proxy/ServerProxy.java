package com.thevortex.potionsmaster.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ServerProxy implements CommonProxy {

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public PlayerEntity getClientPlayer() {
        throw new IllegalStateException("Only run this on the client!");

    }

    @Override
    public Minecraft getMinecraft() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public ServerWorld getWorld() {
        return getWorld();
    }

}
