package com.thevortex.potionsmaster.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;



public class ServerProxy implements CommonProxy {

    @Override
    public Level getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public LocalPlayer getClientPlayer() {
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
    public ServerLevel getWorld() {
        return getWorld();
    }

}
