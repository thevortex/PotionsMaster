package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import net.minecraft.item.Item.Properties;

public class GallBladder extends Item {

    public GallBladder(Properties properties) {
        super(properties.stacksTo(16));

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        if ((entityLiving instanceof PlayerEntity) && (stack.getItem() == ModItems.GALLBLADDER)) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            if (player.hasEffect(Effects.DIG_SLOWDOWN)) {
                player.removeEffect(Effects.DIG_SLOWDOWN);
            }
        }
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
