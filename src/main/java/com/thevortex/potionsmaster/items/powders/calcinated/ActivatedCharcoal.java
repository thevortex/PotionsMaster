package com.thevortex.potionsmaster.items.powders.calcinated;

import com.thevortex.potionsmaster.init.ModItems;


import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ActivatedCharcoal extends Item {

    public ActivatedCharcoal(Properties properties) {

        super(properties.stacksTo(16));

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {

        if ((entityLiving instanceof Player) && (stack.getItem() == ModItems.ACTIVATEDCHARCOAL)) {
            Player player = (Player) entityLiving;
            if (player.hasEffect(MobEffects.WITHER)) {
                player.removeEffect(MobEffects.WITHER);
            }
        }
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
