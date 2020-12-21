package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModItems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Pestle extends Item {

    public Pestle(Properties properties) {

        super(properties.maxStackSize(1));

    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return (stack.getItem() == ModItems.PESTLE);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {

        return new ItemStack(this);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (itemstack.getItem() == ModItems.PESTLE) {
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        } else {
            return new ActionResult<>(ActionResultType.FAIL, itemstack);

        }

    }
}
