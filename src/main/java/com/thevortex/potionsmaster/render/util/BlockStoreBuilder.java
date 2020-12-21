package com.thevortex.potionsmaster.render.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;


public class BlockStoreBuilder {


    public static SimpleBlockData COAL = new SimpleBlockData("CoalOre", "forge:ores/coal", new OutlineColor(82, 82, 82), false, 0);
    public static SimpleBlockData IRON = new SimpleBlockData("IronOre", "forge:ores/iron", new OutlineColor(228, 192, 170), false, 0);
    public static SimpleBlockData REDSTONE = new SimpleBlockData("RedstoneOre", "forge:ores/redstone", new OutlineColor(255, 0, 0), false, 0);
    public static SimpleBlockData LAPIS = new SimpleBlockData("LapisOre", "forge:ores/lapis", new OutlineColor(10, 10, 255), false, 0);
    public static SimpleBlockData GOLD = new SimpleBlockData("GoldOre", "forge:ores/gold", new OutlineColor(212, 175, 55), false, 0);
    public static SimpleBlockData DIAMOND = new SimpleBlockData("DiamondOre", "forge:ores/diamond", new OutlineColor(61, 219, 227), false, 0);
    public static SimpleBlockData EMERALD = new SimpleBlockData("EmeraldOre", "forge:ores/emerald", new OutlineColor(0, 255, 0), false, 0);

    public static List<SimpleBlockData> list = new ArrayList<SimpleBlockData>();


    public static void init() {


        list.add(COAL);
        list.add(IRON);
        list.add(REDSTONE);
        list.add(LAPIS);
        list.add(GOLD);
        list.add(DIAMOND);
        list.add(EMERALD);

        list.add(new SimpleBlockData("AluminumOre", "forge:ores/aluminum", new OutlineColor(227, 227, 227), false, 0));
        list.add(new SimpleBlockData("CopperOre", "forge:ores/copper", new OutlineColor(183, 112, 58), false, 0));
        list.add(new SimpleBlockData("TinOre", "forge:ores/tin", new OutlineColor(120, 120, 120), false, 0));
        list.add(new SimpleBlockData("SilverOre", "forge:ores/silver", new OutlineColor(164, 224, 231), false, 0));
        list.add(new SimpleBlockData("LeadOre", "forge:ores/lead", new OutlineColor(124, 140, 198), false, 0));
        list.add(new SimpleBlockData("NickelOre", "forge:ores/nickel", new OutlineColor(169, 169, 132), false, 0));
        list.add(new SimpleBlockData("UraniumOre", "forge:ores/uranium", new OutlineColor(126, 231, 120), false, 0));
        list.add(new SimpleBlockData("ZincOre", "forge:ores/zinc", new OutlineColor(181, 181, 117), false, 0));
        list.add(new SimpleBlockData("OsmiumOre", "forge:ores/osmium", new OutlineColor(192, 201, 221), false, 0));
        list.add(new SimpleBlockData("BismuthOre", "forge:ores/bismuth", new OutlineColor(181, 181, 181), false, 0));
        list.add(new SimpleBlockData("CrimsonIronOre", "forge:ores/crimson_iron", new OutlineColor(255, 192, 170), false, 0));
        list.add(new SimpleBlockData("NetherQuartzOre", "forge:ores/quartz", new OutlineColor(255, 255, 255), false, 0));
        list.add(new SimpleBlockData("PlatinumOre", "forge:ores/platinum", new OutlineColor(181, 181, 255), false, 0));
        list.add(new SimpleBlockData("NetheriteOre", "forge:ores/netherite_scrap", new OutlineColor(255, 165, 0), false, 0));
        list.add(new SimpleBlockData("AllthemodiumOre", "forge:ores/allthemodium", new OutlineColor(254, 217, 90), false, 0));
        list.add(new SimpleBlockData("VibraniumOre", "forge:ores/vibranium", new OutlineColor(38, 222, 136), false, 0));
        list.add(new SimpleBlockData("UnobtainiumOre", "forge:ores/unobtainium", new OutlineColor(209, 82, 227), false, 0));


        PotionsMaster.blockStore.setStore(BlockStore.getFromSimpleBlockList(list));

    }


}
