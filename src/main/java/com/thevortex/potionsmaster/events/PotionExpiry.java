package com.thevortex.potionsmaster.events;

import java.util.Collection;

import com.thevortex.potionsmaster.init.ModPotionEffects;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.network.PotionPacket;

import com.thevortex.potionsmaster.reference.Ores;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.PotionEvent.PotionExpiryEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PotionExpiry {

    @SubscribeEvent
    public static void onPlayerDeath(PlayerRespawnEvent event) {
        if (event.getEntityLiving() instanceof Player) {
            Player player = (Player) event.getEntityLiving();

            PotionPacket pkt = new PotionPacket("allthemodium");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("vibranium");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("unobtainium");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("aluminum");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("coal");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("osmium");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("copper");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("diamond");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("emerald");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("gold");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("iron");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("lapis");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("lead");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("nickel");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("quartz");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("platinum");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("redstone");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("silver");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("tin");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("uranium");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("zinc");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
            pkt = new PotionPacket("netherite_scrap");
            PacketHandler.sendTo(pkt, (ServerPlayer) player);
        }
    }

    @SubscribeEvent
    public static void onpotionExpired(PotionExpiryEvent event) {
        if (event.getPotionEffect() == null) {
            return;
        }
        if ((isOreSightPotion(event.getPotionEffect().getEffect()))
                && (event.getEntityLiving() instanceof Player)) {

            if (event.getPotionEffect().getEffect() == ModPotionEffects.ALLTHEMODIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.ALLTHEMODIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.VIBRANIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.VIBRANIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.UNOBTAINIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.UNOBTAINIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.NETHERITESIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.NETHERITE.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ALUMINIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.ALUMINIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }

            if (event.getPotionEffect().getEffect() == ModPotionEffects.COALSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.COAL.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.COPPERSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.COPPER.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.PLATINUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.PLATINUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.DIAMONDSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.DIAMOND.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.EMERALDSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.EMERALD.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.GOLDSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.GOLD.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.IRONSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.IRON.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.LAPISSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.LAPIS.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.LEADSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.LEAD.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.NICKELSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.NICKEL.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }

            if (event.getPotionEffect().getEffect() == ModPotionEffects.QUARTZSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.QUARTZ.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.REDSTONESIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.REDSTONE.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.SILVERSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.SILVER.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.TINSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.TIN.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.URANIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.URANIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ZINCSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.ZINC.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.OSMIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.OSMIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
        }
    }

    @SubscribeEvent
    public static void onpotionRemoved(PotionRemoveEvent event) {
        if (event.getPotionEffect() == null) {
            return;
        }
        if ((isOreSightPotion(event.getPotionEffect().getEffect()))
                && (event.getEntityLiving() instanceof Player)) {
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ALLTHEMODIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.ALLTHEMODIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.VIBRANIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.VIBRANIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.UNOBTAINIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.UNOBTAINIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.NETHERITESIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.NETHERITE.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ALUMINIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.ALUMINIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }

            if (event.getPotionEffect().getEffect() == ModPotionEffects.COALSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.COAL.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.COPPERSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.COPPER.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.PLATINUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.PLATINUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.DIAMONDSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.DIAMOND.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.EMERALDSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.EMERALD.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.GOLDSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.GOLD.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.IRONSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.IRON.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.LAPISSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.LAPIS.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.LEADSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.LEAD.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.NICKELSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.NICKEL.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }

            if (event.getPotionEffect().getEffect() == ModPotionEffects.QUARTZSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.QUARTZ.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.REDSTONESIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.REDSTONE.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.SILVERSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.SILVER.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.TINSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.TIN.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.URANIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.URANIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ZINCSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.ZINC.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.OSMIUMSIGHT) {
                PotionPacket pkt = new PotionPacket(Ores.OSMIUM.toString());
                PacketHandler.sendTo(pkt, (ServerPlayer) event.getEntityLiving());

            }
        }
    }

    private static boolean isOreSightPotion(MobEffect potion) {

        return (potion.getRegistryName().getNamespace().contains("potionsmaster"));
    }

}