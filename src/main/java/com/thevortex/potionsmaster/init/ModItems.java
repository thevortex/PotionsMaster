package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.items.Bezoar;
import com.thevortex.potionsmaster.items.Cauldron;
import com.thevortex.potionsmaster.items.GallBladder;
import com.thevortex.potionsmaster.items.Mortar;
import com.thevortex.potionsmaster.items.Pestle;
import com.thevortex.potionsmaster.items.feathers.RedFeather;
import com.thevortex.potionsmaster.items.powders.base.*;
import com.thevortex.potionsmaster.items.powders.calcinated.*;
import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {
    @ObjectHolder("potionsmaster:charcoal_powder")
    public static Item CHARCOAL_POWDER;
    @ObjectHolder("potionsmaster:coal_powder")
    public static Item COAL_POWDER;
    @ObjectHolder("potionsmaster:diamond_powder")
    public static Item DIAMOND_POWDER;
    @ObjectHolder("potionsmaster:emerald_powder")
    public static Item EMERALD_POWDER;
    @ObjectHolder("potionsmaster:gold_powder")
    public static Item GOLD_POWDER;
    @ObjectHolder("potionsmaster:iron_powder")
    public static Item IRON_POWDER;
    @ObjectHolder("potionsmaster:lapis_powder")
    public static Item LAPIS_POWDER;
    @ObjectHolder("potionsmaster:redstone_powder")
    public static Item REDSTONE_POWDER;

    @ObjectHolder("potionsmaster:aluminium_powder")
    public static Item ALUMINIUM_POWDER;
    @ObjectHolder("potionsmaster:copper_powder")
    public static Item COPPER_POWDER;
    @ObjectHolder("potionsmaster:tin_powder")
    public static Item TIN_POWDER;
    @ObjectHolder("potionsmaster:nickel_powder")
    public static Item NICKEL_POWDER;
    @ObjectHolder("potionsmaster:uranium_powder")
    public static Item URANIUM_POWDER;
    @ObjectHolder("potionsmaster:lead_powder")
    public static Item LEAD_POWDER;
    @ObjectHolder("potionsmaster:silver_powder")
    public static Item SILVER_POWDER;
    @ObjectHolder("potionsmaster:zinc_powder")
    public static Item ZINC_POWDER;
    @ObjectHolder("potionsmaster:osmium_powder")
    public static Item OSMIUM_POWDER;
    @ObjectHolder("potionsmaster:quartz_powder")
    public static Item QUARTZ_POWDER;
    @ObjectHolder("potionsmaster:bismuth_powder")
    public static Item BISMUTH_POWDER;
    @ObjectHolder("potionsmaster:crimsoniron_powder")
    public static Item CRIMSONIRON_POWDER;
    @ObjectHolder("potionsmaster:platinum_powder")
    public static Item PLATINUM_POWDER;
    @ObjectHolder("potionsmaster:allthemodium_powder")
    public static Item ALLTHEMODIUM_POWDER;
    @ObjectHolder("potionsmaster:vibranium_powder")
    public static Item VIBRANIUM_POWDER;
    @ObjectHolder("potionsmaster:unobtainium_powder")
    public static Item UNOBTAINIUM_POWDER;
    @ObjectHolder("potionsmaster:netherite_powder")
    public static Item NETHERITE_POWDER;

    @ObjectHolder("potionsmaster:calcinatedcoal_powder")
    public static Item CALCINATEDCOAL_POWDER;
    @ObjectHolder("potionsmaster:calcinateddiamond_powder")
    public static Item CALCINATEDDIAMOND_POWDER;
    @ObjectHolder("potionsmaster:calcinatedemerald_powder")
    public static Item CALCINATEDEMERALD_POWDER;
    @ObjectHolder("potionsmaster:calcinatedgold_powder")
    public static Item CALCINATEDGOLD_POWDER;
    @ObjectHolder("potionsmaster:calcinatediron_powder")
    public static Item CALCINATEDIRON_POWDER;
    @ObjectHolder("potionsmaster:calcinatedlapis_powder")
    public static Item CALCINATEDLAPIS_POWDER;
    @ObjectHolder("potionsmaster:calcinatedredstone_powder")
    public static Item CALCINATEDREDSTONE_POWDER;
    @ObjectHolder("potionsmaster:calcinatednetherite_powder")
    public static Item CALCINATEDNETHERITE_POWDER;
    @ObjectHolder("potionsmaster:calcinatedaluminium_powder")
    public static Item CALCINATEDALUMINIUM_POWDER;
    @ObjectHolder("potionsmaster:calcinatedcopper_powder")
    public static Item CALCINATEDCOPPER_POWDER;
    @ObjectHolder("potionsmaster:calcinatedtin_powder")
    public static Item CALCINATEDTIN_POWDER;
    @ObjectHolder("potionsmaster:calcinatednickel_powder")
    public static Item CALCINATEDNICKEL_POWDER;
    @ObjectHolder("potionsmaster:calcinateduranium_powder")
    public static Item CALCINATEDURANIUM_POWDER;
    @ObjectHolder("potionsmaster:calcinatedlead_powder")
    public static Item CALCINATEDLEAD_POWDER;
    @ObjectHolder("potionsmaster:calcinatedsilver_powder")
    public static Item CALCINATEDSILVER_POWDER;
    @ObjectHolder("potionsmaster:calcinatedzinc_powder")
    public static Item CALCINATEDZINC_POWDER;
    @ObjectHolder("potionsmaster:calcinatedosmium_powder")
    public static Item CALCINATEDOSMIUM_POWDER;
    @ObjectHolder("potionsmaster:calcinatedquartz_powder")
    public static Item CALCINATEDQUARTZ_POWDER;
    @ObjectHolder("potionsmaster:calcinatedbismuth_powder")
    public static Item CALCINATEDBISMUTH_POWDER;
    @ObjectHolder("potionsmaster:calcinatedcrimsoniron_powder")
    public static Item CALCINATEDCRIMSONIRON_POWDER;
    @ObjectHolder("potionsmaster:calcinatedplatinum_powder")
    public static Item CALCINATEDPLATINUM_POWDER;
    @ObjectHolder("potionsmaster:calcinatedallthemodium_powder")
    public static Item CALCINATEDALLTHEMODIUM_POWDER;
    @ObjectHolder("potionsmaster:calcinatedvibranium_powder")
    public static Item CALCINATEDVIBRANIUM_POWDER;
    @ObjectHolder("potionsmaster:calcinatedunobtainium_powder")
    public static Item CALCINATEDUNOBTAINIUM_POWDER;

    @ObjectHolder("potionsmaster:pestle")
    public static Item PESTLE;
    @ObjectHolder("potionsmaster:bezoar")
    public static Item BEZOAR;
    @ObjectHolder("potionsmaster:gallbladder")
    public static Item GALLBLADDER;

    @ObjectHolder("potionsmaster:ender_powder")
    public static Item ENDER_POWDER;


    @ObjectHolder("potionsmaster:red_feather")
    public static Item RED_FEATHER;

    @ObjectHolder("potionsmaster:item_cauldron")
    public static Item ITEM_CAULDRON;

    @ObjectHolder("potionsmaster:item_mortar")
    public static Item ITEM_MORTAR;
    @ObjectHolder("potionsmaster:activated_charcoal")
    public static Item ACTIVATEDCHARCOAL;

    public static void init(Register<Item> event) {
        ItemGroup group = PotionsMaster.GROUP;

        RED_FEATHER = new RedFeather(new Item.Properties().group(group)).setRegistryName(location("red_feather"));


        CHARCOAL_POWDER = new CharcoalPowder(new Item.Properties().group(group)).setRegistryName(location("charcoal_powder"));
        COAL_POWDER = new CoalPowder(new Item.Properties().group(group)).setRegistryName(location("coal_powder"));
        DIAMOND_POWDER = new DiamondPowder(new Item.Properties().group(group)).setRegistryName(location("diamond_powder"));
        EMERALD_POWDER = new EmeraldPowder(new Item.Properties().group(group)).setRegistryName(location("emerald_powder"));
        GOLD_POWDER = new GoldPowder(new Item.Properties().group(group)).setRegistryName(location("gold_powder"));
        IRON_POWDER = new IronPowder(new Item.Properties().group(group)).setRegistryName(location("iron_powder"));
        LAPIS_POWDER = new LapisPowder(new Item.Properties().group(group)).setRegistryName(location("lapis_powder"));
        REDSTONE_POWDER = new RedstonePowder(new Item.Properties().group(group)).setRegistryName(location("redstone_powder"));

        ALUMINIUM_POWDER = new AluminiumPowder(new Item.Properties().group(group)).setRegistryName(location("aluminium_powder"));
        COPPER_POWDER = new CopperPowder(new Item.Properties().group(group)).setRegistryName(location("copper_powder"));
        TIN_POWDER = new TinPowder(new Item.Properties().group(group)).setRegistryName(location("tin_powder"));
        NICKEL_POWDER = new NickelPowder(new Item.Properties().group(group)).setRegistryName(location("nickel_powder"));
        URANIUM_POWDER = new UraniumPowder(new Item.Properties().group(group)).setRegistryName(location("uranium_powder"));
        LEAD_POWDER = new LeadPowder(new Item.Properties().group(group)).setRegistryName(location("lead_powder"));
        SILVER_POWDER = new SilverPowder(new Item.Properties().group(group)).setRegistryName(location("silver_powder"));
        ZINC_POWDER = new ZincPowder(new Item.Properties().group(group)).setRegistryName(location("zinc_powder"));
        OSMIUM_POWDER = new OsmiumPowder(new Item.Properties().group(group)).setRegistryName(location("osmium_powder"));
        QUARTZ_POWDER = new QuartzPowder(new Item.Properties().group(group)).setRegistryName(location("quartz_powder"));
        BISMUTH_POWDER = new BismuthPowder(new Item.Properties().group(group)).setRegistryName(location("bismuth_powder"));
        CRIMSONIRON_POWDER = new CrimsonIronPowder(new Item.Properties().group(group)).setRegistryName(location("crimsoniron_powder"));
        PLATINUM_POWDER = new PlatinumPowder(new Item.Properties().group(group)).setRegistryName(location("platinum_powder"));
        NETHERITE_POWDER = new NetheritePowder(new Item.Properties().group(group)).setRegistryName(location("netherite_powder"));

        ALLTHEMODIUM_POWDER = new AllthemodiumPowder(new Item.Properties().group(group)).setRegistryName(location("allthemodium_powder"));
        VIBRANIUM_POWDER = new VibraniumPowder(new Item.Properties().group(group)).setRegistryName(location("vibranium_powder"));
        UNOBTAINIUM_POWDER = new UnobtainiumPowder(new Item.Properties().group(group)).setRegistryName(location("unobtainium_powder"));

        ENDER_POWDER = new EnderPowder(new Item.Properties().group(group)).setRegistryName(location("ender_powder"));

        CALCINATEDCOAL_POWDER = new CalcinatedCoalPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedcoal_powder"));
        CALCINATEDDIAMOND_POWDER = new CalcinatedDiamondPowder(new Item.Properties().group(group)).setRegistryName(location("calcinateddiamond_powder"));
        CALCINATEDEMERALD_POWDER = new CalcinatedEmeraldPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedemerald_powder"));
        CALCINATEDGOLD_POWDER = new CalcinatedGoldPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedgold_powder"));
        CALCINATEDIRON_POWDER = new CalcinatedIronPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatediron_powder"));
        CALCINATEDLAPIS_POWDER = new CalcinatedLapisPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedlapis_powder"));
        CALCINATEDREDSTONE_POWDER = new CalcinatedRedstonePowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedredstone_powder"));
        CALCINATEDNETHERITE_POWDER = new CalcinatedNetheritePowder(new Item.Properties().group(group)).setRegistryName(location("calcinatednetherite_powder"));

        CALCINATEDALUMINIUM_POWDER = new CalcinatedAluminiumPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedaluminium_powder"));
        CALCINATEDCOPPER_POWDER = new CalcinatedCopperPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedcopper_powder"));
        CALCINATEDTIN_POWDER = new CalcinatedTinPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedtin_powder"));
        CALCINATEDNICKEL_POWDER = new CalcinatedNickelPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatednickel_powder"));
        CALCINATEDURANIUM_POWDER = new CalcinatedUraniumPowder(new Item.Properties().group(group)).setRegistryName(location("calcinateduranium_powder"));
        CALCINATEDLEAD_POWDER = new CalcinatedLeadPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedlead_powder"));
        CALCINATEDSILVER_POWDER = new CalcinatedSilverPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedsilver_powder"));
        CALCINATEDZINC_POWDER = new CalcinatedZincPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedzinc_powder"));
        CALCINATEDOSMIUM_POWDER = new CalcinatedOsmiumPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedosmium_powder"));
        CALCINATEDQUARTZ_POWDER = new CalcinatedQuartzPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedquartz_powder"));
        CALCINATEDBISMUTH_POWDER = new CalcinatedBismuthPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedbismuth_powder"));
        CALCINATEDCRIMSONIRON_POWDER = new CalcinatedCrimsonIronPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedcrimsoniron_powder"));
        CALCINATEDPLATINUM_POWDER = new CalcinatedPlatinumPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedplatinum_powder"));
        CALCINATEDALLTHEMODIUM_POWDER = new CalcinatedAllthemodiumPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedallthemodium_powder"));
        CALCINATEDVIBRANIUM_POWDER = new CalcinatedVibraniumPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedvibranium_powder"));
        CALCINATEDUNOBTAINIUM_POWDER = new CalcinatedUnobtainiumPowder(new Item.Properties().group(group)).setRegistryName(location("calcinatedunobtainium_powder"));

        PESTLE = new Pestle(new Item.Properties().group(group)).setRegistryName(location("pestle"));
        BEZOAR = new Bezoar(new Item.Properties().group(group).food(ModFoods.BEZOAR)).setRegistryName(location("bezoar"));
        ACTIVATEDCHARCOAL = new ActivatedCharcoal(new Item.Properties().group(group).food(ModFoods.ACTIVATEDCHARCOAL)).setRegistryName(location("activated_charcoal"));
        GALLBLADDER = new GallBladder(new Item.Properties().group(group).food(ModFoods.GALLBLADDER)).setRegistryName(location("gallbladder"));

        Item.Properties properties = new Item.Properties().group(group);

        ITEM_CAULDRON = new Cauldron(properties).setRegistryName(location("cauldron"));
        ITEM_MORTAR = new Mortar(properties).setRegistryName(location("tile_mortar"));
        event.getRegistry().register(ModItems.CHARCOAL_POWDER);
        event.getRegistry().register(ModItems.COAL_POWDER);
        event.getRegistry().register(ModItems.DIAMOND_POWDER);
        event.getRegistry().register(ModItems.EMERALD_POWDER);
        event.getRegistry().register(ModItems.GOLD_POWDER);
        event.getRegistry().register(ModItems.IRON_POWDER);
        event.getRegistry().register(ModItems.LAPIS_POWDER);
        event.getRegistry().register(ModItems.REDSTONE_POWDER);

        event.getRegistry().register(ModItems.ALUMINIUM_POWDER);
        event.getRegistry().register(ModItems.COPPER_POWDER);
        event.getRegistry().register(ModItems.TIN_POWDER);
        event.getRegistry().register(ModItems.NICKEL_POWDER);
        event.getRegistry().register(ModItems.URANIUM_POWDER);
        event.getRegistry().register(ModItems.LEAD_POWDER);
        event.getRegistry().register(ModItems.SILVER_POWDER);
        event.getRegistry().register(ModItems.ZINC_POWDER);
        event.getRegistry().register(ModItems.OSMIUM_POWDER);
        event.getRegistry().register(ModItems.QUARTZ_POWDER);
        event.getRegistry().register(ModItems.BISMUTH_POWDER);
        event.getRegistry().register(ModItems.CRIMSONIRON_POWDER);
        event.getRegistry().register(ModItems.PLATINUM_POWDER);
        event.getRegistry().register(ModItems.ALLTHEMODIUM_POWDER);
        event.getRegistry().register(ModItems.VIBRANIUM_POWDER);
        event.getRegistry().register(ModItems.UNOBTAINIUM_POWDER);
        event.getRegistry().register(ModItems.NETHERITE_POWDER);

        event.getRegistry().register(ModItems.ENDER_POWDER);

        event.getRegistry().register(ModItems.CALCINATEDCOAL_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDDIAMOND_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDEMERALD_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDGOLD_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDIRON_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDLAPIS_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDREDSTONE_POWDER);

        event.getRegistry().register(ModItems.CALCINATEDALUMINIUM_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDCOPPER_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDTIN_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDNICKEL_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDURANIUM_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDLEAD_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDSILVER_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDZINC_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDOSMIUM_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDQUARTZ_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDBISMUTH_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDCRIMSONIRON_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDPLATINUM_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDALLTHEMODIUM_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDVIBRANIUM_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDUNOBTAINIUM_POWDER);
        event.getRegistry().register(ModItems.CALCINATEDNETHERITE_POWDER);
        event.getRegistry().register(ModItems.PESTLE);
        event.getRegistry().register(ModItems.BEZOAR);
        event.getRegistry().register(ModItems.GALLBLADDER);
        event.getRegistry().register(ModItems.ACTIVATEDCHARCOAL);


        event.getRegistry().register(ModItems.RED_FEATHER);

        event.getRegistry().register(ModItems.ITEM_CAULDRON);
        event.getRegistry().register(ModItems.ITEM_MORTAR);
    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(Reference.MOD_ID, name);
    }

}
