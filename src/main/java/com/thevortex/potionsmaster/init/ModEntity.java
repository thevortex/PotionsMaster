package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;



public class ModEntity {
  //  @ObjectHolder("potionsmaster:cauldron")
    //public static BlockEntityType<TileEntityCauldron> TILE_CAULDRON;

    public static void init(RegistryEvent.Register<BlockEntityType<?>> event) {
/*
        TILE_CAULDRON = BlockEntityType.Builder.of(TileEntityCauldron::new, ModBlocks.CAULDRON).build(null);

        TILE_CAULDRON.setRegistryName(location("cauldron"));


        IForgeRegistry<BlockEntityType<?>> registry = event.getRegistry();

        registry.register(TILE_CAULDRON);

*/
    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(Reference.MOD_ID, name);
    }

}
