package com.thevortex.potionsmaster.client;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;
import net.minecraft.locale.Language;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Reload listener that injects dynamic language translations at runtime
 */
public class DynamicLanguageProvider implements PreparableReloadListener {

    private static boolean injected = false;

    @Override
    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager,
                                          ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler,
                                          Executor backgroundExecutor, Executor gameExecutor) {
        return preparationBarrier.wait(null).thenRunAsync(() -> {
            injectLanguageEntries();
        }, gameExecutor);
    }

    private void injectLanguageEntries() {
        if (injected) {
            PotionsMaster.LOGGER.info("Dynamic language entries already injected, skipping");
            return;
        }

        PotionsMaster.LOGGER.info("=== Injecting Dynamic Language Entries ===");

        try {
            Language language = Language.getInstance();

            // Try to get the actual implementation instance
            PotionsMaster.LOGGER.info("Language instance class: " + language.getClass().getName());

            // Search ALL fields including private ones in the actual runtime class
            Field storageField = null;
            Class<?> clazz = language.getClass();

            // Check the instance class and its superclasses
            while (clazz != null && storageField == null) {
                PotionsMaster.LOGGER.info("Checking class: " + clazz.getName());
                Field[] fields = clazz.getDeclaredFields();
                PotionsMaster.LOGGER.info("Found " + fields.length + " fields in " + clazz.getSimpleName());

                for (Field field : fields) {
                    field.setAccessible(true);
                    PotionsMaster.LOGGER.info("  Field: " + field.getName() + " Type: " + field.getType().getName());

                    // Look for Map fields
                    if (Map.class.isAssignableFrom(field.getType())) {
                        try {
                            Object value = field.get(language);
                            if (value instanceof Map) {
                                Map<?, ?> map = (Map<?, ?>) value;
                                PotionsMaster.LOGGER.info("    -> Found Map with " + map.size() + " entries");
                                // Check if it's a String->String map (translations)
                                if (!map.isEmpty()) {
                                    Object firstKey = map.keySet().iterator().next();
                                    Object firstValue = map.values().iterator().next();
                                    PotionsMaster.LOGGER.info("    -> Map types: " + firstKey.getClass().getSimpleName() + " -> " + firstValue.getClass().getSimpleName());
                                    if (firstKey instanceof String && firstValue instanceof String) {
                                        storageField = field;
                                        PotionsMaster.LOGGER.info("    -> THIS IS THE TRANSLATION MAP!");
                                        break;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            PotionsMaster.LOGGER.debug("Could not access field " + field.getName(), e);
                        }
                    }
                }

                clazz = clazz.getSuperclass();
            }

            if (storageField == null) {
                PotionsMaster.LOGGER.error("Could not find language storage field after exhaustive search!");
                return;
            }

            storageField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, String> storage = (Map<String, String>) storageField.get(language);

            PotionsMaster.LOGGER.info("Successfully found translation storage with " + storage.size() + " entries");

            // Check if the map is immutable and replace it with a mutable copy
            try {
                storage.put("test.key", "test.value");
                storage.remove("test.key");
                PotionsMaster.LOGGER.info("Map is mutable, can inject directly");
            } catch (UnsupportedOperationException e) {
                PotionsMaster.LOGGER.info("Map is immutable, creating mutable copy...");
                // Create a mutable HashMap with all existing entries plus our new ones
                java.util.HashMap<String, String> mutableStorage = new java.util.HashMap<>(storage);
                storage = mutableStorage;
                // Replace the field with our mutable map
                storageField.set(language, mutableStorage);
                PotionsMaster.LOGGER.info("Replaced immutable map with mutable HashMap");
            }

            // Inject translations for each ore
            int count = 0;
            for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
                String oreName = data.getEntryName();
                String displayName = capitalizeFirstLetter(oreName);

                // Base powder
                storage.put("item." + Reference.MOD_ID + "." + oreName + "_oresight_powder",
                    displayName + " Oresight Powder");

                // Calcinated powder
                storage.put("item." + Reference.MOD_ID + ".calcinated_" + oreName + "_oresight_powder",
                    "Calcinated " + displayName + " Oresight Powder");

                // Effect
                storage.put("effect." + Reference.MOD_ID + "." + oreName + "_sight",
                    displayName + " Sight");

                // Potion variants - all using effect name (ore_sight)
                String effectName = oreName + "_sight_potion";

                // Regular potions
                storage.put("item.minecraft.potion.effect." + effectName,
                    "Potion of " + displayName + " Sight");

                // Splash potions
                storage.put("item.minecraft.splash_potion.effect." + effectName,
                    "Splash Potion of " + displayName + " Sight");

                // Lingering potions
                storage.put("item.minecraft.lingering_potion.effect." + effectName,
                    "Lingering Potion of " + displayName + " Sight");

                // Tipped arrows
                storage.put("item.minecraft.tipped_arrow.effect." + effectName,
                    "Arrow of " + displayName + " Sight");

                count++;
            }

            injected = true;
            PotionsMaster.LOGGER.info("=== Successfully injected " + (count * 7) + " dynamic language entries for " + count + " ores ===");

        } catch (Exception e) {
            PotionsMaster.LOGGER.error("Failed to inject language entries", e);
            e.printStackTrace();
        }
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] parts = input.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                result.append(" ");
            }
            String part = parts[i];
            if (!part.isEmpty()) {
                result.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    result.append(part.substring(1).toLowerCase());
                }
            }
        }

        return result.toString();
    }
}

