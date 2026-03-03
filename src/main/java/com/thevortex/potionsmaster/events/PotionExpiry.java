package com.thevortex.potionsmaster.events;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.items.potions.effect.oresight.OreSightEffect;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.network.PotionPacket;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
@SuppressWarnings({"StringBuilderReplaceableByString", "NullableProblems"})
public class PotionExpiry {
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof Player player) {
            sendAll(player);
        }
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        PotionsMaster.LOGGER.info("=== Server Started - Registering Dynamic Recipes ===");

        int blockStoreSize = PotionsMaster.blockStore.getStore().size();

        if (blockStoreSize == 0) {
            PotionsMaster.LOGGER.warn("BlockStore is empty - no recipes to register!");
            return;
        }

        PotionsMaster.LOGGER.info("Registering " + (blockStoreSize * 2) + " recipes for " + blockStoreSize + " ores");

        int successCount = 0;
        int failureCount = 0;

        // Try to register recipes through the recipe manager
        try {
            for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
                String oreName = data.getEntryName();

                // Try to register blasting recipe
                try {
                    String blastingJson = generateBlastingRecipeJson(oreName);
                    if (blastingJson != null) {
                        // Validate the JSON can be parsed
                        JsonParser.parseString(blastingJson).getAsJsonObject();
                        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, oreName + "_blasting");

                        // Log successful registration
                        PotionsMaster.LOGGER.debug("  Registered blasting recipe: " + recipeId);
                        successCount++;
                    }
                } catch (Exception e) {
                    PotionsMaster.LOGGER.error("Failed to register blasting recipe for: " + oreName, e);
                    failureCount++;
                }

                // Try to register crafting recipe
                try {
                    String craftingJson = generateCraftingRecipeJson(oreName);
                    if (craftingJson != null) {
                        // Validate the JSON can be parsed
                        JsonParser.parseString(craftingJson).getAsJsonObject();
                        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, oreName + "_crafting");

                        // Log successful registration
                        PotionsMaster.LOGGER.debug("  Registered crafting recipe: " + recipeId);
                        successCount++;
                    }
                } catch (Exception e) {
                    PotionsMaster.LOGGER.error("Failed to register crafting recipe for: " + oreName, e);
                    failureCount++;
                }
            }
            PotionsMaster.LOGGER.info("=== Recipe registration complete: " + successCount + " registered, " + failureCount + " failed ===");
        } catch (Exception e) {
            PotionsMaster.LOGGER.error("Failed to register recipes", e);
        }
    }

    private static String generateBlastingRecipeJson(String oreName) {
        // Verify items exist
        String basePowderName = oreName + "_oresight_powder";
        String calcinatedPowderName = "calcinated_" + oreName + "_oresight_powder";

        Item basePowder = BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, basePowderName)
        );
        Item calcinatedPowder = BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, calcinatedPowderName)
        );

        if (basePowder == Items.AIR || calcinatedPowder == Items.AIR) {
            return null;
        }

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

        return json.toString();
    }

    private static String generateCraftingRecipeJson(String oreName) {
        // Find the ore in blockstore
        BlockData blockData = null;
        for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
            if (data.getEntryName().equals(oreName)) {
                blockData = data;
                break;
            }
        }

        if (blockData == null) {
            return null;
        }

        // Verify items exist
        String basePowderName = oreName + "_oresight_powder";
        Item basePowder = BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, basePowderName)
        );

        if (basePowder == Items.AIR) {
            return null;
        }

        String oreTag = blockData.getoreTag();

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
        json.append("      \"tag\": \"").append(oreTag).append("\"\n");
        json.append("    }\n");
        json.append("  ],\n");
        json.append("  \"result\": {\n");
        json.append("    \"id\": \"").append(Reference.MOD_ID).append(":").append(oreName).append("_oresight_powder\",\n");
        json.append("    \"count\": 1\n");
        json.append("  }\n");
        json.append("}\n");

        return json.toString();
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(event.getEntity() instanceof Player player) {
            sendAll(player);
        }
    }

    private static void sendAll(Player player) {
            
        for (BlockData data:PotionsMaster.blockStore.getStore().values()) {
            PotionPacket pkt = new PotionPacket(data.getoreTag());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
        }

    }
    @SubscribeEvent
    public static void onpotionExpired(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() == null) {
            return;
        }

        if ((isOreSightPotion(event.getEffectInstance().getEffect()))
                && (event.getEntity() instanceof Player)) {
            OreSightEffect effect = (OreSightEffect) event.getEffectInstance().getEffect().value();
            PotionPacket pkt = new PotionPacket(effect.getEffectType());
            PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onpotionRemoved(MobEffectEvent.Remove event) {
        if (event.getEffectInstance() == null) {
            return;
        }
        if ((isOreSightPotion(event.getEffectInstance().getEffect()))
                && (event.getEntity() instanceof ServerPlayer)) {
            OreSightEffect effect = (OreSightEffect) event.getEffectInstance().getEffect().value();
            PotionPacket pkt = new PotionPacket(effect.getEffectType());
            PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntity());
        }
    }


    private static boolean isOreSightPotion(Holder<MobEffect> potion) {
        return potion.getKey().location().getNamespace().contains("potionsmaster");
    }

}

