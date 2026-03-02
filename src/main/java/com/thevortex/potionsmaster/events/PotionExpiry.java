package com.thevortex.potionsmaster.events;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.items.potions.effect.oresight.OreSightEffect;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.network.PotionPacket;
import com.thevortex.potionsmaster.render.util.BlockData;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
public class PotionExpiry {
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof Player player) {
            sendAll(player);
        }
    }

   
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(event.getEntity() instanceof Player player) {
            sendAll(player);
        }
    }

    private static void sendAll(Player player) {
            
        for (BlockData data:PotionsMaster.blockStore.getStore().values()) {
            PotionPacket pkt = new PotionPacket(data.getoreTag());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
        }

    }
    @SubscribeEvent
    public static void onpotionExpired(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() == null) {
            return;
        }

        if ((isOreSightPotion(event.getEffectInstance().getEffect()))
                && (event.getEntity() instanceof Player)) {
            OreSightEffect effect = (OreSightEffect) event.getEffectInstance().getEffect().value();
            PotionPacket pkt = new PotionPacket(effect.getEffectType());
            PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onpotionRemoved(MobEffectEvent.Remove event) {
        if (event.getEffectInstance() == null) {
            return;
        }
        if ((isOreSightPotion(event.getEffectInstance().getEffect()))
                && (event.getEntity() instanceof ServerPlayer)) {
            OreSightEffect effect = (OreSightEffect) event.getEffectInstance().getEffect().value();
            PotionPacket pkt = new PotionPacket(effect.getEffectType());
            PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntity());
        }
    }


    private static boolean isOreSightPotion(Holder<MobEffect> potion) {
        return potion.getKey().location().getNamespace().contains("potionsmaster");
    }

}