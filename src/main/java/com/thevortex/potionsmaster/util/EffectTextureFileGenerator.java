package com.thevortex.potionsmaster.util;

import com.mojang.blaze3d.platform.NativeImage;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Generates mob effect icon textures dynamically at runtime and registers them to the texture manager.
 * Works similarly to the dynamic powder item textures.
 */
public class EffectTextureFileGenerator {

    private static final Map<String, ResourceLocation> registeredTextures = new HashMap<>();
    private static NativeImage cachedBaseTexture = null;

    /**
     * Generates and registers all mob effect icon textures to the texture manager
     */
    public static void generateAllEffectTextures() {
        PotionsMaster.LOGGER.info("=== Generating Dynamic Mob Effect Icon Textures ===");

        // Load base texture once
        NativeImage baseTexture = loadBaseTexture();
        if (baseTexture == null) {
            PotionsMaster.LOGGER.error("Failed to load base texture - cannot generate effect icons");
            return;
        }

        // Generate texture for each effect
        int successCount = 0;
        for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
            String effectName = data.getEntryName() + "_sight";

            if (generateEffectTexture(effectName, data.getColor(), baseTexture)) {
                successCount++;
            }
        }

        PotionsMaster.LOGGER.info("=== Generated " + successCount + " dynamic effect icon textures ===");
    }

    /**
     * Generates and registers a single effect texture
     */
    private static boolean generateEffectTexture(String effectName, int color, NativeImage baseTexture) {
        try {
            // Generate colored texture
            NativeImage coloredTexture = applyColorTint(baseTexture, color);

            // Create DynamicTexture from the NativeImage
            DynamicTexture dynamicTexture = new DynamicTexture(coloredTexture);

            // Register to texture manager with the location Minecraft expects for mob effects
            // Minecraft looks for mob effect textures in the mob_effects atlas
            // But we register them directly to the texture manager
            ResourceLocation textureLocation = ResourceLocation.fromNamespaceAndPath(
                Reference.MOD_ID,
                "textures/mob_effect/" + effectName + ".png"
            );

            Minecraft.getInstance().getTextureManager().register(textureLocation, dynamicTexture);
            registeredTextures.put(effectName, textureLocation);

            PotionsMaster.LOGGER.info("Registered dynamic texture for effect: " + effectName + " at " + textureLocation);
            return true;

        } catch (Exception e) {
            PotionsMaster.LOGGER.error("Failed to generate texture for " + effectName, e);
            return false;
        }
    }

    /**
     * Loads the base potion effect texture from resources
     */
    private static NativeImage loadBaseTexture() {
        if (cachedBaseTexture != null) {
            return cachedBaseTexture;
        }

        try {
            ResourceLocation baseLocation = ResourceLocation.fromNamespaceAndPath(
                Reference.MOD_ID,
                "textures/mob_effect/basepotioneffect.png"
            );

            var resourceManager = Minecraft.getInstance().getResourceManager();
            var resource = resourceManager.getResource(baseLocation);

            if (resource.isPresent()) {
                try (InputStream stream = resource.get().open()) {
                    cachedBaseTexture = NativeImage.read(stream);
                    PotionsMaster.LOGGER.info("Loaded base potion effect texture: " +
                        cachedBaseTexture.getWidth() + "x" + cachedBaseTexture.getHeight());
                    return cachedBaseTexture;
                }
            } else {
                PotionsMaster.LOGGER.error("Base potion effect texture not found at: " + baseLocation);
            }
        } catch (IOException e) {
            PotionsMaster.LOGGER.error("Failed to load base texture", e);
        }

        return null;
    }

    /**
     * Applies color tint to a texture using multiplication blend mode
     */
    private static NativeImage applyColorTint(NativeImage base, int color) {
        // Create new image with same dimensions
        NativeImage tinted = new NativeImage(base.getWidth(), base.getHeight(), true);

        // Extract color components (ARGB format)
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        // Apply tint to each pixel
        for (int y = 0; y < base.getHeight(); y++) {
            for (int x = 0; x < base.getWidth(); x++) {
                int pixel = base.getPixelRGBA(x, y);

                // Extract RGBA components
                int origR = pixel & 0xFF;
                int origG = (pixel >> 8) & 0xFF;
                int origB = (pixel >> 16) & 0xFF;
                int a = (pixel >> 24) & 0xFF;

                // Apply color multiplication (blend mode)
                int newR = (origR * r) / 255;
                int newG = (origG * g) / 255;
                int newB = (origB * b) / 255;

                // Set pixel in RGBA format
                int newPixel = (a << 24) | (newB << 16) | (newG << 8) | newR;
                tinted.setPixelRGBA(x, y, newPixel);
            }
        }

        return tinted;
    }

    /**
     * Gets the registered texture location for an effect
     */
    public static ResourceLocation getEffectTextureLocation(String effectName) {
        return registeredTextures.get(effectName);
    }

    /**
     * Cleanup method to free resources
     */
    public static void cleanup() {
        if (cachedBaseTexture != null) {
            cachedBaseTexture.close();
            cachedBaseTexture = null;
        }
        registeredTextures.clear();
    }
}

