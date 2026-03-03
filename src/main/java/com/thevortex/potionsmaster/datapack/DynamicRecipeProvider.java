package com.thevortex.potionsmaster.datapack;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * Virtual resource pack that provides dynamic crafting and blasting recipes
 */
@SuppressWarnings({"NullableProblems", "OverriddenMethodCallDuringObjectConstruction", "StringBuilderReplaceableByString"})
public class DynamicRecipeProvider implements PackResources {

    private static final String PACK_ID = "potionsmaster_dynamic_recipes";

    @Override
    public String packId() {
        return PACK_ID;
    }

    @Override
    public PackLocationInfo location() {
        PotionsMaster.LOGGER.info("DynamicRecipeProvider.location() called");
        return new PackLocationInfo(
            PACK_ID,
            net.minecraft.network.chat.Component.literal("PotionsMaster Dynamic Recipes"),
            net.minecraft.server.packs.repository.PackSource.BUILT_IN,
            java.util.Optional.empty()
        );
    }

    @Override
    public IoSupplier<InputStream> getRootResource(String... paths) {
        return null;
    }

    @Override
    public IoSupplier<InputStream> getResource(PackType packType, ResourceLocation location) {
        PotionsMaster.LOGGER.debug("getResource called: " + packType + " -> " + location);

        // Only handle server/data resources for recipes
        if (packType != PackType.SERVER_DATA) {
            return null;
        }

        // Check if this is a recipe file for our mod
        if (!location.getNamespace().equals(Reference.MOD_ID)) {
            return null;
        }

        String path = location.getPath();
        if (!path.startsWith("recipe/")) {
            return null;
        }

        // Get the recipe name - Minecraft includes .json extension
        String recipePath = path.substring("recipe/".length());

        // Remove .json extension if present
        if (recipePath.endsWith(".json")) {
            recipePath = recipePath.substring(0, recipePath.length() - 5);
        }

        PotionsMaster.LOGGER.info("Attempting to provide dynamic recipe: " + location);

        // Extract recipe type and name
        String jsonContent = null;
        if (recipePath.endsWith("_blasting")) {
            String recipeName = recipePath.substring(0, recipePath.length() - "_blasting".length());
            PotionsMaster.LOGGER.info("Generating blasting recipe for: " + recipeName);
            jsonContent = generateBlastingRecipeJson(recipeName);
        } else if (recipePath.endsWith("_crafting")) {
            String recipeName = recipePath.substring(0, recipePath.length() - "_crafting".length());
            PotionsMaster.LOGGER.info("Generating crafting recipe for: " + recipeName);
            jsonContent = generateCraftingRecipeJson(recipeName);
        }

        if (jsonContent != null) {
            byte[] jsonBytes = jsonContent.getBytes(StandardCharsets.UTF_8);
            return () -> new ByteArrayInputStream(jsonBytes);
        }

        return null;
    }

    /**
     * Generate JSON content for a blasting (blast furnace) recipe
     */
    private String generateBlastingRecipeJson(String oreName) {
        PotionsMaster.LOGGER.info("  [Blasting] Generating recipe for ore: " + oreName);

        // Find the ore in blockstore
        BlockData blockData = null;
        for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
            if (data.getEntryName().equals(oreName)) {
                blockData = data;
                break;
            }
        }

        if (blockData == null) {
            PotionsMaster.LOGGER.warn("  [Blasting] BlockData not found for ore: " + oreName);
            return null;
        }
        PotionsMaster.LOGGER.info("  [Blasting] Found BlockData for ore: " + oreName);

        // Verify items exist
        String basePowderName = oreName + "_oresight_powder";
        String calcinatedPowderName = "calcinated_" + oreName + "_oresight_powder";

