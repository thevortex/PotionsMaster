package com.thevortex.potionsmaster.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public interface CommonProxy {

    void init();

    World getClientWorld();

    ServerWorld getWorld();

    PlayerEntity getClientPlayer();

    Minecraft getMinecraft();
}
