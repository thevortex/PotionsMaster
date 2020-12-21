package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.items.potions.effect.oresight.*;
import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class ModPotionEffects {


    public static Effect COALSIGHT;
    public static Effect IRONSIGHT;
    public static Effect REDSTONESIGHT;
    public static Effect LAPISSIGHT;
    public static Effect GOLDSIGHT;
    public static Effect DIAMONDSIGHT;
    public static Effect EMERALDSIGHT;

    public static Effect ALUMINIUMSIGHT;
    public static Effect COPPERSIGHT;
    public static Effect TINSIGHT;
    public static Effect NICKELSIGHT;
    public static Effect URANIUMSIGHT;
    public static Effect LEADSIGHT;
    public static Effect SILVERSIGHT;
    public static Effect ZINCSIGHT;
    public static Effect OSMIUMSIGHT;
    public static Effect QUARTZSIGHT;
    public static Effect BISMUTHSIGHT;
    public static Effect CRIMSONIRONSIGHT;
    public static Effect PLATINUMSIGHT;
    public static Effect NETHERITESIGHT;
    public static Effect ALLTHEMODIUMSIGHT;
    public static Effect VIBRANIUMSIGHT;
    public static Effect UNOBTAINIUMSIGHT;

    public static void init(Register<Effect> event) {

        COALSIGHT = new CoalPotionEffect(EffectType.BENEFICIAL, 65793).setRegistryName(location("coalpotioneffect"));
        IRONSIGHT = new IronPotionEffect(EffectType.BENEFICIAL, 14991530).setRegistryName(location("ironpotioneffect"));
        REDSTONESIGHT = new RedStonePotionEffect(EffectType.BENEFICIAL, 16711680).setRegistryName(location("redstonepotioneffect"));
        LAPISSIGHT = new LapisPotionEffect(EffectType.BENEFICIAL, 658175).setRegistryName(location("lapispotioneffect"));
        GOLDSIGHT = new GoldPotionEffect(EffectType.BENEFICIAL, 13938487).setRegistryName(location("goldpotioneffect"));
        DIAMONDSIGHT = new DiamondPotionEffect(EffectType.BENEFICIAL, 4053987).setRegistryName(location("diamondpotioneffect"));
        EMERALDSIGHT = new EmeraldPotionEffect(EffectType.BENEFICIAL, 65280).setRegistryName(location("emeraldpotioneffect"));

        ALUMINIUMSIGHT = new AluminiumPotionEffect(EffectType.BENEFICIAL, 14935011).setRegistryName(location("aluminiumpotioneffect"));
        COPPERSIGHT = new CopperPotionEffect(EffectType.BENEFICIAL, 12021818).setRegistryName(location("copperpotioneffect"));
        TINSIGHT = new TinPotionEffect(EffectType.BENEFICIAL, 7895160).setRegistryName(location("tinpotioneffect"));
        NICKELSIGHT = new NickelPotionEffect(EffectType.BENEFICIAL, 11118980).setRegistryName(location("nickelpotioneffect"));
        URANIUMSIGHT = new UraniumPotionEffect(EffectType.BENEFICIAL, 8316792).setRegistryName(location("uraniumpotioneffect"));
        LEADSIGHT = new LeadPotionEffect(EffectType.BENEFICIAL, 8162502).setRegistryName(location("leadpotioneffect"));
        SILVERSIGHT = new SilverPotionEffect(EffectType.BENEFICIAL, 10805479).setRegistryName(location("silverpotioneffect"));
        ZINCSIGHT = new ZincPotionEffect(EffectType.BENEFICIAL, 11908469).setRegistryName(location("zincpotioneffect"));
        OSMIUMSIGHT = new OsmiumPotionEffect(EffectType.BENEFICIAL, 12634589).setRegistryName(location("osmiumpotioneffect"));
        QUARTZSIGHT = new QuartzPotionEffect(EffectType.BENEFICIAL, 8162502).setRegistryName(location("quartzpotioneffect"));
        BISMUTHSIGHT = new BismuthPotionEffect(EffectType.BENEFICIAL, 10805479).setRegistryName(location("bismuthpotioneffect"));
        CRIMSONIRONSIGHT = new CrimsonIronPotionEffect(EffectType.BENEFICIAL, 11908469).setRegistryName(location("crimsonironpotioneffect"));
        PLATINUMSIGHT = new PlatinumPotionEffect(EffectType.BENEFICIAL, 12634589).setRegistryName(location("platinumpotioneffect"));
        NETHERITESIGHT = new NetheritePotionEffect(EffectType.BENEFICIAL, 16753920).setRegistryName(location("netheritepotioneffect"));
        ALLTHEMODIUMSIGHT = new AllthemodiumPotionEffect(EffectType.BENEFICIAL, 16701786).setRegistryName(location("allthemodiumpotioneffect"));
        VIBRANIUMSIGHT = new VibraniumPotionEffect(EffectType.BENEFICIAL, 2547336).setRegistryName(location("vibraniumpotioneffect"));
        UNOBTAINIUMSIGHT = new UnobtainiumPotionEffect(EffectType.BENEFICIAL, 13718243).setRegistryName(location("unobtainiumpotioneffect"));


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
