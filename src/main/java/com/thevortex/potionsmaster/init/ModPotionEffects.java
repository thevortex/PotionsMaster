package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.items.potions.effect.oresight.*;
import com.thevortex.potionsmaster.reference.Ores;
import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.RegistryEvent.Register;



public class ModPotionEffects {


    public static MobEffect COALSIGHT;
    public static MobEffect IRONSIGHT;
    public static MobEffect REDSTONESIGHT;
    public static MobEffect LAPISSIGHT;
    public static MobEffect GOLDSIGHT;
    public static MobEffect DIAMONDSIGHT;
    public static MobEffect EMERALDSIGHT;

    public static MobEffect ALUMINIUMSIGHT;
    public static MobEffect COPPERSIGHT;
    public static MobEffect TINSIGHT;
    public static MobEffect NICKELSIGHT;
    public static MobEffect URANIUMSIGHT;
    public static MobEffect LEADSIGHT;
    public static MobEffect SILVERSIGHT;
    public static MobEffect ZINCSIGHT;
    public static MobEffect OSMIUMSIGHT;
    public static MobEffect QUARTZSIGHT;
    public static MobEffect BISMUTHSIGHT;
    public static MobEffect CRIMSONIRONSIGHT;
    public static MobEffect PLATINUMSIGHT;
    public static MobEffect NETHERITESIGHT;
    public static MobEffect ALLTHEMODIUMSIGHT;
    public static MobEffect VIBRANIUMSIGHT;
    public static MobEffect UNOBTAINIUMSIGHT;

    public static void init(Register<MobEffect> event) {

        COALSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.COAL.toString(), 65793).setRegistryName(location("coalpotioneffect"));
        IRONSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.IRON.toString(), 14991530).setRegistryName(location("ironpotioneffect"));
        REDSTONESIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.REDSTONE.toString() ,16711680).setRegistryName(location("redstonepotioneffect"));
        LAPISSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.LAPIS.toString(), 658175).setRegistryName(location("lapispotioneffect"));
        GOLDSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.GOLD.toString(),13938487).setRegistryName(location("goldpotioneffect"));
        DIAMONDSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.DIAMOND.toString(),4053987).setRegistryName(location("diamondpotioneffect"));
        EMERALDSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.EMERALD.toString(),65280).setRegistryName(location("emeraldpotioneffect"));

        ALUMINIUMSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.ALUMINIUM.toString(),14935011).setRegistryName(location("aluminiumpotioneffect"));
        COPPERSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.COPPER.toString(),12021818).setRegistryName(location("copperpotioneffect"));
        TINSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.TIN.toString(),7895160).setRegistryName(location("tinpotioneffect"));
        NICKELSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.NICKEL.toString(),11118980).setRegistryName(location("nickelpotioneffect"));
        URANIUMSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.URANIUM.toString(),8316792).setRegistryName(location("uraniumpotioneffect"));
        LEADSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.LEAD.toString(),8162502).setRegistryName(location("leadpotioneffect"));
        SILVERSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.SILVER.toString(),10805479).setRegistryName(location("silverpotioneffect"));
        ZINCSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.ZINC.toString(),11908469).setRegistryName(location("zincpotioneffect"));
        OSMIUMSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.OSMIUM.toString(),12634589).setRegistryName(location("osmiumpotioneffect"));
        QUARTZSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.QUARTZ.toString(),8162502).setRegistryName(location("quartzpotioneffect"));
        BISMUTHSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.BISMUTH.toString(),10805479).setRegistryName(location("bismuthpotioneffect"));
        CRIMSONIRONSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.CRIMSONIRON.toString(),11908469).setRegistryName(location("crimsonironpotioneffect"));
        PLATINUMSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.PLATINUM.toString(),12634589).setRegistryName(location("platinumpotioneffect"));
        NETHERITESIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.NETHERITE.toString(),16753920).setRegistryName(location("netheritepotioneffect"));
        ALLTHEMODIUMSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.ALLTHEMODIUM.toString(), 16701786).setRegistryName(location("allthemodiumpotioneffect"));
        VIBRANIUMSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.VIBRANIUM.toString(),2547336).setRegistryName(location("vibraniumpotioneffect"));
        UNOBTAINIUMSIGHT = new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.UNOBTAINIUM.toString(),13718243).setRegistryName(location("unobtainiumpotioneffect"));


        event.getRegistry().register(ModPotionEffects.COALSIGHT);
        event.getRegistry().register(ModPotionEffects.IRONSIGHT);
        event.getRegistry().register(ModPotionEffects.REDSTONESIGHT);
        event.getRegistry().register(ModPotionEffects.LAPISSIGHT);
        event.getRegistry().register(ModPotionEffects.GOLDSIGHT);
        event.getRegistry().register(ModPotionEffects.DIAMONDSIGHT);
        event.getRegistry().register(ModPotionEffects.EMERALDSIGHT);


        event.getRegistry().register(ModPotionEffects.ALUMINIUMSIGHT);
        event.getRegistry().register(ModPotionEffects.COPPERSIGHT);
        event.getRegistry().register(ModPotionEffects.TINSIGHT);
        event.getRegistry().register(ModPotionEffects.NICKELSIGHT);
        event.getRegistry().register(ModPotionEffects.URANIUMSIGHT);
        event.getRegistry().register(ModPotionEffects.LEADSIGHT);
        event.getRegistry().register(ModPotionEffects.SILVERSIGHT);
        event.getRegistry().register(ModPotionEffects.ZINCSIGHT);
        event.getRegistry().register(ModPotionEffects.OSMIUMSIGHT);
        event.getRegistry().register(ModPotionEffects.QUARTZSIGHT);
        event.getRegistry().register(ModPotionEffects.BISMUTHSIGHT);
        event.getRegistry().register(ModPotionEffects.CRIMSONIRONSIGHT);
        event.getRegistry().register(ModPotionEffects.PLATINUMSIGHT);

        event.getRegistry().register(ModPotionEffects.NETHERITESIGHT);
        event.getRegistry().register(ModPotionEffects.ALLTHEMODIUMSIGHT);

        event.getRegistry().register(ModPotionEffects.VIBRANIUMSIGHT);
        event.getRegistry().register(ModPotionEffects.UNOBTAINIUMSIGHT);


    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(Reference.MOD_ID, name);
    }

}
