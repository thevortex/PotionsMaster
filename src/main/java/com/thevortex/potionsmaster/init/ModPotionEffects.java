package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.items.potions.effect.oresight.*;
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

        COALSIGHT = new CoalPotionEffect(MobEffectCategory.BENEFICIAL, 65793).setRegistryName(location("coalpotioneffect"));
        IRONSIGHT = new IronPotionEffect(MobEffectCategory.BENEFICIAL, 14991530).setRegistryName(location("ironpotioneffect"));
        REDSTONESIGHT = new RedStonePotionEffect(MobEffectCategory.BENEFICIAL, 16711680).setRegistryName(location("redstonepotioneffect"));
        LAPISSIGHT = new LapisPotionEffect(MobEffectCategory.BENEFICIAL, 658175).setRegistryName(location("lapispotioneffect"));
        GOLDSIGHT = new GoldPotionEffect(MobEffectCategory.BENEFICIAL, 13938487).setRegistryName(location("goldpotioneffect"));
        DIAMONDSIGHT = new DiamondPotionEffect(MobEffectCategory.BENEFICIAL, 4053987).setRegistryName(location("diamondpotioneffect"));
        EMERALDSIGHT = new EmeraldPotionEffect(MobEffectCategory.BENEFICIAL, 65280).setRegistryName(location("emeraldpotioneffect"));

        ALUMINIUMSIGHT = new AluminiumPotionEffect(MobEffectCategory.BENEFICIAL, 14935011).setRegistryName(location("aluminiumpotioneffect"));
        COPPERSIGHT = new CopperPotionEffect(MobEffectCategory.BENEFICIAL, 12021818).setRegistryName(location("copperpotioneffect"));
        TINSIGHT = new TinPotionEffect(MobEffectCategory.BENEFICIAL, 7895160).setRegistryName(location("tinpotioneffect"));
        NICKELSIGHT = new NickelPotionEffect(MobEffectCategory.BENEFICIAL, 11118980).setRegistryName(location("nickelpotioneffect"));
        URANIUMSIGHT = new UraniumPotionEffect(MobEffectCategory.BENEFICIAL, 8316792).setRegistryName(location("uraniumpotioneffect"));
        LEADSIGHT = new LeadPotionEffect(MobEffectCategory.BENEFICIAL, 8162502).setRegistryName(location("leadpotioneffect"));
        SILVERSIGHT = new SilverPotionEffect(MobEffectCategory.BENEFICIAL, 10805479).setRegistryName(location("silverpotioneffect"));
        ZINCSIGHT = new ZincPotionEffect(MobEffectCategory.BENEFICIAL, 11908469).setRegistryName(location("zincpotioneffect"));
        OSMIUMSIGHT = new OsmiumPotionEffect(MobEffectCategory.BENEFICIAL, 12634589).setRegistryName(location("osmiumpotioneffect"));
        QUARTZSIGHT = new QuartzPotionEffect(MobEffectCategory.BENEFICIAL, 8162502).setRegistryName(location("quartzpotioneffect"));
        BISMUTHSIGHT = new BismuthPotionEffect(MobEffectCategory.BENEFICIAL, 10805479).setRegistryName(location("bismuthpotioneffect"));
        CRIMSONIRONSIGHT = new CrimsonIronPotionEffect(MobEffectCategory.BENEFICIAL, 11908469).setRegistryName(location("crimsonironpotioneffect"));
        PLATINUMSIGHT = new PlatinumPotionEffect(MobEffectCategory.BENEFICIAL, 12634589).setRegistryName(location("platinumpotioneffect"));
        NETHERITESIGHT = new NetheritePotionEffect(MobEffectCategory.BENEFICIAL, 16753920).setRegistryName(location("netheritepotioneffect"));
        ALLTHEMODIUMSIGHT = new AllthemodiumPotionEffect(MobEffectCategory.BENEFICIAL, 16701786).setRegistryName(location("allthemodiumpotioneffect"));
        VIBRANIUMSIGHT = new VibraniumPotionEffect(MobEffectCategory.BENEFICIAL, 2547336).setRegistryName(location("vibraniumpotioneffect"));
        UNOBTAINIUMSIGHT = new UnobtainiumPotionEffect(MobEffectCategory.BENEFICIAL, 13718243).setRegistryName(location("unobtainiumpotioneffect"));


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
