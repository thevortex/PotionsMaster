package com.thevortex.potionsmaster.items;


import com.thevortex.potionsmaster.init.ModRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class Mortar extends BlockItem {

    public Mortar(Block block, Properties properties) {
        super(block, properties.stacksTo(1));
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
