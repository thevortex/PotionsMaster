package com.thevortex.potionsmaster.render.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

import com.thevortex.potionsmaster.render.util.BlockData;
import com.thevortex.potionsmaster.render.util.SimpleBlockData;

public class BlockStore {

    // Default blocks
    public static final List<SimpleBlockData> DEFAULT_BLOCKS = new ArrayList<SimpleBlockData>();

    private HashMap<UUID, BlockData> store = new HashMap<>();
    private HashMap<String, UUID> storeReference = new HashMap<>();

    public static ArrayList<BlockData> getFromSimpleBlockList(List<SimpleBlockData> simpleList) {
        ArrayList<BlockData> blockData = new ArrayList<>();

        for (SimpleBlockData e : simpleList) {
            if (e == null)
                continue;

            ResourceLocation location = null;
            try {
                location = new ResourceLocation(e.getBlockName());
            } catch (Exception ignored) {
            }
            ;
            if (location == null)
                continue;

            Block block = ForgeRegistries.BLOCKS.getValue(location);
            if (block == null)
                continue;

            blockData.add(
                    new BlockData(
                            e.getName(),
                            e.getBlockName(),
                            e.getColor(),
                            e.isDrawing(),
                            e.getOrder()
                    )
            );
        }

        return blockData;
    }

    public void put(BlockData data) {
        if (this.storeReference.containsKey(data.getBlockName()))
            return;

        UUID uniqueId = UUID.randomUUID();
        this.store.put(uniqueId, data);

        this.storeReference.put(data.getBlockName(), uniqueId);
    }

    public HashMap<UUID, BlockData> getStore() {
        return store;
    }

    public void setStore(ArrayList<BlockData> store) {
        this.store.clear();
        this.storeReference.clear();

        store.forEach(this::put);

    }

    public BlockDataWithUUID getStoreByReference(String name) {
        UUID uniqueId = storeReference.get(name);
        if (uniqueId == null)
            return null;

        BlockData blockData = this.store.get(uniqueId);
        if (blockData == null)
            return null;

        return new BlockDataWithUUID(blockData, uniqueId);
    }

    public void toggleDrawing(BlockData data) {
        UUID uniqueId = storeReference.get(data.getBlockName());
        if (uniqueId == null)
            return;

        // We'd hope this never happens...
        BlockData blockData = this.store.get(uniqueId);
        if (blockData == null)
            return;

        blockData.setDrawing(!blockData.isDrawing());
    }

    public static final class BlockDataWithUUID {
        BlockData blockData;
        UUID uuid;

        public BlockDataWithUUID(BlockData blockData, UUID uuid) {
            this.blockData = blockData;
            this.uuid = uuid;
        }

        public BlockData getBlockData() {
            return blockData;
        }

        public UUID getUuid() {
            return uuid;
        }
    }
}
