package com.thevortex.potionsmaster.reference;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;


public class Ores {

    public static final ResourceLocation DIAMOND = makeWrapperTag("ores/diamond");
    public static final ResourceLocation LAPIS = makeWrapperTag("ores/lapis");
    public static final ResourceLocation ALUMINIUM = makeWrapperTag("ores/aluminum");
    public static final ResourceLocation COPPER = makeWrapperTag("ores/copper");
    public static final ResourceLocation TIN = makeWrapperTag("ores/tin");
    public static final ResourceLocation LEAD = makeWrapperTag("ores/lead");
    public static final ResourceLocation SILVER = makeWrapperTag("ores/silver");
    public static final ResourceLocation GOLD = makeWrapperTag("ores/gold");
    public static final ResourceLocation URANIUM = makeWrapperTag("ores/uranium");
    public static final ResourceLocation NICKEL = makeWrapperTag("ores/nickel");
    public static final ResourceLocation IRON = makeWrapperTag("ores/iron");
    public static final ResourceLocation ZINC = makeWrapperTag("ores/zinc");
    public static final ResourceLocation EMERALD = makeWrapperTag("ores/emerald");
    public static final ResourceLocation COAL = makeWrapperTag("ores/coal");
    public static final ResourceLocation REDSTONE = makeWrapperTag("ores/redstone");
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
}
