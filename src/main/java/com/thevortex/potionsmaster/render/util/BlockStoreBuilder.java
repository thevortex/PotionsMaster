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
        
        // If no config files exist, use default ore list so recipes can be generated
        if (list.isEmpty()) {
            PotionsMaster.LOGGER.info("No ore config files found, using default ore list");
            list.add(new BlockData("coal", "c:ores/coal", 0xFF202020, false, 0,"minecraft:coal"));
            list.add(new BlockData("emerald", "c:ores/emerald", 0xFF00FF00, false, 0,"minecraft:emerald"));
            list.add(new BlockData("lapis", "c:ores/lapis", 0xFF0000FF, false, 0,"minecraft:lapis_lazuli"));
            list.add(new BlockData("redstone", "c:ores/redstone", 0xFFFF0000, false, 0,"minecraft:redstone"));
            list.add(new BlockData("diamond", "c:ores/diamond", 0xFF3DDBE3, false, 0,"minecraft:diamond"));
            list.add(new BlockData("iron", "c:ores/iron", 0xFFE4C0AA, false, 0,"minecraft:raw_iron"));
            list.add(new BlockData("gold", "c:ores/gold", 0xFFD4AF37, false, 0,"minecraft:raw_gold"));
            list.add(new BlockData("netherite", "c:ores/ancient_debris", 0xFFFFA500, false, 0,"minecraft:netherite_scrap"));
            list.add(new BlockData("quartz", "c:ores/nether_quartz", 0xFFFFFFFF, false, 0,"minecraft:quartz"));
            list.add(new BlockData("copper", "c:ores/copper", 0xFFB7703A, false, 0,"minecraft:raw_copper"));
        }

        PotionsMaster.blockStore.setStore(list);
        PotionsMaster.LOGGER.info("BlockStore initialized with " + list.size() + " ores");
    }


}
