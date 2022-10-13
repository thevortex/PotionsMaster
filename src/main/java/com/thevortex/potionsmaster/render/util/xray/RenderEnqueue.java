package com.thevortex.potionsmaster.render.util.xray;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mojang.math.Vector3d;
import com.thevortex.potionsmaster.reference.Ores;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockData;
import com.thevortex.potionsmaster.render.util.BlockInfo;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.WorldRegion;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
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
				Render.ores.remove(new BlockInfo(pos, null, 0.0));
				return;
			}

			BlockData data = null;
			if (Controller.getBlockStore().getStore().containsKey(defaultState))
				data = Controller.getBlockStore().getStore().get(defaultState);

			if (data == null)
				return;

			double alpha = Math.max(0, ((Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().distanceToSqr(pos.getX(), pos.getY(), pos.getZ())) / Controller.getRadius()) * 255);

			// the block was added to the world, let's add it to the drawing buffer
			Render.ores.add(new BlockInfo(new Vec3i(pos.getX(),pos.getY(),pos.getZ()), data.getColor().getColor(), alpha));
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

		final Level world = PotionsMaster.proxy.getClientPlayer().level;

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

                                final Optional<TagKey<Block>> firstTag = currentState.getTags().findFirst();
                                if (!firstTag.isPresent())
                                    continue;

                                block = firstTag.get();

                                if (currentState.is(Ores.DIAMOND)) {
                                    block = Ores.DIAMOND;
                                }
                                if (currentState.is(Ores.LAPIS)) {
                                    block = Ores.LAPIS;
                                }
                                if (currentState.is(Ores.ALUMINIUM)) {
                                    block = Ores.ALUMINIUM;
                                }
                                if (currentState.is(Ores.COPPER)) {
                                    block = Ores.COPPER;
                                }
                                if (currentState.is(Ores.TIN)) {
                                    block = Ores.TIN;
                                }
                                if (currentState.is(Ores.LEAD)) {
                                    block = Ores.LEAD;
                                }
                                if (currentState.is(Ores.SILVER)) {
                                    block = Ores.SILVER;
                                }
                                if (currentState.is(Ores.GOLD)) {
                                    block = Ores.GOLD;
                                }
                                if (currentState.is(Ores.URANIUM)) {
                                    block = Ores.URANIUM;
                                }
                                if (currentState.is(Ores.NICKEL)) {
                                    block = Ores.NICKEL;
                                }
                                if (currentState.is(Ores.IRON)) {
                                    block = Ores.IRON;
                                }
                                if (currentState.is(Ores.OSMIUM)) {
                                    block = Ores.OSMIUM;
                                }
                                if (currentState.is(Ores.ZINC)) {
                                    block = Ores.ZINC;
                                }
                                if (currentState.is(Ores.EMERALD)) {
                                    block = Ores.EMERALD;
                                }
                                if (currentState.is(Ores.COAL)) {
                                    block = Ores.COAL;
                                }
                                if (currentState.is(Ores.REDSTONE)) {
                                    block = Ores.REDSTONE;
                                }
                                if (currentState.is(Ores.QUARTZ)) {
                                    block = Ores.QUARTZ;
                                }
                                if (currentState.is(Ores.BISMUTH)) {
                                    block = Ores.BISMUTH;
                                }
                                if (currentState.is(Ores.CRIMSONIRON)) {
                                    block = Ores.CRIMSONIRON;
                                }
                                if (currentState.is(Ores.PLATINUM)) {
                                    block = Ores.PLATINUM;
                                }
                                if (currentState.is(Ores.NETHERITE)) {
                                    block = Ores.NETHERITE;
                                }
                                if (currentState.is(Ores.ALLTHEMODIUM)) {
                                    block = Ores.ALLTHEMODIUM;
                                }
                                if (currentState.is(Ores.VIBRANIUM)) {
                                    block = Ores.VIBRANIUM;
								}
								if (currentState.is(Ores.UNOBTAINIUM)) {
									block = Ores.UNOBTAINIUM;
								}

								dataWithUUID = Controller.getBlockStore().getStoreByReference(block.toString());
								if (dataWithUUID == null)
									continue;

								if (dataWithUUID.getBlockData() == null || !dataWithUUID.getBlockData().isDrawing()) // fail safe
									continue;

								// Calculate distance from player to block. Fade out further away blocks
								//double alpha = Math.max(0, ((Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().getDistanceSq(x + i, y + j, z + k)) / Controller.getRadius() ) * 255);
								double alpha = Math.max(0, Controller.getRadius() - PotionsMaster.proxy.getClientPlayer().distanceToSqr(x + i, y + j, z + k) / (Controller.getRadius() / 4));
								// Push the block to the render queue
								renderQueue.add(new BlockInfo(x + i, y + j, z + k, dataWithUUID.getBlockData().getColor().getColor(), alpha));
							}
						}
					}
				}
			}
		}
		renderQueue.sort((t, t1) -> Double.compare(t1.distSqr(new Vec3i(player.getX(),player.getY(),player.getZ())), t.distSqr(new Vec3i(player.getX(),player.getY(),player.getZ()))));

		Render.ores.clear();
		Render.ores.addAll(renderQueue); // Add all our found blocks to the Render.ores list. To be use by Render when drawing.
	}
}
