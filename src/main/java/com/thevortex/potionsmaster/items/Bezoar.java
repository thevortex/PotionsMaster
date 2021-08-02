package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class Bezoar extends Item {

    public Bezoar(Properties properties) {

        super(properties.stacksTo(16));

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {

        if ((entityLiving instanceof Player) && (stack.getItem() == ModItems.BEZOAR)) {
            Player player = (Player) entityLiving;
            if (player.hasEffect(MobEffects.POISON)) {
                player.removeEffect(MobEffects.POISON);
            }
        }
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