        Item basePowder = BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, basePowderName)
        );
        Item calcinatedPowder = BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, calcinatedPowderName)
        );

        if (basePowder == Items.AIR) {
            PotionsMaster.LOGGER.warn("  [Blasting] Base powder item not found: " + basePowderName);
            return null;
        }
        PotionsMaster.LOGGER.debug("  [Blasting] Found base powder: " + basePowderName);

        if (calcinatedPowder == Items.AIR) {
            PotionsMaster.LOGGER.warn("  [Blasting] Calcinated powder item not found: " + calcinatedPowderName);
            return null;
        }
        PotionsMaster.LOGGER.debug("  [Blasting] Found calcinated powder: " + calcinatedPowderName);

        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"type\": \"minecraft:blasting\",\n");
        json.append("  \"ingredient\": {\n");
        json.append("    \"item\": \"").append(Reference.MOD_ID).append(":").append(oreName).append("_oresight_powder\"\n");
        json.append("  },\n");
        json.append("  \"result\": {\n");
        json.append("    \"id\": \"").append(Reference.MOD_ID).append(":calcinated_").append(oreName).append("_oresight_powder\",\n");
        json.append("    \"count\": 1\n");
        json.append("  },\n");
        json.append("  \"experience\": 0.1,\n");
        json.append("  \"cookingtime\": 100\n");
        json.append("}\n");

        PotionsMaster.LOGGER.info("  [Blasting] Successfully generated recipe for ore: " + oreName);
        return json.toString();
    }

    /**
     * Generate JSON content for a crafting recipe
     */
    private String generateCraftingRecipeJson(String oreName) {
        PotionsMaster.LOGGER.info("  [Crafting] Generating recipe for ore: " + oreName);

        // Find the ore in blockstore
        BlockData blockData = null;
        for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
            if (data.getEntryName().equals(oreName)) {
                blockData = data;
                break;
            }
        }

        if (blockData == null) {
            PotionsMaster.LOGGER.warn("  [Crafting] BlockData not found for ore: " + oreName);
            return null;
        }
        PotionsMaster.LOGGER.info("  [Crafting] Found BlockData for ore: " + oreName);

        // Verify items exist
        String basePowderName = oreName + "_oresight_powder";
        Item basePowder = BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, basePowderName)
        );

        if (basePowder == Items.AIR) {
            PotionsMaster.LOGGER.warn("  [Crafting] Base powder item not found: " + basePowderName);
            return null;
        }
        PotionsMaster.LOGGER.debug("  [Crafting] Found base powder: " + basePowderName);

        String oreTag = blockData.getoreTag();


        PotionsMaster.LOGGER.debug("  [Crafting] Using ore tag: " + oreTag);

        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"type\": \"minecraft:crafting_shapeless\",\n");
        json.append("  \"category\": \"misc\",\n");
        json.append("  \"group\": \"potionsmaster:ore_powder\",\n");
        json.append("  \"ingredients\": [\n");
        json.append("    {\n");
        json.append("      \"item\": \"").append(Reference.MOD_ID).append(":pestle\"\n");
        json.append("    },\n");
        json.append("    {\n");
        json.append("      \"item\": \"").append(Reference.MOD_ID).append(":tile_mortar\"\n");
        json.append("    },\n");
        json.append("    {\n");
        json.append("      \"item\": \"minecraft:glowstone\"\n");
        json.append("    },\n");
        json.append("    {\n");
        json.append("      \"item\": \"").append(Reference.MOD_ID).append(":ender_powder\"\n");
        json.append("    },\n");
        json.append("    {\n");
        json.append("      \"item\": \"").append(blockData.getrecipeItem()).append("\"\n");
        json.append("    }\n");
        json.append("  ],\n");
        json.append("  \"result\": {\n");
        json.append("    \"id\": \"").append(Reference.MOD_ID).append(":").append(oreName).append("_oresight_powder\",\n");
        json.append("    \"count\": 1\n");
        json.append("  }\n");
        json.append("}\n");

        PotionsMaster.LOGGER.info("  [Crafting] Successfully generated recipe for ore: " + oreName);
        return json.toString();
    }

    @Override
    public void listResources(PackType packType, String namespace, String path, ResourceOutput resourceOutput) {
        PotionsMaster.LOGGER.info("listResources called: packType=" + packType + ", namespace=" + namespace + ", path='" + path + "'");

        if (packType != PackType.SERVER_DATA || !namespace.equals(Reference.MOD_ID)) {
            PotionsMaster.LOGGER.debug("Rejecting listResources call - wrong packType or namespace");
            return;
        }

        // Accept calls where path is empty, "recipe", or starts with "recipe/"
        // This handles all variations of how Minecraft might call this method
        if (!path.isEmpty() && !path.startsWith("recipe")) {
            PotionsMaster.LOGGER.debug("Rejecting listResources call - wrong path: '" + path + "'");
            return;
        }

        int blockStoreSize = PotionsMaster.blockStore.getStore().size();
        if (blockStoreSize == 0) {
            PotionsMaster.LOGGER.warn("BlockStore is empty when listResources was called! No recipes will be provided.");
            return;
        }

        PotionsMaster.LOGGER.info("=== listResources called: Listing " + blockStoreSize + " dynamic recipes ===");

        // List all recipes for all ores in the blockStore
        for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
            final String oreName = data.getEntryName();
            PotionsMaster.LOGGER.info("  Listing recipe for: " + oreName);

            // List blasting recipe
            ResourceLocation blastingLoc = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "recipe/" + oreName + "_blasting.json");
            resourceOutput.accept(blastingLoc, () -> {
                PotionsMaster.LOGGER.debug("getResource called for: " + blastingLoc);
                String jsonContent = generateBlastingRecipeJson(oreName);
                if (jsonContent != null) {
                    return new ByteArrayInputStream(jsonContent.getBytes(StandardCharsets.UTF_8));
                }
                return new ByteArrayInputStream(new byte[0]);
            });

            // List crafting recipe
            ResourceLocation craftingLoc = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "recipe/" + oreName + "_crafting.json");
            resourceOutput.accept(craftingLoc, () -> {
                PotionsMaster.LOGGER.debug("getResource called for: " + craftingLoc);
                String jsonContent = generateCraftingRecipeJson(oreName);
                if (jsonContent != null) {
                    return new ByteArrayInputStream(jsonContent.getBytes(StandardCharsets.UTF_8));
                }
                return new ByteArrayInputStream(new byte[0]);
            });
        }
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        return type == PackType.SERVER_DATA ? Set.of(Reference.MOD_ID) : Set.of();
    }

    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> deserializer) {
        if (deserializer.getMetadataSectionName().equals("pack")) {
            com.google.gson.JsonObject packMeta = new com.google.gson.JsonObject();
            packMeta.addProperty("pack_format", net.minecraft.SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            packMeta.addProperty("description", "PotionsMaster Dynamic Recipes");

            try {
                return deserializer.fromJson(packMeta);
            } catch (Exception e) {
                PotionsMaster.LOGGER.error("Failed to create recipe pack metadata", e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        // Nothing to close
    }
}


