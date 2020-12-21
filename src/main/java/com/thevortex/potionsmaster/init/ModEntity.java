package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.entity.TileEntityCauldron;
import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class ModEntity {
    @ObjectHolder("potionsmaster:cauldron")
    public static TileEntityType<TileEntityCauldron> TILE_CAULDRON;

    public static void init(Register<TileEntityType<?>> event) {

        TILE_CAULDRON = TileEntityType.Builder.create(TileEntityCauldron::new, ModBlocks.CAULDRON).build(null);

        TILE_CAULDRON.setRegistryName(location("cauldron"));


        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

        registry.register(TILE_CAULDRON);


    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(Reference.MOD_ID, name);
    }

}
