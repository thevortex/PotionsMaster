package com.thevortex.potionsmaster.events;

import com.thevortex.potionsmaster.items.potions.effect.oresight.OreSightEffect;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.network.PotionPacket;

import com.thevortex.potionsmaster.reference.Ores;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PotionExpiry {

    @SubscribeEvent
    public static void onPlayerDeath(PlayerRespawnEvent event) {
        if(event.getEntity() instanceof Player) {
            sendAll((Player)event.getEntity());
        }
    }
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(event.getEntity() instanceof Player) {
            sendAll((Player)event.getEntity());
        }
    }

    private static void sendAll(Player player) {
            PotionPacket pkt = new PotionPacket(Ores.ALLTHEMODIUM.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.ALUMINIUM.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.BISMUTH.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.COPPER.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.CRIMSONIRON.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.COAL.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.DIAMOND.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.EMERALD.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.GOLD.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.IRON.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.LEAD.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.LAPIS.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.NICKEL.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.NETHERITE.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.OSMIUM.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.PLATINUM.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.QUARTZ.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.SILVER.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.TIN.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.URANIUM.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.UNOBTAINIUM.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.VIBRANIUM.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket(Ores.ZINC.toString());
            PacketHandler.sendTo(pkt, (ServerPlayer) player);


    }
    @SubscribeEvent
    public static void onpotionExpired(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() == null) {
            return;
        }

        if ((isOreSightPotion(event.getEffectInstance().getEffect()))
                && (event.getEntity() instanceof Player)) {
            OreSightEffect effect = (OreSightEffect) event.getEffectInstance().getEffect();
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
                && (event.getEntity() instanceof Player)) {
            OreSightEffect effect = (OreSightEffect) event.getEffectInstance().getEffect();
            PotionPacket pkt = new PotionPacket(effect.getEffectType());
            PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntity());
        }
    }

    private static boolean isOreSightPotion(MobEffect potion) {
        return ForgeRegistries.MOB_EFFECTS.getKey(potion).getNamespace().contains("potionsmaster");
    }

}