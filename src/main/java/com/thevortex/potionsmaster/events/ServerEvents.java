package com.thevortex.potionsmaster.events;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.datapack.DynamicRecipeProvider;
import com.thevortex.potionsmaster.render.util.BlockData;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.PackSelectionConfig;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.server.packs.PackType;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(modid = Reference.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
@SuppressWarnings({"NullableProblems"})
public class ServerEvents {

    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            PotionsMaster.LOGGER.info("=== Registering Dynamic Recipe Pack ===");

            DynamicRecipeProvider recipePack = new DynamicRecipeProvider();

            Pack.ResourcesSupplier resourcesSupplier = new Pack.ResourcesSupplier() {
                @Override
                public PackResources openPrimary(PackLocationInfo info) {
                    return recipePack;
                }

                @Override
                public PackResources openFull(PackLocationInfo info, Pack.Metadata metadata) {
                    return recipePack;
                }
            };

            event.addRepositorySource(packSource -> {
                PackLocationInfo location = recipePack.location();

                Pack pack = Pack.readMetaAndCreate(
                    location,
                    resourcesSupplier,
                    PackType.SERVER_DATA,
                    new PackSelectionConfig(true, Pack.Position.TOP, false)
                );

                if (pack != null) {
                    packSource.accept(pack);
                    PotionsMaster.LOGGER.info("Successfully registered dynamic recipe pack!");

                    // Log the recipes that will be provided
                    int blockStoreSize = PotionsMaster.blockStore.getStore().size();
                    if (blockStoreSize == 0) {
                        PotionsMaster.LOGGER.warn("WARNING: BlockStore is empty! No recipes will be generated!");
                    } else {
                        PotionsMaster.LOGGER.info("BlockStore has " + blockStoreSize + " ore(s). Will provide " + (blockStoreSize * 2) + " recipes (blasting + crafting for each)");
                        for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
                            PotionsMaster.LOGGER.debug("  - " + data.getEntryName());
                        }
                    }
                }
            });
        }
    }


}

