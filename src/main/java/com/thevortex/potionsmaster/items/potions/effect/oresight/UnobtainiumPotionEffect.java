package com.thevortex.potionsmaster.items.potions.effect.oresight;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.init.ModPotionEffects;
import com.thevortex.potionsmaster.reference.Ores;
import com.thevortex.potionsmaster.render.util.BlockData;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.BlockStore.BlockDataWithUUID;
import com.thevortex.potionsmaster.render.util.xray.Controller;


import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class UnobtainiumPotionEffect extends MobEffect {

    public UnobtainiumPotionEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(MobEffectCategory.BENEFICIAL, liquidColorIn);

    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {

        return duration > 0;
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        BlockStore store = PotionsMaster.blockStore;
        if (entityLivingBaseIn instanceof AbstractClientPlayer) {
            BlockDataWithUUID bdUUID = store.getStoreByReference(Ores.UNOBTAINIUM.toString());
            BlockData UNOBTAINIUM = bdUUID.getBlockData();
            if ((UNOBTAINIUM.isDrawing() != true) && (!(entityLivingBaseIn.getEffect(ModPotionEffects.UNOBTAINIUMSIGHT) == null))) {
                UNOBTAINIUM.setDrawing(true);
            }
            if (Controller.drawOres() == false) {
                Controller.toggleDrawOres();
            }

            super.applyEffectTick(entityLivingBaseIn, amplifier);
        }
    }
}