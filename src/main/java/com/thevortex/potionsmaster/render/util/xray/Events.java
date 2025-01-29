package com.thevortex.potionsmaster.render.util.xray;


import javax.swing.text.html.parser.Entity;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;

import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

@EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
public class Events {


	protected static int counter;

	@SubscribeEvent
	public static void onExit(ServerStoppingEvent event) {
		if ((Controller.drawOres())) {
			Controller.toggleDrawOres();
		}
		Controller.shutdownExecutor();
	}

	
	@SubscribeEvent
	public static void pickupItem(BlockEvent.BreakEvent event) {
		RenderEnqueue.checkBlock(event.getPos(), event.getState(), false);
	}

	@SubscribeEvent
	public static void placeItem(BlockEvent.EntityPlaceEvent event) {
		RenderEnqueue.checkBlock(event.getPos(), event.getState(), true);
	}

	@SubscribeEvent
	public static void chunkLoad(ChunkEvent.Load event) {
		Controller.requestBlockFinder(true);
	}


	@SubscribeEvent
	public static void tickEnd(ClientTickEvent.Post event) {		
		counter++;
		if ((counter > 40) && (Controller.drawOres())) {
			counter = 0;
			Controller.requestBlockFinder(false);
		}
		
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onWorldRenderLast(RenderLevelStageEvent event) // Called when drawing the world.
	{
		
		if ((Controller.drawOres()) && (PotionsMaster.proxy.getMinecraft().player != null) && (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
			if(PotionsMaster.proxy.getMinecraft().player.getActiveEffects().stream().iterator().hasNext()) {
				boolean pmPot = false;
				if(PotionsMaster.proxy.getMinecraft().player.getActiveEffects().stream().iterator().next().getEffect().getRegisteredName().contains("potionsmaster")) {
					pmPot = true;
				}
				if(!pmPot) {
					Controller.toggleDrawOres();
					return;
				}
				// this is a world pos of the player
				try {
					Render.INSTANCE.drawOres(event);
				} catch (Throwable ignore) {
				}
			}
		}
	}
}
