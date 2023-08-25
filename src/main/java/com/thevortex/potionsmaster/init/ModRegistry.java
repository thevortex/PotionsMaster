package com.thevortex.potionsmaster.init;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.blocks.Mortar;
import com.thevortex.potionsmaster.items.Bezoar;
import com.thevortex.potionsmaster.items.GallBladder;
import com.thevortex.potionsmaster.items.Pestle;
import com.thevortex.potionsmaster.items.potions.effect.oresight.OreSightEffect;
import com.thevortex.potionsmaster.items.powders.base.BasePowder;
import com.thevortex.potionsmaster.items.powders.base.CalcinatedPowder;
import com.thevortex.potionsmaster.items.powders.calcinated.ActivatedCharcoal;
import com.thevortex.potionsmaster.reference.Ores;
import com.thevortex.potionsmaster.reference.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Reference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Reference.MOD_ID);
    public static final DeferredRegister<MobEffect> MOBEFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Reference.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);



    // Block(s?)
    public static final RegistryObject<Block> MORTAR = BLOCKS.register("tile_mortar", () -> new Mortar());

    // Items

    public static final RegistryObject<Item> CHARCOAL_POWDER = ITEMS.register("charcoal_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> COAL_POWDER = ITEMS.register("coal_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_POWDER = ITEMS.register("diamond_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_POWDER = ITEMS.register("emerald_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_POWDER = ITEMS.register("gold_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> IRON_POWDER = ITEMS.register("iron_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> LAPIS_POWDER = ITEMS.register("lapis_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_POWDER  = ITEMS.register("redstone_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_POWDER = ITEMS.register("aluminium_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_POWDER = ITEMS.register("copper_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> TIN_POWDER = ITEMS.register("tin_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> NICKEL_POWDER = ITEMS.register("nickel_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> URANIUM_POWDER = ITEMS.register("uranium_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> LEAD_POWDER = ITEMS.register("lead_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_POWDER = ITEMS.register("silver_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> ZINC_POWDER = ITEMS.register("zinc_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> OSMIUM_POWDER = ITEMS.register("osmium_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> QUARTZ_POWDER = ITEMS.register("quartz_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> BISMUTH_POWDER = ITEMS.register("bismuth_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> CRIMSONIRON_POWDER = ITEMS.register("crimsoniron_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_POWDER = ITEMS.register("platinum_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> ALLTHEMODIUM_POWDER = ITEMS.register("allthemodium_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> VIBRANIUM_POWDER = ITEMS.register("vibranium_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> UNOBTAINIUM_POWDER = ITEMS.register("unobtainium_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_POWDER = ITEMS.register("netherite_powder", () -> new BasePowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDCOAL_POWDER = ITEMS.register("calcinatedcoal_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDDIAMOND_POWDER = ITEMS.register("calcinateddiamond_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDEMERALD_POWDER = ITEMS.register("calcinatedemerald_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDGOLD_POWDER = ITEMS.register("calcinatedgold_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDIRON_POWDER = ITEMS.register("calcinatediron_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDLAPIS_POWDER = ITEMS.register("calcinatedlapis_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDREDSTONE_POWDER = ITEMS.register("calcinatedredstone_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDNETHERITE_POWDER = ITEMS.register("calcinatednetherite_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDALUMINIUM_POWDER = ITEMS.register("calcinatedaluminium_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDCOPPER_POWDER = ITEMS.register("calcinatedcopper_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDTIN_POWDER = ITEMS.register("calcinatedtin_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDNICKEL_POWDER = ITEMS.register("calcinatednickel_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDURANIUM_POWDER = ITEMS.register("calcinateduranium_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDLEAD_POWDER = ITEMS.register("calcinatedlead_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDSILVER_POWDER = ITEMS.register("calcinatedsilver_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDZINC_POWDER = ITEMS.register("calcinatedzinc_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDOSMIUM_POWDER = ITEMS.register("calcinatedosmium_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDQUARTZ_POWDER = ITEMS.register("calcinatedquartz_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDBISMUTH_POWDER = ITEMS.register("calcinatedbismuth_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final RegistryObject<Item> CALCINATEDCRIMSONIRON_POWDER = ITEMS.register("calcinatedcrimsoniron_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final RegistryObject<Item> CALCINATEDPLATINUM_POWDER = ITEMS.register("calcinatedplatinum_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final RegistryObject<Item> CALCINATEDALLTHEMODIUM_POWDER = ITEMS.register("calcinatedallthemodium_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final RegistryObject<Item> CALCINATEDVIBRANIUM_POWDER = ITEMS.register("calcinatedvibranium_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final RegistryObject<Item> CALCINATEDUNOBTAINIUM_POWDER = ITEMS.register("calcinatedunobtainium_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final RegistryObject<Item> ENDER_POWDER = ITEMS.register("ender_powder", () ->  new BasePowder(new Item.Properties()));


    public static final RegistryObject<Item> BEZOAR = ITEMS.register("bezoar",() -> new Bezoar(new Item.Properties().food(ModFoods.BEZOAR)));
    public static final RegistryObject<Item> GALLBLADDER = ITEMS.register("gallbladder",() -> new GallBladder(new Item.Properties().food(ModFoods.GALLBLADDER)));
    public static final RegistryObject<Item> ACTIVATEDCHARCOAL = ITEMS.register("activated_charcoal", () -> new ActivatedCharcoal(new Item.Properties().food(ModFoods.ACTIVATEDCHARCOAL)));
    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle",() -> new Pestle(new Item.Properties()));

    public static final RegistryObject<Item> ITEM_MORTAR = ITEMS.register("tile_mortar",() -> new com.thevortex.potionsmaster.items.Mortar(MORTAR.get(), new Item.Properties()));

    // Potions

    public static final RegistryObject<Potion> COAL_SIGHT = POTIONS.register("coal_sight",  () -> ModPotions.COAL_SIGHT);
    public static final RegistryObject<Potion> IRON_SIGHT = POTIONS.register("iron_sight", () -> ModPotions.IRON_SIGHT);
    public static final RegistryObject<Potion> REDSTONE_SIGHT = POTIONS.register("redstone_sight", () -> ModPotions.REDSTONE_SIGHT);
    public static final RegistryObject<Potion> LAPIS_SIGHT = POTIONS.register("lapis_sight" ,() -> ModPotions.LAPIS_SIGHT);
    public static final RegistryObject<Potion> GOLD_SIGHT = POTIONS.register("gold_sight",() -> ModPotions.GOLD_SIGHT);
    public static final RegistryObject<Potion> DIAMOND_SIGHT = POTIONS.register("diamond_sight",() -> ModPotions.DIAMOND_SIGHT);
    public static final RegistryObject<Potion> EMERALD_SIGHT = POTIONS.register("emerald_sight",() -> ModPotions.EMERALD_SIGHT);

    public static final RegistryObject<Potion> ALUMINIUM_SIGHT = POTIONS.register("aluminum_sight",() -> ModPotions.ALUMINIUM_SIGHT);
    public static final RegistryObject<Potion> COPPER_SIGHT  = POTIONS.register("copper_sight",() -> ModPotions.COPPER_SIGHT);
    public static final RegistryObject<Potion> TIN_SIGHT = POTIONS.register("tin_sight",() -> ModPotions.TIN_SIGHT);
    public static final RegistryObject<Potion> NICKEL_SIGHT = POTIONS.register("nickel_sight",() -> ModPotions.NICKEL_SIGHT);
    public static final RegistryObject<Potion> URANIUM_SIGHT = POTIONS.register("uranium_sight",() -> ModPotions.URANIUM_SIGHT);
    public static final RegistryObject<Potion> LEAD_SIGHT = POTIONS.register("lead_sight",() -> ModPotions.LEAD_SIGHT);
    public static final RegistryObject<Potion> SILVER_SIGHT = POTIONS.register("silver_sight",() -> ModPotions.SILVER_SIGHT);
    public static final RegistryObject<Potion> ZINC_SIGHT = POTIONS.register("zinc_sight",() -> ModPotions.ZINC_SIGHT);
    public static final RegistryObject<Potion> OSMIUM_SIGHT = POTIONS.register("osmium_sight",() -> ModPotions.OSMIUM_SIGHT);
    public static final RegistryObject<Potion> QUARTZ_SIGHT = POTIONS.register("quartz_sight",() -> ModPotions.QUARTZ_SIGHT);
    public static final RegistryObject<Potion> BISMUTH_SIGHT = POTIONS.register("bismuth_sight",() -> ModPotions.BISMUTH_SIGHT);
    public static final RegistryObject<Potion> CRIMSONIRON_SIGHT = POTIONS.register("crimsoniron_sight",() -> ModPotions.CRIMSONIRON_SIGHT);
    public static final RegistryObject<Potion> PLATINUM_SIGHT = POTIONS.register("platinum_sight",() -> ModPotions.PLATINUM_SIGHT);
    public static final RegistryObject<Potion> NETHERITE_SIGHT = POTIONS.register("netherite_sight",() -> ModPotions.NETHERITE_SIGHT);
    public static final RegistryObject<Potion> ALLTHEMODIUM_SIGHT = POTIONS.register("allthemodium_sight",() -> ModPotions.ALLTHEMODIUM_SIGHT);
    public static final RegistryObject<Potion> VIBRANIUM_SIGHT = POTIONS.register("vibranium_sight",() -> ModPotions.VIBRANIUM_SIGHT);
    public static final RegistryObject<Potion> UNOBTAINIUM_SIGHT = POTIONS.register("unobtainium_sight",() -> ModPotions.UNOBTAINIUM_SIGHT);


    // Mob Effects

    public static RegistryObject<MobEffect> COALSIGHT = MOBEFFECTS.register("coalpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.COAL.toString(), 65793));
    public static RegistryObject<MobEffect> IRONSIGHT= MOBEFFECTS.register("ironpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.IRON.toString(), 14991530));
    public static RegistryObject<MobEffect> REDSTONESIGHT= MOBEFFECTS.register("redstonepotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.REDSTONE.toString() ,16711680));
    public static RegistryObject<MobEffect> LAPISSIGHT= MOBEFFECTS.register("lapispotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.LAPIS.toString(), 658175));
    public static RegistryObject<MobEffect> GOLDSIGHT= MOBEFFECTS.register("goldpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.GOLD.toString(),13938487));
    public static RegistryObject<MobEffect> DIAMONDSIGHT= MOBEFFECTS.register("diamondpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.DIAMOND.toString(),4053987));
    public static RegistryObject<MobEffect> EMERALDSIGHT= MOBEFFECTS.register("emeraldpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.EMERALD.toString(),65280));

    public static RegistryObject<MobEffect> ALUMINIUMSIGHT= MOBEFFECTS.register("aluminiumpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.ALUMINIUM.toString(),14935011));
    public static RegistryObject<MobEffect> COPPERSIGHT= MOBEFFECTS.register("copperpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.COPPER.toString(),12021818));
    public static RegistryObject<MobEffect> TINSIGHT= MOBEFFECTS.register("tinpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.TIN.toString(),7895160));
    public static RegistryObject<MobEffect> NICKELSIGHT= MOBEFFECTS.register("nickelpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.NICKEL.toString(),11118980));
    public static RegistryObject<MobEffect> URANIUMSIGHT= MOBEFFECTS.register("uraniumpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.URANIUM.toString(),8316792));
    public static RegistryObject<MobEffect> LEADSIGHT= MOBEFFECTS.register("leadpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.LEAD.toString(),8162502));
    public static RegistryObject<MobEffect> SILVERSIGHT= MOBEFFECTS.register("silverpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.SILVER.toString(),10805479));
    public static RegistryObject<MobEffect> ZINCSIGHT= MOBEFFECTS.register("zincpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.ZINC.toString(),11908469));
    public static RegistryObject<MobEffect> OSMIUMSIGHT= MOBEFFECTS.register("osmiumpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.OSMIUM.toString(),12634589));
    public static RegistryObject<MobEffect> QUARTZSIGHT= MOBEFFECTS.register("quartzpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.QUARTZ.toString(),8162502));
    public static RegistryObject<MobEffect> BISMUTHSIGHT= MOBEFFECTS.register("bismuthpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.BISMUTH.toString(),10805479));
    public static RegistryObject<MobEffect> CRIMSONIRONSIGHT= MOBEFFECTS.register("crimsonironpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.CRIMSONIRON.toString(),11908469));
    public static RegistryObject<MobEffect> PLATINUMSIGHT= MOBEFFECTS.register("platinumpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.PLATINUM.toString(),12634589));
    public static RegistryObject<MobEffect> NETHERITESIGHT= MOBEFFECTS.register("netheritepotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.NETHERITE.toString(),16753920));
    public static RegistryObject<MobEffect> ALLTHEMODIUMSIGHT= MOBEFFECTS.register("allthemodiumpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.ALLTHEMODIUM.toString(), 16701786));
    public static RegistryObject<MobEffect> VIBRANIUMSIGHT= MOBEFFECTS.register("vibraniumpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.VIBRANIUM.toString(),2547336));
    public static RegistryObject<MobEffect> UNOBTAINIUMSIGHT= MOBEFFECTS.register("unobtainiumpotioneffect", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, Ores.UNOBTAINIUM.toString(),13718243));


    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_TABS.register("creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(Reference.tab()))
            .icon(() -> Items.BREWING_STAND.getDefaultInstance())
            .displayItems((parameters, output) -> ITEMS.getEntries().stream()
                    .map(RegistryObject::get)
                    .map(Item::getDefaultInstance)
                    .forEach(output::accept))
            .build()
    );

}