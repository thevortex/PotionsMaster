package com.thevortex.potionsmaster.items.powders.calcinated;

import com.thevortex.potionsmaster.init.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import net.minecraft.item.Item.Properties;

public class ActivatedCharcoal extends Item {

    public ActivatedCharcoal(Properties properties) {

        super(properties.stacksTo(16));

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        if ((entityLiving instanceof PlayerEntity) && (stack.getItem() == ModItems.ACTIVATEDCHARCOAL)) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            if (player.hasEffect(Effects.WITHER)) {
                player.removeEffect(Effects.WITHER);
            }
        }
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
