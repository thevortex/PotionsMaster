package com.thevortex.potionsmaster.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;



public interface CommonProxy {

    void init();

    Level getClientWorld();

    ServerLevel getWorld();

    LocalPlayer getClientPlayer();

    Minecraft getMinecraft();
}
