package com.thevortex.potionsmaster.render.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.nio.file.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import com.google.gson.stream.JsonReader;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.init.ModRegistry;
import com.thevortex.potionsmaster.items.potions.effect.oresight.OreSightEffect;
import com.thevortex.potionsmaster.items.powders.base.BasePowder;
import com.thevortex.potionsmaster.items.powders.base.CalcinatedPowder;
import com.thevortex.potionsmaster.reference.Ores;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.Item;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import javax.annotation.Nullable;


public class BlockStoreBuilder {


    public static ArrayList<BlockData> list = new ArrayList<BlockData>();


    public static List<BlockData> scanFolder(Path folderPath) {
        List<BlockData> blockDataList = new ArrayList<>();
        File folderFile = folderPath.toFile();
        if (!folderFile.exists()) {
            boolean result = folderFile.mkdirs();
            if (!result) {
                PotionsMaster.LOGGER.error("Couldn't create folder {}", folderPath);
            }
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath, "*.json")) {
            for (Path entry : stream) {
                BlockData blockData = readJsonFile(entry);
                blockDataList.add(blockData);
            }
        } catch (IOException e) {
            PotionsMaster.LOGGER.error("Failed to read config folder", e);
        }
        return blockDataList;
    }

    private static @Nullable BlockData readJsonFile(Path filePath) {
        try (Reader reader = new FileReader(filePath.toFile())) {
            return new Gson().fromJson(reader, BlockData.class);
        } catch (IOException e) {
            PotionsMaster.LOGGER.error("Failed to read JSON file", e);
        }
        return null;
    }

    public static void loadPotions(Path folderPath) {
        
        List<BlockData> blockDataList = scanFolder(folderPath);
        list.addAll(blockDataList);
        PotionsMaster.blockStore.setStore(list);
    }

    public static void init() {
        Path folderPath = FMLPaths.CONFIGDIR.get().resolve(PotionsMaster.MOD_ID);
        loadPotions(folderPath);
        
/* 
        list.add(new BlockData("CoalOre", Ores.COAL.toString(), new OutlineColor(32, 32, 32), false, 0));
        list.add(new BlockData("IronOre", Ores.IRON.toString(), new OutlineColor(228, 192, 170), false, 0));
        list.add(new BlockData("CopperOre", Ores.COPPER.toString(), new OutlineColor(183, 112, 58), false, 0));
        list.add(new BlockData("RedstoneOre", Ores.REDSTONE.toString(), new OutlineColor(255, 0, 0), false, 0));
        list.add(new BlockData("LapisOre", Ores.LAPIS.toString(), new OutlineColor(10, 10, 255), false, 0));
        list.add(new BlockData("GoldOre", Ores.GOLD.toString(), new OutlineColor(212, 175, 55), false, 0));
        list.add(new BlockData("DiamondOre", Ores.DIAMOND.toString(), new OutlineColor(61, 219, 227), false, 0));
        list.add(new BlockData("EmeraldOre", Ores.EMERALD.toString(), new OutlineColor(0, 255, 0), false, 0));

        list.add(new BlockData("AluminumOre", Ores.ALUMINIUM.toString(), new OutlineColor(227, 227, 227), false, 0));
        list.add(new BlockData("TinOre", Ores.TIN.toString(), new OutlineColor(120, 120, 120), false, 0));
        list.add(new BlockData("SilverOre", Ores.SILVER.toString(), new OutlineColor(164, 224, 231), false, 0));
        list.add(new BlockData("LeadOre", Ores.LEAD.toString(), new OutlineColor(124, 140, 198), false, 0));
        list.add(new BlockData("NickelOre", Ores.NICKEL.toString(), new OutlineColor(169, 169, 132), false, 0));
        list.add(new BlockData("UraniumOre", Ores.URANIUM.toString(), new OutlineColor(126, 231, 120), false, 0));
        list.add(new BlockData("ZincOre", Ores.ZINC.toString(), new OutlineColor(181, 181, 117), false, 0));
        list.add(new BlockData("OsmiumOre", Ores.OSMIUM.toString(), new OutlineColor(192, 201, 221), false, 0));
        list.add(new BlockData("BismuthOre", Ores.BISMUTH.toString(), new OutlineColor(181, 181, 181), false, 0));
        list.add(new BlockData("CrimsonIronOre", Ores.CRIMSONIRON.toString(), new OutlineColor(255, 192, 170), false, 0));
        list.add(new BlockData("NetherQuartzOre", Ores.QUARTZ.toString(), new OutlineColor(255, 255, 255), false, 0));
        list.add(new BlockData("PlatinumOre", Ores.PLATINUM.toString(), new OutlineColor(181, 181, 255), false, 0));
        list.add(new BlockData("NetheriteOre", Ores.NETHERITE.toString(), new OutlineColor(255, 165, 0), false, 0));
        list.add(new BlockData("AllthemodiumOre", Ores.ALLTHEMODIUM.toString(), new OutlineColor(254, 217, 90), false, 0));
        list.add(new BlockData("VibraniumOre", Ores.VIBRANIUM.toString(), new OutlineColor(38, 222, 136), false, 0));
        list.add(new BlockData("UnobtainiumOre", Ores.UNOBTAINIUM.toString(), new OutlineColor(209, 82, 227), false, 0));

*/
    

    }


}
