package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModBlocks;
import com.thevortex.potionsmaster.init.ModItems;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class Cauldron extends BlockItem {

    public Cauldron(Properties properties) {
        super(ModBlocks.CAULDRON, properties.maxStackSize(1));
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
