package com.thevortex.potionsmaster.render.util.xray;


import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.WorldRegion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.joml.Vector3d;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {
	// Radius +/- around the player to search. So 8 is 8 on left and right of player plus under the player. So 17x17 area.
	private static final int[] distanceList = new int[]{8, 16, 24, 32, 48, 64, 80, 128, 256};

	public static ArrayList blackList = new ArrayList<Block>() {{
		add(Blocks.AIR);
		add(Blocks.BEDROCK);
		add(Blocks.STONE);
		add(Blocks.GRASS_BLOCK);
		add(Blocks.DIRT);
	}};

	private static Vector3d lastPlayerPos = null;


	// Thread management
	private static Future task;
	private static ExecutorService executor;
	// Draw states
	private static boolean drawOres = false; // Off by default

	public static BlockStore getBlockStore() {
		return PotionsMaster.blockStore;
	}


	public static boolean drawOres() {
		return drawOres && (PotionsMaster.proxy.getClientWorld() != null) && (PotionsMaster.proxy.getClientPlayer() != null);
	}

	public static void toggleDrawOres() {
		if (!drawOres) {
			Render.ores.clear();
			executor = Executors.newSingleThreadExecutor();
			drawOres = true;
			requestBlockFinder(true);

		} else {
			shutdownExecutor();
		}
	}

	public static int getRadius() {
		return distanceList[2];
	}

	public static void incrementCurrentDist() {

	}

	public static void decrementCurrentDist() {

	}


	private static boolean playerHasMoved() {
		if ((PotionsMaster.proxy.getClientWorld() == null) && (Controller.drawOres)) {
			toggleDrawOres();
			return false;
		}

		return lastPlayerPos == null
				|| lastPlayerPos.x != PotionsMaster.proxy.getClientPlayer().getX()
				|| lastPlayerPos.z != PotionsMaster.proxy.getClientPlayer().getZ();
	}

	private static void updatePlayerPosition() {

		lastPlayerPos = new Vector3d(PotionsMaster.proxy.getClientPlayer().position().x,PotionsMaster.proxy.getClientPlayer().position().y,PotionsMaster.proxy.getClientPlayer().position().z);
	}

	public static synchronized void requestBlockFinder(boolean force) {
		if (drawOres() && (task == null || task.isDone()) && (force || playerHasMoved())) // world/player check done by drawOres()
		{
			updatePlayerPosition(); // since we're about to run, update the last known position

			WorldRegion region = new WorldRegion(lastPlayerPos, getRadius(), PotionsMaster.proxy.getClientWorld().getMinBuildHeight(),PotionsMaster.proxy.getClientWorld().getMaxBuildHeight()); // the region to scan for ores
			//PotionsMaster.LOGGER.info("min " + PotionsMaster.proxy.getClientWorld().getMinBuildHeight() + " >> 4 = "+ (PotionsMaster.proxy.getClientWorld().getMinBuildHeight() >> 4) + "max " + PotionsMaster.proxy.getClientWorld().getMaxBuildHeight() + " >> 4 = "+ (PotionsMaster.proxy.getClientWorld().getMaxBuildHeight() >> 4)  );
			task = executor.submit(new RenderEnqueue(region));

		}
	}

	public static void shutdownExecutor() {
		// Important. If drawOres is true when a player logs out then logs back in, the next requestBlockFinder will crash
		drawOres = false;
		try {
			executor.shutdownNow();
		} catch (Throwable ignore) {
		}
	}


}


