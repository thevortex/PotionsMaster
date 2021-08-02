package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class Pestle extends Item {

    public Pestle(Properties properties) {

        super(properties.stacksTo(1));

    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return (stack.getItem() == ModItems.PESTLE);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {

        return new ItemStack(this);
    }


}
