package com.thevortex.potionsmaster.render.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
                location = ResourceLocation.tryParse(e.getoreTag());
            } catch (Exception ignored) {
            }
            ;
            if (location == null)
                continue;

            Block block = BuiltInRegistries.BLOCK.get(location);
            if (block == null)
                continue;

            blockData.add(
                    new BlockData(
                            e.getName(),
                            e.getoreTag(),
                            e.getColor(),
                            e.isDrawing(),
                            e.getOrder(),
                            e.getrecipeItem()
                    )
            );
        }

        return blockData;
    }

    public void put(BlockData data) {
        if (this.storeReference.containsKey(data.getoreTag()))
            return;

        UUID uniqueId = UUID.randomUUID();
        this.store.put(uniqueId, data);

        this.storeReference.put(data.getoreTag(), uniqueId);
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
        UUID uniqueId = storeReference.get(data.getoreTag());
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
