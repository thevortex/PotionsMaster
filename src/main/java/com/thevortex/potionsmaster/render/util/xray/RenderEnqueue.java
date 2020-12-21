package com.thevortex.potionsmaster.render.util.xray;


import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.thevortex.potionsmaster.reference.Ores;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockData;
import com.thevortex.potionsmaster.render.util.BlockInfo;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.WorldRegion;

public class RenderEnqueue implements Runnable {
	private final WorldRegion box;

	public RenderEnqueue(WorldRegion region) {
		box = region;
	}

	/**
	 * Single-block version of blockFinder. Can safely be called directly
	 * for quick block check.
	 *
	 * @param pos   the BlockPos to check
	 * @param state the current state of the block
	 * @param add   true if the block was added to world, false if it was removed
	 */
	public static void checkBlock(BlockPos pos, BlockState state, boolean add) {
		if (!Controller.drawOres() || Controller.getBlockStore().getStore().isEmpty())
			return; // just pass

		String defaultState = state.getBlock().getDefaultState().toString();


		// Let's see if the block to check is an ore we monitor
		if (Controller.getBlockStore().getStore().containsKey(defaultState)) // it's a block we are monitoring
		{
			if (!add) {
				Render.ores.remove(new BlockInfo(pos, null, 0.0));
				return;
			}

			BlockData data = null;
			if (Controller.getBlockStore().getStore().containsKey(defaultState))
				data = Controller.getBlockStore().getStore().get(defaultState);

			if (data == null)
				return;

			double alpha = Math.max(0, ((Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().getDistanceSq(pos.getX(), pos.getY(), pos.getZ())) / Controller.getRadius()) * 255);

			// the block was added to the world, let's add it to the drawing buffer
			Render.ores.add(new BlockInfo(pos, data.getColor().getColor(), alpha));
		}
	}

	@Override
	public void run() // Our thread code for finding ores near the player.
	{
		blockFinder();
	}

