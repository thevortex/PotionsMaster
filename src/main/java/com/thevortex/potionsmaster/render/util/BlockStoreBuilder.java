package com.thevortex.potionsmaster.render.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Ores;
import com.thevortex.potionsmaster.reference.Reference;



public class BlockStoreBuilder {


    public static ArrayList<BlockData> list = new ArrayList<BlockData>();

    public static void init() {


        list.add(new BlockData("CoalOre", Ores.COAL.getNamespace() + ":" + Ores.COAL.getPath(), new OutlineColor(82, 82, 82), false, 0));
        list.add(new BlockData("IronOre", Ores.IRON.getNamespace() + ":" + Ores.IRON.getPath(), new OutlineColor(228, 192, 170), false, 0));
        list.add(new BlockData("CopperOre", Ores.COPPER.getNamespace() + ":" + Ores.COPPER.getPath(), new OutlineColor(183, 112, 58), false, 0));
        list.add(new BlockData("RedstoneOre", Ores.REDSTONE.getNamespace() + ":" + Ores.REDSTONE.getPath(), new OutlineColor(255, 0, 0), false, 0));
        list.add(new BlockData("LapisOre", Ores.LAPIS.getNamespace() + ":" + Ores.LAPIS.getPath(), new OutlineColor(10, 10, 255), false, 0));
        list.add(new BlockData("GoldOre", Ores.GOLD.getNamespace() + ":" + Ores.GOLD.getPath(), new OutlineColor(212, 175, 55), false, 0));
        list.add(new BlockData("DiamondOre", Ores.DIAMOND.getNamespace() + ":" + Ores.DIAMOND.getPath(), new OutlineColor(61, 219, 227), false, 0));
        list.add(new BlockData("EmeraldOre", Ores.EMERALD.getNamespace() + ":" + Ores.EMERALD.getPath(), new OutlineColor(0, 255, 0), false, 0));

        list.add(new BlockData("AluminumOre", Ores.ALUMINIUM.getNamespace() + ":" + Ores.ALUMINIUM.getPath(), new OutlineColor(227, 227, 227), false, 0));
        list.add(new BlockData("TinOre", Ores.TIN.getNamespace() + ":" + Ores.TIN.getPath(), new OutlineColor(120, 120, 120), false, 0));
        list.add(new BlockData("SilverOre", Ores.SILVER.getNamespace() + ":" + Ores.SILVER.getPath(), new OutlineColor(164, 224, 231), false, 0));
        list.add(new BlockData("LeadOre", Ores.LEAD.getNamespace() + ":" + Ores.LEAD.getPath(), new OutlineColor(124, 140, 198), false, 0));
        list.add(new BlockData("NickelOre", Ores.NICKEL.getNamespace() + ":" + Ores.NICKEL.getPath(), new OutlineColor(169, 169, 132), false, 0));
        list.add(new BlockData("UraniumOre", Ores.URANIUM.getNamespace() + ":" + Ores.URANIUM.getPath(), new OutlineColor(126, 231, 120), false, 0));
        list.add(new BlockData("ZincOre", Ores.ZINC.getNamespace() + ":" + Ores.ZINC.getPath(), new OutlineColor(181, 181, 117), false, 0));
        list.add(new BlockData("OsmiumOre", Ores.OSMIUM.getNamespace() + ":" + Ores.OSMIUM.getPath(), new OutlineColor(192, 201, 221), false, 0));
        list.add(new BlockData("BismuthOre", Ores.BISMUTH.getNamespace() + ":" + Ores.BISMUTH.getPath(), new OutlineColor(181, 181, 181), false, 0));
        list.add(new BlockData("CrimsonIronOre", Ores.CRIMSONIRON.getNamespace() + ":" + Ores.CRIMSONIRON.getPath(), new OutlineColor(255, 192, 170), false, 0));
        list.add(new BlockData("NetherQuartzOre", Ores.QUARTZ.getNamespace() + ":" + Ores.QUARTZ.getPath(), new OutlineColor(255, 255, 255), false, 0));
        list.add(new BlockData("PlatinumOre", Ores.PLATINUM.getNamespace() + ":" + Ores.PLATINUM.getPath(), new OutlineColor(181, 181, 255), false, 0));
        list.add(new BlockData("NetheriteOre", Ores.NETHERITE.getNamespace() + ":" + Ores.NETHERITE.getPath(), new OutlineColor(255, 165, 0), false, 0));
        list.add(new BlockData("AllthemodiumOre", Ores.ALLTHEMODIUM.getNamespace() + ":" + Ores.ALLTHEMODIUM.getPath(), new OutlineColor(254, 217, 90), false, 0));
        list.add(new BlockData("VibraniumOre", Ores.VIBRANIUM.getNamespace() + ":" + Ores.VIBRANIUM.getPath(), new OutlineColor(38, 222, 136), false, 0));
        list.add(new BlockData("UnobtainiumOre", Ores.UNOBTAINIUM.getNamespace() + ":" + Ores.UNOBTAINIUM.getPath(), new OutlineColor(209, 82, 227), false, 0));


        PotionsMaster.blockStore.setStore(list);

    }


}
