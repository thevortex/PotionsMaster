package com.thevortex.potionsmaster.init;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

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
import com.thevortex.potionsmaster.render.util.BlockData;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Reference.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Reference.MOD_ID);
    public static final DeferredRegister<MobEffect> MOBEFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Reference.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);



    // Block(s?)
    public static final DeferredBlock<Mortar> MORTAR = BLOCKS.register("tile_mortar", () -> new Mortar());
    // Potions
   
   
    // Items
    /*
    public static final DeferredItem<BasePowder> CHARCOAL_POWDER = ITEMS.register("charcoal_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> COAL_POWDER = ITEMS.register("coal_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> DIAMOND_POWDER = ITEMS.register("diamond_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> EMERALD_POWDER = ITEMS.register("emerald_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> GOLD_POWDER = ITEMS.register("gold_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> IRON_POWDER = ITEMS.register("iron_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> LAPIS_POWDER = ITEMS.register("lapis_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> REDSTONE_POWDER  = ITEMS.register("redstone_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> ALUMINIUM_POWDER = ITEMS.register("aluminium_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> COPPER_POWDER = ITEMS.register("copper_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> TIN_POWDER = ITEMS.register("tin_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> NICKEL_POWDER = ITEMS.register("nickel_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> URANIUM_POWDER = ITEMS.register("uranium_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> LEAD_POWDER = ITEMS.register("lead_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> SILVER_POWDER = ITEMS.register("silver_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> ZINC_POWDER = ITEMS.register("zinc_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> OSMIUM_POWDER = ITEMS.register("osmium_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> QUARTZ_POWDER = ITEMS.register("quartz_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> BISMUTH_POWDER = ITEMS.register("bismuth_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> CRIMSONIRON_POWDER = ITEMS.register("crimsoniron_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> PLATINUM_POWDER = ITEMS.register("platinum_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> ALLTHEMODIUM_POWDER = ITEMS.register("allthemodium_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> VIBRANIUM_POWDER = ITEMS.register("vibranium_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> UNOBTAINIUM_POWDER = ITEMS.register("unobtainium_powder", () -> new BasePowder(new Item.Properties()));
    public static final DeferredItem<BasePowder> NETHERITE_POWDER = ITEMS.register("netherite_powder", () -> new BasePowder(new Item.Properties())); */
 /* public static final DeferredItem<CalcinatedPowder> CALCINATEDCOAL_POWDER = ITEMS.register("calcinatedcoal_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDDIAMOND_POWDER = ITEMS.register("calcinateddiamond_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDEMERALD_POWDER = ITEMS.register("calcinatedemerald_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDGOLD_POWDER = ITEMS.register("calcinatedgold_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDIRON_POWDER = ITEMS.register("calcinatediron_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDLAPIS_POWDER = ITEMS.register("calcinatedlapis_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDREDSTONE_POWDER = ITEMS.register("calcinatedredstone_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDNETHERITE_POWDER = ITEMS.register("calcinatednetherite_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDALUMINIUM_POWDER = ITEMS.register("calcinatedaluminium_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDCOPPER_POWDER = ITEMS.register("calcinatedcopper_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDTIN_POWDER = ITEMS.register("calcinatedtin_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDNICKEL_POWDER = ITEMS.register("calcinatednickel_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDURANIUM_POWDER = ITEMS.register("calcinateduranium_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDLEAD_POWDER = ITEMS.register("calcinatedlead_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDSILVER_POWDER = ITEMS.register("calcinatedsilver_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDZINC_POWDER = ITEMS.register("calcinatedzinc_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDOSMIUM_POWDER = ITEMS.register("calcinatedosmium_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDQUARTZ_POWDER = ITEMS.register("calcinatedquartz_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDBISMUTH_POWDER = ITEMS.register("calcinatedbismuth_powder", () -> new CalcinatedPowder(new Item.Properties()));
    public static final DeferredItem<CalcinatedPowder> CALCINATEDCRIMSONIRON_POWDER = ITEMS.register("calcinatedcrimsoniron_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final DeferredItem<CalcinatedPowder> CALCINATEDPLATINUM_POWDER = ITEMS.register("calcinatedplatinum_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final DeferredItem<CalcinatedPowder> CALCINATEDALLTHEMODIUM_POWDER = ITEMS.register("calcinatedallthemodium_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final DeferredItem<CalcinatedPowder> CALCINATEDVIBRANIUM_POWDER = ITEMS.register("calcinatedvibranium_powder", () -> new CalcinatedPowder(new Item.Properties()));;
    public static final DeferredItem<CalcinatedPowder> CALCINATEDUNOBTAINIUM_POWDER = ITEMS.register("calcinatedunobtainium_powder", () -> new CalcinatedPowder(new Item.Properties()));; */
    public static final DeferredItem<Item> ENDER_POWDER = ITEMS.register("ender_powder", () ->  new Item(new Item.Properties()));

    private static HashMap<String, DeferredHolder<MobEffect,MobEffect>> EffectsListParsed = new HashMap<>();
    public static final List<DeferredHolder<Item,Item>> BaseItemList = registerBaseItems();
    public static final List<DeferredHolder<Item,Item>> CalcinatedItemList = registerCalcinatedItems();
    public static final List<DeferredHolder<MobEffect,MobEffect>> EffectList = registerEffects();
    public static final List<DeferredHolder<Potion,Potion>> PotionList = registerPotions();

    public static final DeferredItem<Bezoar> BEZOAR = ITEMS.register("bezoar",() -> new Bezoar(new Item.Properties().food(ModFoods.BEZOAR)));
    public static final DeferredItem<GallBladder> GALLBLADDER = ITEMS.register("gallbladder",() -> new GallBladder(new Item.Properties().food(ModFoods.GALLBLADDER)));
    public static final DeferredItem<ActivatedCharcoal> ACTIVATEDCHARCOAL = ITEMS.register("activated_charcoal", () -> new ActivatedCharcoal(new Item.Properties().food(ModFoods.ACTIVATEDCHARCOAL)));
    public static final DeferredItem<Pestle> PESTLE = ITEMS.register("pestle",() -> new Pestle(new Item.Properties()));

    public static final DeferredItem<com.thevortex.potionsmaster.items.Mortar> ITEM_MORTAR = ITEMS.register("tile_mortar",() -> new com.thevortex.potionsmaster.items.Mortar(MORTAR.get(), new Item.Properties()));



    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = CREATIVE_TABS.register("creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(Reference.tab()))
            .icon(Items.BREWING_STAND::getDefaultInstance)
            .displayItems((parameters, output) -> ITEMS.getEntries().stream()
                    .map(DeferredHolder::get)
                    .map(Item::getDefaultInstance)
                    .forEach(output::accept))
            .build()
    );

    public static DeferredHolder<Item,Item> createBasePowder(String name, Supplier<BasePowder> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }
    public static DeferredHolder<Item,Item> createCalcinatedPowder(String name, Supplier<CalcinatedPowder> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }
    public static DeferredHolder<MobEffect, MobEffect> createMobEffect(String name, Supplier<OreSightEffect> effectSupplier) {
        return MOBEFFECTS.register(name, effectSupplier);
    }
    public static DeferredHolder<Potion, Potion> createPotion(String name, Supplier<Potion> potionSupplier) {
        return POTIONS.register(name, potionSupplier);
    }

    public static List<DeferredHolder<Item,Item>> registerBaseItems() {
        List<DeferredHolder<Item,Item>> list = new ArrayList<>();
        for(BlockData blockData : PotionsMaster.blockStore.getStore().values()) {
            list.add(createBasePowder(blockData.getEntryName() + "_oresight_powder", () -> new BasePowder(blockData.getColor(),new Item.Properties())));
            
        }
        return list;
    }
    public static List<DeferredHolder<Item,Item>> registerCalcinatedItems() {
        List<DeferredHolder<Item,Item>> list = new ArrayList<>();
        for(BlockData blockData : PotionsMaster.blockStore.getStore().values()) {
            list.add(createCalcinatedPowder("calcinated_" + blockData.getEntryName() + "_oresight_powder", () -> new CalcinatedPowder(blockData.getColor(), new Item.Properties())));
            
        }
        return list;
    }
    public static List<DeferredHolder<MobEffect,MobEffect>> registerEffects() {
        List<DeferredHolder<MobEffect,MobEffect>> list = new ArrayList<>();
        for(BlockData blockData : PotionsMaster.blockStore.getStore().values()) {
            DeferredHolder<MobEffect,MobEffect> effect = createMobEffect(blockData.getEntryName() + "_sight", () -> new OreSightEffect(MobEffectCategory.BENEFICIAL, blockData.getoreTag(), blockData.getColor()));
            list.add(effect);            
            EffectsListParsed.put(blockData.getEntryName(),effect);

        }
        return list;
    }

    public static List<DeferredHolder<Potion,Potion>> registerPotions() {
        List<DeferredHolder<Potion,Potion>> list = new ArrayList<>();
        for(String potionName : EffectsListParsed.keySet()) {
            DeferredHolder<Potion,Potion> potion = createPotion(potionName, () -> new Potion(potionName + "_sight_potion", new MobEffectInstance(EffectsListParsed.get(potionName).getDelegate())));
            list.add(potion);
        }
        return list;
    }
}