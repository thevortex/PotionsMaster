package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import net.minecraft.item.Item.Properties;

public class Bezoar extends Item {

    public Bezoar(Properties properties) {

        super(properties.stacksTo(16));

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        if ((entityLiving instanceof PlayerEntity) && (stack.getItem() == ModItems.BEZOAR)) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            if (player.hasEffect(Effects.POISON)) {
                player.removeEffect(Effects.POISON);
            }
        }
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
