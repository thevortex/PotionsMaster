package com.thevortex.potionsmaster.items;

import com.thevortex.potionsmaster.init.ModItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GallBladder extends Item {

    public GallBladder(Properties properties) {
        super(properties.stacksTo(16));

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {

        if ((entityLiving instanceof Player) && (stack.getItem() == ModItems.GALLBLADDER)) {
            Player player = (Player) entityLiving;
            if (player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
                player.removeEffect(MobEffects.DIG_SLOWDOWN);
            }
        }
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
