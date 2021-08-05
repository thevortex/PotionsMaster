package com.thevortex.potionsmaster.reference;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;


public class Ores {

    public static final ResourceLocation DIAMOND = vanillaWrapper("diamond_ores");
    public static final ResourceLocation LAPIS = vanillaWrapper("lapis_ores");
    public static final ResourceLocation ALUMINIUM = makeWrapperTag("ores/aluminum");
    public static final ResourceLocation COPPER = vanillaWrapper("copper_ores");
    public static final ResourceLocation TIN = makeWrapperTag("ores/tin");
    public static final ResourceLocation LEAD = makeWrapperTag("ores/lead");
    public static final ResourceLocation SILVER = makeWrapperTag("ores/silver");
    public static final ResourceLocation GOLD = vanillaWrapper("gold_ores");
    public static final ResourceLocation URANIUM = makeWrapperTag("ores/uranium");
    public static final ResourceLocation NICKEL = makeWrapperTag("ores/nickel");
    public static final ResourceLocation IRON = vanillaWrapper("iron_ores");
    public static final ResourceLocation ZINC = makeWrapperTag("ores/zinc");
    public static final ResourceLocation EMERALD = vanillaWrapper("emerald_ores");
    public static final ResourceLocation COAL = vanillaWrapper("coal_ores");
    public static final ResourceLocation REDSTONE = vanillaWrapper("redstone_ores");
    public static final ResourceLocation OSMIUM = makeWrapperTag("ores/osmium");
    public static final ResourceLocation PLATINUM = makeWrapperTag("ores/platinum");
    public static final ResourceLocation NETHERITE = makeWrapperTag("ores/netherite_scrap");
    public static final ResourceLocation QUARTZ = makeWrapperTag("ores/quartz");
    public static final ResourceLocation CRIMSONIRON = makeWrapperTag("ores/crimson_iron");
    public static final ResourceLocation BISMUTH = makeWrapperTag("ores/bismuth");
    public static final ResourceLocation ALLTHEMODIUM = makeWrapperTag("ores/allthemodium");
    public static final ResourceLocation VIBRANIUM = makeWrapperTag("ores/vibranium");
    public static final ResourceLocation UNOBTAINIUM = makeWrapperTag("ores/unobtainium");

    private static ResourceLocation makeWrapperTag(String tagname) {
        return new ResourceLocation("forge", tagname);

    }
    private static ResourceLocation vanillaWrapper(String tag){
        return new ResourceLocation("minecraft", tag);
    }
}
