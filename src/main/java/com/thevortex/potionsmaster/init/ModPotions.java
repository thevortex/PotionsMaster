package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class ModPotions {
    public static final EffectInstance COALSIGHT = new EffectInstance(ModPotionEffects.COALSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance IRONSIGHT = new EffectInstance(ModPotionEffects.IRONSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance REDSTONESIGHT = new EffectInstance(ModPotionEffects.REDSTONESIGHT, 6000, 0, false, false, true);
    public static final EffectInstance LAPISSIGHT = new EffectInstance(ModPotionEffects.LAPISSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance GOLDSIGHT = new EffectInstance(ModPotionEffects.GOLDSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance DIAMONDSIGHT = new EffectInstance(ModPotionEffects.DIAMONDSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance EMERALDSIGHT = new EffectInstance(ModPotionEffects.EMERALDSIGHT, 6000, 0, false, false, true);

    public static final EffectInstance ALUMINIUMSIGHT = new EffectInstance(ModPotionEffects.ALUMINIUMSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance COPPERSIGHT = new EffectInstance(ModPotionEffects.COPPERSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance TINSIGHT = new EffectInstance(ModPotionEffects.TINSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance NICKELSIGHT = new EffectInstance(ModPotionEffects.NICKELSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance URANIUMSIGHT = new EffectInstance(ModPotionEffects.URANIUMSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance LEADSIGHT = new EffectInstance(ModPotionEffects.LEADSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance SILVERSIGHT = new EffectInstance(ModPotionEffects.SILVERSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance ZINCSIGHT = new EffectInstance(ModPotionEffects.ZINCSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance OSMIUMSIGHT = new EffectInstance(ModPotionEffects.OSMIUMSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance QUARTZSIGHT = new EffectInstance(ModPotionEffects.QUARTZSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance BISMUTHSIGHT = new EffectInstance(ModPotionEffects.BISMUTHSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance CRIMSONIRONSIGHT = new EffectInstance(ModPotionEffects.CRIMSONIRONSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance PLATINUMSIGHT = new EffectInstance(ModPotionEffects.PLATINUMSIGHT, 6000, 0, false, false, true);
    public static final EffectInstance NETHERITESIGHT = new EffectInstance(ModPotionEffects.NETHERITESIGHT, 6000, 0, false, false, true);

    public static final EffectInstance ALLTHEMODIUMSIGHT = new EffectInstance(ModPotionEffects.ALLTHEMODIUMSIGHT, 3000, 0, false, false, true);
    public static final EffectInstance VIBRANIUMSIGHT = new EffectInstance(ModPotionEffects.VIBRANIUMSIGHT, 3000, 0, false, false, true);
    public static final EffectInstance UNOBTAINIUMSIGHT = new EffectInstance(ModPotionEffects.UNOBTAINIUMSIGHT, 3000, 0, false, false, true);


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

    //public static final Potion INVISIBILITY = new Potion(new EffectInstance(Effects.INVISIBILITY, 3600,0,false,false,true)).setRegistryName(override("invisibility"));
    //public static final Potion LONG_INVISIBILITY = new Potion(new EffectInstance(Effects.INVISIBILITY, 9600,0,false,false,true)).setRegistryName(override("long_invisibility"));

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

        //event.getRegistry().register(ModPotions.INVISIBILITY);
        //event.getRegistry().register(ModPotions.LONG_INVISIBILITY);
    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(Reference.MOD_ID, name);
    }

    private static ResourceLocation override(String name) {
        return new ResourceLocation("minecraft", name);
    }
}
