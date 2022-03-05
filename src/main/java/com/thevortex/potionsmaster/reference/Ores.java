package com.thevortex.potionsmaster.reference;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;


public class Ores {

    public static final TagKey<Block> DIAMOND = vanillaWrapper("diamond_ores");
    public static final TagKey<Block> LAPIS = vanillaWrapper("lapis_ores");
    public static final TagKey<Block> ALUMINIUM = makeWrapperTag("ores/aluminum");
    public static final TagKey<Block> COPPER = vanillaWrapper("copper_ores");
    public static final TagKey<Block> TIN = makeWrapperTag("ores/tin");
    public static final TagKey<Block> LEAD = makeWrapperTag("ores/lead");
    public static final TagKey<Block> SILVER = makeWrapperTag("ores/silver");
    public static final TagKey<Block> GOLD = vanillaWrapper("gold_ores");
    public static final TagKey<Block> URANIUM = makeWrapperTag("ores/uranium");
    public static final TagKey<Block> NICKEL = makeWrapperTag("ores/nickel");
    public static final TagKey<Block> IRON = vanillaWrapper("iron_ores");
    public static final TagKey<Block> ZINC = makeWrapperTag("ores/zinc");
    public static final TagKey<Block> EMERALD = vanillaWrapper("emerald_ores");
    public static final TagKey<Block> COAL = vanillaWrapper("coal_ores");
    public static final TagKey<Block> REDSTONE = vanillaWrapper("redstone_ores");
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
