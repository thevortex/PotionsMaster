package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.event.RegistryEvent.Register;


public class ModPotions {
    public static final MobEffectInstance COALSIGHT = new MobEffectInstance(ModPotionEffects.COALSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance IRONSIGHT = new MobEffectInstance(ModPotionEffects.IRONSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance REDSTONESIGHT = new MobEffectInstance(ModPotionEffects.REDSTONESIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance LAPISSIGHT = new MobEffectInstance(ModPotionEffects.LAPISSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance GOLDSIGHT = new MobEffectInstance(ModPotionEffects.GOLDSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance DIAMONDSIGHT = new MobEffectInstance(ModPotionEffects.DIAMONDSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance EMERALDSIGHT = new MobEffectInstance(ModPotionEffects.EMERALDSIGHT, 6000, 0, false, true, true);

    public static final MobEffectInstance ALUMINIUMSIGHT = new MobEffectInstance(ModPotionEffects.ALUMINIUMSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance COPPERSIGHT = new MobEffectInstance(ModPotionEffects.COPPERSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance TINSIGHT = new MobEffectInstance(ModPotionEffects.TINSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance NICKELSIGHT = new MobEffectInstance(ModPotionEffects.NICKELSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance URANIUMSIGHT = new MobEffectInstance(ModPotionEffects.URANIUMSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance LEADSIGHT = new MobEffectInstance(ModPotionEffects.LEADSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance SILVERSIGHT = new MobEffectInstance(ModPotionEffects.SILVERSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance ZINCSIGHT = new MobEffectInstance(ModPotionEffects.ZINCSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance OSMIUMSIGHT = new MobEffectInstance(ModPotionEffects.OSMIUMSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance QUARTZSIGHT = new MobEffectInstance(ModPotionEffects.QUARTZSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance BISMUTHSIGHT = new MobEffectInstance(ModPotionEffects.BISMUTHSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance CRIMSONIRONSIGHT = new MobEffectInstance(ModPotionEffects.CRIMSONIRONSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance PLATINUMSIGHT = new MobEffectInstance(ModPotionEffects.PLATINUMSIGHT, 6000, 0, false, true, true);
    public static final MobEffectInstance NETHERITESIGHT = new MobEffectInstance(ModPotionEffects.NETHERITESIGHT, 6000, 0, false, true, true);

    public static final MobEffectInstance ALLTHEMODIUMSIGHT = new MobEffectInstance(ModPotionEffects.ALLTHEMODIUMSIGHT, 3000, 0, false, true, true);
    public static final MobEffectInstance VIBRANIUMSIGHT = new MobEffectInstance(ModPotionEffects.VIBRANIUMSIGHT, 3000, 0, false, true, true);
    public static final MobEffectInstance UNOBTAINIUMSIGHT = new MobEffectInstance(ModPotionEffects.UNOBTAINIUMSIGHT, 3000, 0, false, true, true);


    public static final Potion COAL_SIGHT = new Potion("coal_sight", COALSIGHT).setRegistryName(location("coal_sight"));
    public static final Potion IRON_SIGHT = new Potion("iron_sight", IRONSIGHT).setRegistryName(location("iron_sight"));
    public static final Potion REDSTONE_SIGHT = new Potion("redstone_sight", REDSTONESIGHT).setRegistryName(location("redstone_sight"));
    public static final Potion LAPIS_SIGHT = new Potion("lapis_sight", LAPISSIGHT).setRegistryName(location("lapis_sight"));
    public static final Potion GOLD_SIGHT = new Potion("gold_sight", GOLDSIGHT).setRegistryName(location("gold_sight"));
    public static final Potion DIAMOND_SIGHT = new Potion("diamond_sight", DIAMONDSIGHT).setRegistryName(location("diamond_sight"));
    public static final Potion EMERALD_SIGHT = new Potion("emerald_sight", EMERALDSIGHT).setRegistryName(location("emerald_sight"));

    public static final Potion ALUMINIUM_SIGHT = new Potion("aluminium_sight", ALUMINIUMSIGHT).setRegistryName(location("aluminium_sight"));
    public static final Potion COPPER_SIGHT = new Potion("copper_sight", COPPERSIGHT).setRegistryName(location("copper_sight"));
    public static final Potion TIN_SIGHT = new Potion("tin_sight", TINSIGHT).setRegistryName(location("tin_sight"));
    public static final Potion NICKEL_SIGHT = new Potion("nickel_sight", NICKELSIGHT).setRegistryName(location("nickel_sight"));
    public static final Potion URANIUM_SIGHT = new Potion("uranium_sight", URANIUMSIGHT).setRegistryName(location("uranium_sight"));
    public static final Potion LEAD_SIGHT = new Potion("lead_sight", LEADSIGHT).setRegistryName(location("lead_sight"));
    public static final Potion SILVER_SIGHT = new Potion("silver_sight", SILVERSIGHT).setRegistryName(location("silver_sight"));
    public static final Potion ZINC_SIGHT = new Potion("zinc_sight", ZINCSIGHT).setRegistryName(location("zinc_sight"));
    public static final Potion OSMIUM_SIGHT = new Potion("osmium_sight", OSMIUMSIGHT).setRegistryName(location("osmium_sight"));


    public static final Potion QUARTZ_SIGHT = new Potion("quartz_sight", QUARTZSIGHT).setRegistryName(location("quartz_sight"));
    public static final Potion BISMUTH_SIGHT = new Potion("bismuth_sight", BISMUTHSIGHT).setRegistryName(location("bismuth_sight"));
    public static final Potion CRIMSONIRON_SIGHT = new Potion("crimsoniron_sight", CRIMSONIRONSIGHT).setRegistryName(location("crimsoniron_sight"));
    public static final Potion PLATINUM_SIGHT = new Potion("platinum_sight", PLATINUMSIGHT).setRegistryName(location("platinum_sight"));
    public static final Potion NETHERITE_SIGHT = new Potion("netherite_sight", NETHERITESIGHT).setRegistryName(location("netherite_sight"));
    public static final Potion ALLTHEMODIUM_SIGHT = new Potion("allthemodium_sight", ALLTHEMODIUMSIGHT).setRegistryName(location("allthemodium_sight"));
    public static final Potion VIBRANIUM_SIGHT = new Potion("vibranium_sight", VIBRANIUMSIGHT).setRegistryName(location("vibranium_sight"));
    public static final Potion UNOBTAINIUM_SIGHT = new Potion("unobtainium_sight", UNOBTAINIUMSIGHT).setRegistryName(location("unobtainium_sight"));


    public static void init(Register<Potion> event) {

        event.getRegistry().register(ModPotions.COAL_SIGHT);
        event.getRegistry().register(ModPotions.IRON_SIGHT);
        event.getRegistry().register(ModPotions.REDSTONE_SIGHT);
        event.getRegistry().register(ModPotions.LAPIS_SIGHT);
        event.getRegistry().register(ModPotions.GOLD_SIGHT);
        event.getRegistry().register(ModPotions.DIAMOND_SIGHT);
        event.getRegistry().register(ModPotions.EMERALD_SIGHT);

        event.getRegistry().register(ModPotions.ALUMINIUM_SIGHT);
        event.getRegistry().register(ModPotions.COPPER_SIGHT);
        event.getRegistry().register(ModPotions.TIN_SIGHT);
        event.getRegistry().register(ModPotions.NICKEL_SIGHT);
        event.getRegistry().register(ModPotions.URANIUM_SIGHT);
        event.getRegistry().register(ModPotions.LEAD_SIGHT);
        event.getRegistry().register(ModPotions.SILVER_SIGHT);
        event.getRegistry().register(ModPotions.ZINC_SIGHT);
        event.getRegistry().register(ModPotions.OSMIUM_SIGHT);
        event.getRegistry().register(ModPotions.QUARTZ_SIGHT);
        event.getRegistry().register(ModPotions.BISMUTH_SIGHT);
        event.getRegistry().register(ModPotions.CRIMSONIRON_SIGHT);
        event.getRegistry().register(ModPotions.PLATINUM_SIGHT);
        event.getRegistry().register(ModPotions.NETHERITE_SIGHT);
        event.getRegistry().register(ModPotions.ALLTHEMODIUM_SIGHT);
        event.getRegistry().register(ModPotions.VIBRANIUM_SIGHT);
        event.getRegistry().register(ModPotions.UNOBTAINIUM_SIGHT);

    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(Reference.MOD_ID, name);
    }

}
