package com.thevortex.potionsmaster.events;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.model.data.ModelDataManager;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void on(RegisterColorHandlersEvent.Item event) {
        
            for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
                event.getItemColors().register((stack, tintIndex) -> {
                    if(stack.getItem() == BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID , data.getEntryName() + "_oresight_powder"))) {
                        return data.getColor();
                    }
                    
                    return tintIndex;
                }, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID , data.getEntryName() + "_oresight_powder")));
                PotionsMaster.LOGGER.info("Registered color handler for " + data.getEntryName() + "_oresight_powder :" + data.getColor());
            }
     
            for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
                event.getItemColors().register((stack, tintIndex) -> {
                    if(stack.getItem() == BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID , "calcinated_" + data.getEntryName() + "_oresight_powder"))) {
                        return data.getColor();
                    }
                    
                    return tintIndex;
                }, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID , "calcinated_" + data.getEntryName() + "_oresight_powder")));
                PotionsMaster.LOGGER.info("Registered color handler for calcinated_" + data.getEntryName() + "_oresight_powder :" + data.getColor());
            }
    }



    }

