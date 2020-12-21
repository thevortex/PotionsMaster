package com.thevortex.potionsmaster.items.potions.effect.oresight;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.init.ModPotionEffects;
import com.thevortex.potionsmaster.reference.Ores;
import com.thevortex.potionsmaster.render.util.BlockData;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.BlockStore.BlockDataWithUUID;
import com.thevortex.potionsmaster.render.util.xray.Controller;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class UraniumPotionEffect extends Effect {

    public UraniumPotionEffect(EffectType typeIn, int liquidColorIn) {
        super(EffectType.BENEFICIAL, liquidColorIn);

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        BlockStore store = PotionsMaster.blockStore;
        if (entityLivingBaseIn instanceof ClientPlayerEntity) {
            BlockDataWithUUID bdUUID = store.getStoreByReference(Ores.URANIUM.toString());
            BlockData URANIUM = bdUUID.getBlockData();
            if ((URANIUM.isDrawing() != true) && (!(entityLivingBaseIn.getActivePotionEffect(ModPotionEffects.URANIUMSIGHT) == null))) {
                URANIUM.setDrawing(true);
            }
            if (Controller.drawOres() == false) {
                Controller.toggleDrawOres();
            }

            super.performEffect(entityLivingBaseIn, amplifier);
        }
    }
}