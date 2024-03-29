package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModBlocks;
import com.thevortex.potionsmaster.init.ModItems;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

import net.minecraft.item.Item.Properties;

public class Mortar extends BlockItem {

    public Mortar(Properties properties) {
        super(ModBlocks.MORTAR, properties.stacksTo(1));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return (stack.getItem() == ModItems.ITEM_MORTAR);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {

        return new ItemStack(this);
    }

}
