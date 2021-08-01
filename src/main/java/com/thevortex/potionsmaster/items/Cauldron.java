package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModBlocks;
import com.thevortex.potionsmaster.init.ModItems;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

import net.minecraft.item.Item.Properties;

public class Cauldron extends BlockItem {

    public Cauldron(Properties properties) {
        super(ModBlocks.CAULDRON, properties.stacksTo(1));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return false;//(stack.getItem() == ModItems.ITEM_CAULDRON);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {

        return new ItemStack(this);
    }

}
