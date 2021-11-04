package com.thevortex.potionsmaster.render.util.xray;


import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmlserverevents.FMLServerStoppingEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
public class Events {

	@SubscribeEvent
	public static void onExit(FMLServerStoppingEvent event) {
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
	public static void tickEnd(TickEvent.ClientTickEvent event) {

		if (event.phase == TickEvent.Phase.END) {

			Controller.requestBlockFinder(false);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onWorldRenderLast(RenderWorldLastEvent event) // Called when drawing the world.
	{

		if ((Controller.drawOres()) && (PotionsMaster.proxy.getMinecraft().player != null)) {
			float f = event.getPartialTicks();

			// this is a world pos of the player
			try {
				Render.INSTANCE.drawOres(event);
			} catch (Throwable ignore) {
			}
		}
	}
}