	/**
	 * Use Controller.requestBlockFinder() to trigger a scan.
	 */
	private void blockFinder() {
		HashMap<UUID, BlockData> blocks = Controller.getBlockStore().getStore();

		if (blocks.isEmpty()) {
			if (!Render.ores.isEmpty())
				Render.ores.clear();

		}

		final World world = PotionsMaster.proxy.getClientPlayer().world;

		final PlayerEntity player = PotionsMaster.proxy.getClientPlayer();

		final List<BlockInfo> renderQueue = new ArrayList<>();

		int lowBoundX, highBoundX, lowBoundY, highBoundY, lowBoundZ, highBoundZ;

		// Used for cleaning up the searching process
		BlockState currentState;
		ResourceLocation block;
		BlockStore.BlockDataWithUUID dataWithUUID;
		// Loop on chunks (x, z)
		for (int chunkX = box.minChunkX; chunkX <= box.maxChunkX; chunkX++) {
			// Pre-compute the extend bounds on X
			int x = chunkX << 4; // lowest x coord of the chunk in block/world coordinates
			lowBoundX = (x < box.minX) ? box.minX - x : 0; // lower bound for x within the extend
			highBoundX = (x + 15 > box.maxX) ? box.maxX - x : 15;// and higher bound. Basically, we clamp it to fit the radius.

			for (int chunkZ = box.minChunkZ; chunkZ <= box.maxChunkZ; chunkZ++) {
				// Time to getStore the chunk (16x256x16) and split it into 16 vertical extends (16x16x16)
				if (!world.chunkExists(chunkX, chunkZ)) {
					continue; // We won't find anything interesting in unloaded chunks
				}

				Chunk chunk = world.getChunk(chunkX, chunkZ);
				ChunkSection[] extendsList = chunk.getSections();

				// Pre-compute the extend bounds on Z
				int z = chunkZ << 4;
				lowBoundZ = (z < box.minZ) ? box.minZ - z : 0;
				highBoundZ = (z + 15 > box.maxZ) ? box.maxZ - z : 15;

				// Loop on the extends around the player's layer (6 down, 2 up)
				for (int curExtend = box.minChunkY; curExtend <= box.maxChunkY; curExtend++) {
					ChunkSection ebs = extendsList[curExtend];
					if (ebs == null) // happens quite often!
						continue;

					// Pre-compute the extend bounds on Y
					int y = curExtend << 4;
					lowBoundY = (y < box.minY) ? box.minY - y : 0;
					highBoundY = (y + 15 > box.maxY) ? box.maxY - y : 15;

					// Now that we have an extend, let's check all its blocks
					for (int i = lowBoundX; i <= highBoundX; i++) {
						for (int j = lowBoundY; j <= highBoundY; j++) {
							for (int k = lowBoundZ; k <= highBoundZ; k++) {
								currentState = ebs.getBlockState(i, j, k);

								// Reject blacklisted blocks
								//if( Controller.blackList.contains(currentState.getBlock()) )
								//	continue;
								block = currentState.getBlock().getRegistryName();
								if (block == null)
									continue;

								if (currentState.getBlock().getTags().contains(Ores.DIAMOND)) {
									block = Ores.DIAMOND;
								}
								if (currentState.getBlock().getTags().contains(Ores.LAPIS)) {
									block = Ores.LAPIS;
								}
								if (currentState.getBlock().getTags().contains(Ores.ALUMINIUM)) {
									block = Ores.ALUMINIUM;
								}
								if (currentState.getBlock().getTags().contains(Ores.COPPER)) {
									block = Ores.COPPER;
								}
								if (currentState.getBlock().getTags().contains(Ores.TIN)) {
									block = Ores.TIN;
								}
								if (currentState.getBlock().getTags().contains(Ores.LEAD)) {
									block = Ores.LEAD;
								}
								if (currentState.getBlock().getTags().contains(Ores.SILVER)) {
									block = Ores.SILVER;
								}
								if (currentState.getBlock().getTags().contains(Ores.GOLD)) {
									block = Ores.GOLD;
								}
								if (currentState.getBlock().getTags().contains(Ores.URANIUM)) {
									block = Ores.URANIUM;
								}
								if (currentState.getBlock().getTags().contains(Ores.NICKEL)) {
									block = Ores.NICKEL;
								}
								if (currentState.getBlock().getTags().contains(Ores.IRON)) {
									block = Ores.IRON;
								}
								if (currentState.getBlock().getTags().contains(Ores.OSMIUM)) {
									block = Ores.OSMIUM;
								}
								if (currentState.getBlock().getTags().contains(Ores.ZINC)) {
									block = Ores.ZINC;
								}
								if (currentState.getBlock().getTags().contains(Ores.EMERALD)) {
									block = Ores.EMERALD;
								}
								if (currentState.getBlock().getTags().contains(Ores.COAL)) {
									block = Ores.COAL;
								}
								if (currentState.getBlock().getTags().contains(Ores.REDSTONE)) {
									block = Ores.REDSTONE;
								}
								if (currentState.getBlock().getTags().contains(Ores.QUARTZ)) {
									block = Ores.QUARTZ;
								}
								if (currentState.getBlock().getTags().contains(Ores.BISMUTH)) {
									block = Ores.BISMUTH;
								}
								if (currentState.getBlock().getTags().contains(Ores.CRIMSONIRON)) {
									block = Ores.CRIMSONIRON;
								}
								if (currentState.getBlock().getTags().contains(Ores.PLATINUM)) {
									block = Ores.PLATINUM;
								}
								if (currentState.getBlock().getTags().contains(Ores.NETHERITE)) {
									block = Ores.NETHERITE;
								}
								if (currentState.getBlock().getTags().contains(Ores.ALLTHEMODIUM)) {
									block = Ores.ALLTHEMODIUM;
								}
								if (currentState.getBlock().getTags().contains(Ores.VIBRANIUM)) {
									block = Ores.VIBRANIUM;
								}
								if (currentState.getBlock().getTags().contains(Ores.UNOBTAINIUM)) {
									block = Ores.UNOBTAINIUM;
								}

								dataWithUUID = Controller.getBlockStore().getStoreByReference(block.toString());
								if (dataWithUUID == null)
									continue;

								if (dataWithUUID.getBlockData() == null || !dataWithUUID.getBlockData().isDrawing()) // fail safe
									continue;

								// Calculate distance from player to block. Fade out further away blocks
								//double alpha = Math.max(0, ((Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().getDistanceSq(x + i, y + j, z + k)) / Controller.getRadius() ) * 255);
								double alpha = Math.max(0, Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().getDistanceSq(x + i, y + j, z + k) / (Controller.getRadius() / 4));
								// Push the block to the render queue
								renderQueue.add(new BlockInfo(x + i, y + j, z + k, dataWithUUID.getBlockData().getColor().getColor(), alpha));
							}
						}
					}
				}
			}
		}
		renderQueue.sort((t, t1) -> Double.compare(t1.distanceSq(player.getPosition()), t.distanceSq(player.getPosition())));

		Render.ores.clear();
		Render.ores.addAll(renderQueue); // Add all our found blocks to the Render.ores list. To be use by Render when drawing.
	}
}
