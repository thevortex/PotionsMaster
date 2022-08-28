package com.thevortex.potionsmaster.items;


import com.thevortex.potionsmaster.init.ModRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Mortar extends Item {

    public Mortar(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return (stack.getItem() == ModRegistry.ITEM_MORTAR.get());
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {

        return new ItemStack(this);
    }

}
