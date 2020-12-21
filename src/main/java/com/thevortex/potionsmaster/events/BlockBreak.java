package com.thevortex.potionsmaster.events;


import com.thevortex.potionsmaster.blocks.Cauldron;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockBreak {


    @SubscribeEvent
    public static void on(BreakEvent event) {
        if (event.getState().getBlock() == Blocks.CAMPFIRE) {
            BlockPos pos = event.getPos();
            if (event.getWorld().getBlockState(pos.up()).getBlock() instanceof Cauldron) {
                event.getWorld().destroyBlock(pos.up(), true);
            }
        }
    }


}
