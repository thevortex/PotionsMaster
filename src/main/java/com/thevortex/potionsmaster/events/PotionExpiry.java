package com.thevortex.potionsmaster.events;

import java.util.Collection;

import com.thevortex.potionsmaster.init.ModPotionEffects;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.network.PotionPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionExpiryEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PotionExpiry {

    @SubscribeEvent
    public static void onPlayerDeath(PlayerRespawnEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();

            PotionPacket pkt = new PotionPacket("allthemodium");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("vibranium");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("unobtainium");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("aluminum");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("coal");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("osmium");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("copper");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("diamond");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("emerald");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("gold");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("iron");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("lapis");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("lead");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("nickel");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("quartz");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("platinum");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("redstone");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("silver");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("tin");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("uranium");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("zinc");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
            pkt = new PotionPacket("netherite_scrap");
            PacketHandler.sendTo(pkt, (ServerPlayerEntity) player);
        }
    }

    @SubscribeEvent
    public static void onpotionExpired(PotionExpiryEvent event) {
        if (event.getPotionEffect() == null) {
            return;
        }
        if ((isOreSightPotion(event.getPotionEffect().getEffect()))
                && (event.getEntityLiving() instanceof PlayerEntity)) {

            if (event.getPotionEffect().getEffect() == ModPotionEffects.ALLTHEMODIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("allthemodium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.VIBRANIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("vibranium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.UNOBTAINIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("unobtainium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.NETHERITESIGHT) {
                PotionPacket pkt = new PotionPacket("netherite_scrap");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ALUMINIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("aluminum");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }

            if (event.getPotionEffect().getEffect() == ModPotionEffects.COALSIGHT) {
                PotionPacket pkt = new PotionPacket("coal");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.COPPERSIGHT) {
                PotionPacket pkt = new PotionPacket("copper");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.PLATINUMSIGHT) {
                PotionPacket pkt = new PotionPacket("platinum");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.DIAMONDSIGHT) {
                PotionPacket pkt = new PotionPacket("diamond");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.EMERALDSIGHT) {
                PotionPacket pkt = new PotionPacket("emerald");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.GOLDSIGHT) {
                PotionPacket pkt = new PotionPacket("gold");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.IRONSIGHT) {
                PotionPacket pkt = new PotionPacket("iron");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.LAPISSIGHT) {
                PotionPacket pkt = new PotionPacket("lapis");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.LEADSIGHT) {
                PotionPacket pkt = new PotionPacket("lead");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.NICKELSIGHT) {
                PotionPacket pkt = new PotionPacket("nickel");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }

            if (event.getPotionEffect().getEffect() == ModPotionEffects.QUARTZSIGHT) {
                PotionPacket pkt = new PotionPacket("quartz");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.REDSTONESIGHT) {
                PotionPacket pkt = new PotionPacket("redstone");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.SILVERSIGHT) {
                PotionPacket pkt = new PotionPacket("silver");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.TINSIGHT) {
                PotionPacket pkt = new PotionPacket("tin");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.URANIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("uranium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ZINCSIGHT) {
                PotionPacket pkt = new PotionPacket("zinc");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.OSMIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("osmium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
        }
    }

    @SubscribeEvent
    public static void onpotionRemoved(PotionRemoveEvent event) {
        if (event.getPotionEffect() == null) {
            return;
        }
        if ((isOreSightPotion(event.getPotionEffect().getEffect()))
                && (event.getEntityLiving() instanceof PlayerEntity)) {
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ALLTHEMODIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("allthemodium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.VIBRANIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("vibranium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());
            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.UNOBTAINIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("unobtainium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());
            }

            if (event.getPotionEffect().getEffect() == ModPotionEffects.ALUMINIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("aluminum");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.PLATINUMSIGHT) {
                PotionPacket pkt = new PotionPacket("platinum");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.NETHERITESIGHT) {
                PotionPacket pkt = new PotionPacket("netherite_scrap");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.COALSIGHT) {
                PotionPacket pkt = new PotionPacket("coal");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.COPPERSIGHT) {
                PotionPacket pkt = new PotionPacket("copper");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.OSMIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("osmium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.DIAMONDSIGHT) {
                PotionPacket pkt = new PotionPacket("diamond");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.EMERALDSIGHT) {
                PotionPacket pkt = new PotionPacket("emerald");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.GOLDSIGHT) {
                PotionPacket pkt = new PotionPacket("gold");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.IRONSIGHT) {
                PotionPacket pkt = new PotionPacket("iron");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.LAPISSIGHT) {
                PotionPacket pkt = new PotionPacket("lapis");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.LEADSIGHT) {
                PotionPacket pkt = new PotionPacket("lead");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.NICKELSIGHT) {
                PotionPacket pkt = new PotionPacket("nickel");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.QUARTZSIGHT) {
                PotionPacket pkt = new PotionPacket("quartz");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.REDSTONESIGHT) {
                PotionPacket pkt = new PotionPacket("redstone");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.SILVERSIGHT) {
                PotionPacket pkt = new PotionPacket("silver");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.TINSIGHT) {
                PotionPacket pkt = new PotionPacket("tin");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.URANIUMSIGHT) {
                PotionPacket pkt = new PotionPacket("uranium");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
            if (event.getPotionEffect().getEffect() == ModPotionEffects.ZINCSIGHT) {
                PotionPacket pkt = new PotionPacket("zinc");
                PacketHandler.sendTo(pkt, (ServerPlayerEntity) event.getEntityLiving());

            }
        }
    }

    private static boolean isOreSightPotion(Effect potion) {

        return (potion.getRegistryName().getNamespace().contains("potionsmaster"));
    }

}