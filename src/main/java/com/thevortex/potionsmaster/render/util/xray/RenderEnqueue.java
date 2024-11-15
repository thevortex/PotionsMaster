package com.thevortex.potionsmaster.render.util.xray;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.thevortex.potionsmaster.reference.Ores;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockData;
import com.thevortex.potionsmaster.render.util.BlockInfo;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.WorldRegion;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;

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

		String defaultState = state.getBlock().defaultBlockState().toString();


		// Let's see if the block to check is an ore we monitor
		if (Controller.getBlockStore().getStore().containsKey(defaultState)) // it's a block we are monitoring
		{
			if (!add) {
				Render.ores.remove(new BlockInfo(pos, 0, 0.0));
				return;
			}

			BlockData data = null;
			if (Controller.getBlockStore().getStore().containsKey(defaultState))
				data = Controller.getBlockStore().getStore().get(defaultState);

			if (data == null)
				return;

			double alpha = Math.max(0, ((Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().distanceToSqr(pos.getX(), pos.getY(), pos.getZ())) / Controller.getRadius()) * 255);

			// the block was added to the world, let's add it to the drawing buffer
			Render.ores.add(new BlockInfo(new Vec3i(pos.getX(),pos.getY(),pos.getZ()), data.getColor(), alpha));
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

		final Level world = PotionsMaster.proxy.getClientPlayer().level();

		final LocalPlayer player = PotionsMaster.proxy.getClientPlayer();

		final List<BlockInfo> renderQueue = new ArrayList<>();

		int lowBoundX, highBoundX, lowBoundY, highBoundY, lowBoundZ, highBoundZ;

		// Used for cleaning up the searching process
		BlockState currentState;
		TagKey<Block> block;
		BlockStore.BlockDataWithUUID dataWithUUID;
		// Loop on chunks (x, z)
		for (int chunkX = box.minChunkX; chunkX <= box.maxChunkX; chunkX++) {
			// Pre-compute the extend bounds on X
			int x = chunkX << 4; // lowest x coord of the chunk in block/world coordinates
			lowBoundX = (x < box.minX) ? box.minX - x : 0; // lower bound for x within the extend
			highBoundX = (x + 15 > box.maxX) ? box.maxX - x : 15;// and higher bound. Basically, we clamp it to fit the radius.

			for (int chunkZ = box.minChunkZ; chunkZ <= box.maxChunkZ; chunkZ++) {
				// Time to getStore the chunk (16x256x16) and split it into 16 vertical extends (16x16x16)
				if (!world.hasChunk(chunkX, chunkZ)) {
					continue; // We won't find anything interesting in unloaded chunks
				}

				LevelChunk chunk = world.getChunk(chunkX, chunkZ);
				LevelChunkSection[] extendsList = chunk.getSections();

				// Pre-compute the extend bounds on Z
				int z = chunkZ << 4;
				lowBoundZ = (z < box.minZ) ? box.minZ - z : 0;
				highBoundZ = (z + 15 > box.maxZ) ? box.maxZ - z : 15;

				// Loop on the extends around the player's layer (6 down, 2 up)
				for (int curExtend = box.minChunkY; curExtend <= box.maxChunkY; curExtend++) {
					LevelChunkSection ebs = extendsList[curExtend + (Math.abs(chunk.getMinBuildHeight()) >> 4) ];
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

                                final Optional<TagKey<Block>> firstTag = currentState.getTags().filter(tag -> tag.toString().contains("ores/")).findFirst();
                                if (!firstTag.isPresent())
                                    continue;

                                block = firstTag.get();
								
                                for(BlockData data: PotionsMaster.blockStore.getStore().values()) {
									PotionsMaster.LOGGER.debug("Checking block: " + block.location().toString() + " " + data.getoreTag());
									if (block.location().toString().contains(data.getoreTag())) {
										double alpha = Math.max(0, Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().distanceToSqr(x + i, y + j, z + k) / (Controller.getRadius() / 2));
										dataWithUUID = PotionsMaster.blockStore.getStoreByReference(data.getoreTag());
										PotionsMaster.LOGGER.debug("Adding block to render queue: " + block.toString() + " " + dataWithUUID.getBlockData().getEntryName());
										
										if (dataWithUUID.getBlockData() == null || !dataWithUUID.getBlockData().isDrawing()) // fail safe
										continue;

										// Calculate distance from player to block. Fade out further away blocks
										//double alpha = Math.max(0, ((Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().getDistanceSq(x + i, y + j, z + k)) / Controller.getRadius() ) * 255);
								
										// Push the block to the render queue
										PotionsMaster.LOGGER.debug("Adding block to render queue: " + x + i + " " + y + j + " " + z + k + " " + dataWithUUID.getBlockData().getColor() + " " + alpha);
										renderQueue.add(new BlockInfo(x + i, y + j, z + k, dataWithUUID.getBlockData().getColor(), 1.0f));
									}
								}

								
							}
						}
					}
				}
			}
		}
		renderQueue.sort((t, t1) -> Double.compare(t1.distSqr(new Vec3i(player.getBlockX(), player.getBlockY(),player.getBlockZ())), t.distSqr(new Vec3i(player.getBlockX(), player.getBlockY(),player.getBlockZ()))));

		Render.ores.clear();
		Render.ores.addAll(renderQueue); // Add all our found blocks to the Render.ores list. To be use by Render when drawing.
	}
}
