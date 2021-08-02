package com.thevortex.potionsmaster.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;




public class ClientProxy implements CommonProxy {

    @Override
    public Level getClientWorld() {

        return Minecraft.getInstance().level;
    }

    @Override
    public LocalPlayer getClientPlayer() {
        return Minecraft.getInstance().player;
    }

    @Override
    public Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }

    @Override
    public void init() {


    }

    @Override
    public ServerLevel getWorld() {
        throw new IllegalStateException("Only run this on the server!");

    }

}
