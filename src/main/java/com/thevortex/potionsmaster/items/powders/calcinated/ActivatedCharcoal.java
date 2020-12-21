package com.thevortex.potionsmaster.items.powders.calcinated;

import com.thevortex.potionsmaster.init.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ActivatedCharcoal extends Item {

    public ActivatedCharcoal(Properties properties) {

        super(properties.maxStackSize(16));

    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        if ((entityLiving instanceof PlayerEntity) && (stack.getItem() == ModItems.ACTIVATEDCHARCOAL)) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            if (player.isPotionActive(Effects.WITHER)) {
                player.removePotionEffect(Effects.WITHER);
            }
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

}
