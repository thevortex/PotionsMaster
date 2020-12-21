package com.thevortex.potionsmaster.init;

import java.util.ArrayList;
import java.util.List;

import com.thevortex.potionsmaster.blocks.Cauldron;
import com.thevortex.potionsmaster.blocks.Mortar;
import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {
    @ObjectHolder("potionsmaster:tile_mortar")
    public static Block MORTAR;
    @ObjectHolder("potionsmaster:cauldron")
    public static Block CAULDRON;


    public static void init(Register<Block> event) {
        MORTAR = new Mortar().setRegistryName(location("tile_mortar"));
        CAULDRON = new Cauldron().setRegistryName(location("cauldron"));

        event.getRegistry().register(ModBlocks.MORTAR);
        event.getRegistry().register(ModBlocks.CAULDRON);
    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(Reference.MOD_ID, name);
    }

}
