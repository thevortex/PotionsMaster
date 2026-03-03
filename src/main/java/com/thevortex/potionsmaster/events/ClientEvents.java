package com.thevortex.potionsmaster.events;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.Map;

@EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientEvents {


    @SubscribeEvent
    public static void onRegisterAdditional(ModelEvent.RegisterAdditional event) {
        // Register the base template models so they get loaded
        PotionsMaster.LOGGER.info("=== Registering Additional Models ===");
        event.register(ModelResourceLocation.standalone(
            ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "item/base_powder")));
        PotionsMaster.LOGGER.info("Registered base_powder model for loading");

        event.register(ModelResourceLocation.standalone(
            ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "item/calcinated_base")));
        PotionsMaster.LOGGER.info("Registered calcinated_base model for loading");
        PotionsMaster.LOGGER.info("=== Additional Models Registration Complete ===");
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void on(RegisterColorHandlersEvent.Item event) {
        PotionsMaster.LOGGER.info("=== Starting Color Handler Registration ===");
        PotionsMaster.LOGGER.info("Total BlockData entries: " + PotionsMaster.blockStore.getStore().size());

            for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
                int color = data.getColor();
                event.getItemColors().register((stack, tintIndex) -> {
                    // Apply color tint to layer 0 (the texture layer)
                    return tintIndex == 0 ? color : -1;
                }, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID , data.getEntryName() + "_oresight_powder")));
                PotionsMaster.LOGGER.info("Registered color handler for " + data.getEntryName() + "_oresight_powder with color: " + color + " (hex: " + String.format("0x%08X", color) + ")");
            }
     
            for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
                int color = data.getColor();
                event.getItemColors().register((stack, tintIndex) -> {
                    // Apply color tint to layer 0 (the texture layer)
                    return tintIndex == 0 ? color : -1;
                }, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID , "calcinated_" + data.getEntryName() + "_oresight_powder")));
                PotionsMaster.LOGGER.info("Registered color handler for calcinated_" + data.getEntryName() + "_oresight_powder with color: " + color + " (hex: " + String.format("0x%08X", color) + ")");
            }

        PotionsMaster.LOGGER.info("=== Color Handler Registration Complete ===");
    }

    @SubscribeEvent
    public static void onRegisterSprites(net.neoforged.neoforge.client.event.RegisterSpriteSourceTypesEvent event) {
        PotionsMaster.LOGGER.info("=== Registering Sprite Source Types ===");
        // Register our custom sprite source type for dynamic effect icons
        com.thevortex.potionsmaster.client.DynamicEffectSpriteSource.registerSpriteSourceType(event);
        PotionsMaster.LOGGER.info("=== Sprite Source Types Registration Complete ===");
    }

    @SubscribeEvent
    public static void onRegisterClientReloadListeners(net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent event) {
        PotionsMaster.LOGGER.info("=== Registering Language Reload Listener ===");
        event.registerReloadListener(new com.thevortex.potionsmaster.client.DynamicLanguageProvider());
        PotionsMaster.LOGGER.info("=== Language Reload Listener Registered ===");
    }

    @SubscribeEvent
    public static void onTextureAtlasStitch(net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent event) {
        // After mob_effect atlas is stitched, verify our sprites were added
        if (event.getAtlas().location().equals(ResourceLocation.withDefaultNamespace("textures/atlas/mob_effects.png"))) {
            PotionsMaster.LOGGER.info("Mob effects atlas stitched - dynamic effect icons should be available");
        }
    }


        @SubscribeEvent
        public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
            Map<ModelResourceLocation, BakedModel> modelRegistry = event.getModels();

            PotionsMaster.LOGGER.info("=== Starting Model Registration ===");
            PotionsMaster.LOGGER.info("Total BlockData entries: " + PotionsMaster.blockStore.getStore().size());

            // Get the base models to clone
            ModelResourceLocation basePowderLoc = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "item/base_powder"));
            ModelResourceLocation calcinatedBaseLoc = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "item/calcinated_base"));

            PotionsMaster.LOGGER.info("Looking for base_powder model at: " + basePowderLoc);
            PotionsMaster.LOGGER.info("Looking for calcinated_base model at: " + calcinatedBaseLoc);

            BakedModel basePowderModel = modelRegistry.get(basePowderLoc);
            BakedModel calcinatedBaseModel = modelRegistry.get(calcinatedBaseLoc);

            if (basePowderModel == null) {
                PotionsMaster.LOGGER.error("Base powder model not found! Available models:");
                modelRegistry.keySet().stream()
                    .filter(loc -> loc.id().getNamespace().equals(Reference.MOD_ID))
                    .limit(20)
                    .forEach(loc -> PotionsMaster.LOGGER.error("  - " + loc));
                return;
            }
            if (calcinatedBaseModel == null) {
                PotionsMaster.LOGGER.error("Calcinated base model not found! Available models:");
                modelRegistry.keySet().stream()
                    .filter(loc -> loc.id().getNamespace().equals(Reference.MOD_ID))
                    .limit(20)
                    .forEach(loc -> PotionsMaster.LOGGER.error("  - " + loc));
                return;
            }

            PotionsMaster.LOGGER.info("Successfully found both base models!");

            for(BlockData data : PotionsMaster.blockStore.getStore().values()) {
                // Register regular powder model using base_powder as template
                ModelResourceLocation regularPowderLocation = ModelResourceLocation.inventory(
                    ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, data.getEntryName() + "_oresight_powder"));
                modelRegistry.put(regularPowderLocation, basePowderModel);
                PotionsMaster.LOGGER.info("Registered model for " + data.getEntryName() + "_oresight_powder");

                // Register calcinated powder model using calcinated_base as template
                ModelResourceLocation calcinatedPowderLocation = ModelResourceLocation.inventory(
                    ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "calcinated_" + data.getEntryName() + "_oresight_powder"));
                modelRegistry.put(calcinatedPowderLocation, calcinatedBaseModel);
                PotionsMaster.LOGGER.info("Registered model for calcinated_" + data.getEntryName() + "_oresight_powder");
            }

            PotionsMaster.LOGGER.info("=== Model Registration Complete ===");
        }

}

