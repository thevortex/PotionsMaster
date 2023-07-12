package com.thevortex.potionsmaster.reference;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;


public class Ores {

    public static final TagKey<Block> DIAMOND = makeWrapperTag("ores/diamond");
    public static final TagKey<Block> LAPIS = makeWrapperTag("ores/lapis");
    public static final TagKey<Block> ALUMINIUM = makeWrapperTag("ores/aluminum");
    public static final TagKey<Block> COPPER = makeWrapperTag("ores/copper");
    public static final TagKey<Block> TIN = makeWrapperTag("ores/tin");
    public static final TagKey<Block> LEAD = makeWrapperTag("ores/lead");
    public static final TagKey<Block> SILVER = makeWrapperTag("ores/silver");
    public static final TagKey<Block> GOLD = makeWrapperTag("ores/gold");
    public static final TagKey<Block> URANIUM = makeWrapperTag("ores/uranium");
    public static final TagKey<Block> NICKEL = makeWrapperTag("ores/nickel");
    public static final TagKey<Block> IRON = makeWrapperTag("ores/iron");
    public static final TagKey<Block> ZINC = makeWrapperTag("ores/zinc");
    public static final TagKey<Block> EMERALD = makeWrapperTag("ores/emerald");
    public static final TagKey<Block> COAL = makeWrapperTag("ores/coal");
    public static final TagKey<Block> REDSTONE = makeWrapperTag("ores/redstone");
    public static final TagKey<Block> OSMIUM = makeWrapperTag("ores/osmium");
    public static final TagKey<Block> PLATINUM = makeWrapperTag("ores/platinum");
    public static final TagKey<Block> NETHERITE = makeWrapperTag("ores/netherite_scrap");
    public static final TagKey<Block> QUARTZ = makeWrapperTag("ores/quartz");
    public static final TagKey<Block> CRIMSONIRON = makeWrapperTag("ores/crimson_iron");
    public static final TagKey<Block> BISMUTH = makeWrapperTag("ores/bismuth");
    public static final TagKey<Block> ALLTHEMODIUM = makeWrapperTag("ores/allthemodium");
    public static final TagKey<Block> VIBRANIUM = makeWrapperTag("ores/vibranium");
    public static final TagKey<Block> UNOBTAINIUM = makeWrapperTag("ores/unobtainium");

    private static TagKey<Block> makeWrapperTag(String tagname) {
        return BlockTags.create(new ResourceLocation("forge", tagname));

    }
    private static TagKey<Block> vanillaWrapper(String tag){
        return BlockTags.create(new ResourceLocation("minecraft", tag));
    }
}