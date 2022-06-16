package com.thevortex.potionsmaster.items;


import com.thevortex.potionsmaster.init.ModRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class Mortar extends BlockItem {

    public Mortar(Properties properties) {
        super(ModRegistry.MORTAR.get(), properties.stacksTo(1));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return (stack.getItem() == ModRegistry.ITEM_MORTAR.get());
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {

        return new ItemStack(this);
    }

}
