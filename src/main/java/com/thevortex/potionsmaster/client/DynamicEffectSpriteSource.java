package com.thevortex.potionsmaster.client;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.serialization.MapCodec;
import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;
import net.minecraft.client.resources.metadata.animation.FrameSize;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceMetadata;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.IOException;
import java.util.Optional;

/**
 * Custom sprite source that generates dynamic mob effect icon sprites at runtime
 * This works the same way as the powder model generation - dynamically creating assets
 */
public class DynamicEffectSpriteSource implements SpriteSource {

    public static final MapCodec<DynamicEffectSpriteSource> CODEC = MapCodec.unit(DynamicEffectSpriteSource::new);
    public static final SpriteSourceType TYPE = new SpriteSourceType(CODEC);

    // Keep baseTexture alive across the entire sprite source lifecycle
    private static NativeImage baseTexture = null;

    /**
     * Register this sprite source type with the given event
     */
    @SuppressWarnings("deprecation")
    public static void registerSpriteSourceType(net.neoforged.neoforge.client.event.RegisterSpriteSourceTypesEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "dynamic_effect"), CODEC);
    }

    @Override
    public void run(ResourceManager resourceManager, Output output) {
        PotionsMaster.LOGGER.info("=== Running Dynamic Effect Sprite Source ===");

        // Load base texture
        baseTexture = loadBaseTexture(resourceManager);
        if (baseTexture == null) {
            PotionsMaster.LOGGER.error("Failed to load base effect texture - cannot generate dynamic sprites");
            return;
        }

        // Generate sprite for each effect
        for (BlockData data : PotionsMaster.blockStore.getStore().values()) {
            String effectName = data.getEntryName() + "_sight";
            ResourceLocation spriteLocation = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, effectName);

            try {
                // Generate colored texture
                final NativeImage coloredTexture = applyColorTint(baseTexture, data.getColor());
                final ResourceLocation finalSpriteLocation = spriteLocation;

                // Create sprite contents and register with output
                // Output.add() accepts ResourceLocation and SpriteSource.SpriteSupplier
                // SpriteSupplier is Function<SpriteResourceLoader, SpriteContents>
                output.add(spriteLocation, (spriteResourceLoader) -> new SpriteContents(
                    finalSpriteLocation,
                    new FrameSize(coloredTexture.getWidth(), coloredTexture.getHeight()),
                    coloredTexture,
                    ResourceMetadata.EMPTY
                ));

                PotionsMaster.LOGGER.info("Generated dynamic sprite for effect: " + effectName + " with color 0x" + Integer.toHexString(data.getColor()));

            } catch (Exception e) {
                PotionsMaster.LOGGER.error("Failed to generate sprite for " + effectName, e);
            }
        }

        PotionsMaster.LOGGER.info("=== Dynamic Effect Sprite Source Complete - Generated " + PotionsMaster.blockStore.getStore().size() + " effect icons ===");
    }

    private NativeImage loadBaseTexture(ResourceManager resourceManager) {
        try {
            ResourceLocation baseLocation = ResourceLocation.fromNamespaceAndPath(
                Reference.MOD_ID,
                "textures/mob_effect/basepotioneffect.png"
            );

            Optional<Resource> resource = resourceManager.getResource(baseLocation);
            if (resource.isPresent()) {
                NativeImage image = NativeImage.read(resource.get().open());
                PotionsMaster.LOGGER.info("Loaded base effect texture: " + image.getWidth() + "x" + image.getHeight());
                return image;
            } else {
                PotionsMaster.LOGGER.error("Base effect texture not found at: " + baseLocation);
            }
        } catch (IOException e) {
            PotionsMaster.LOGGER.error("Failed to load base effect texture", e);
        }
        return null;
    }

    private NativeImage applyColorTint(NativeImage base, int color) {
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

                // Apply color multiplication (blend mode) - same as powder items
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

    @Override
    public SpriteSourceType type() {
        return TYPE;
    }
}

