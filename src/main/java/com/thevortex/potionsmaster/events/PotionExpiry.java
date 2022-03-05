package com.thevortex.potionsmaster.events;

import java.util.Collection;

import com.thevortex.potionsmaster.init.ModPotionEffects;
import com.thevortex.potionsmaster.items.potions.effect.oresight.OreSightEffect;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.network.PotionPacket;

import com.thevortex.potionsmaster.reference.Ores;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionExpiryEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
    public static void onpotionExpired(PotionExpiryEvent event) {
        if (event.getPotionEffect() == null) {
            return;
        }
        if ((isOreSightPotion(event.getPotionEffect().getEffect()))
                && (event.getEntityLiving() instanceof Player)) {
            OreSightEffect effect = (OreSightEffect) event.getPotionEffect().getEffect();
            PotionPacket pkt = new PotionPacket(effect.getEffectType());
            PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());
        }
    }

    @SubscribeEvent
    public static void onpotionRemoved(PotionRemoveEvent event) {
        if (event.getPotionEffect() == null) {
            return;
        }
        if ((isOreSightPotion(event.getPotionEffect().getEffect()))
                && (event.getEntityLiving() instanceof Player)) {
            OreSightEffect effect = (OreSightEffect) event.getPotionEffect().getEffect();
            PotionPacket pkt = new PotionPacket(effect.getEffectType());
            PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());
        }
    }

    private static boolean isOreSightPotion(MobEffect potion) {
        return (potion.getRegistryName().getNamespace().contains("potionsmaster"));
    }

}