package com.thevortex.potionsmaster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thevortex.potionsmaster.events.PotionExpiry;
import com.thevortex.potionsmaster.init.ModRegistry;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.proxy.ClientProxy;
import com.thevortex.potionsmaster.proxy.CommonProxy;
import com.thevortex.potionsmaster.proxy.ServerProxy;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockData;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.BlockStoreBuilder;
import com.thevortex.potionsmaster.render.util.xray.Controller;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;


@SuppressWarnings("deprecation")
@Mod(Reference.MOD_ID)
public class PotionsMaster {

	public static final String MOD_ID = Reference.MOD_ID;
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static BlockStore blockStore = new BlockStore();

	public static CommonProxy proxy;

	public PotionsMaster(IEventBus eventBus, ModContainer container, Dist dist) {
		BlockStoreBuilder.init();
		ModRegistry.BLOCKS.register(eventBus);
		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.MOBEFFECTS.register(eventBus);
		ModRegistry.POTIONS.register(eventBus);
		ModRegistry.CREATIVE_TABS.register(eventBus);
	

		eventBus.register(setupMod.class);
		eventBus.addListener(PacketHandler::register);

		NeoForge.EVENT_BUS.register(PotionExpiry.class);
		NeoForge.EVENT_BUS.addListener(setupMod::registerPotions);

		if (dist.isClient()) {
			PotionsMaster.proxy = new ClientProxy();
		} else {
			PotionsMaster.proxy = new ServerProxy();
		}
	}

	public static ResourceLocation getId(String pathIn) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, pathIn);
	}


	@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT, modid = MOD_ID)
	public static class PlayerEvents {
		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public static void onPlayerLogOut(PlayerLoggedOutEvent event) {
			if (Controller.drawOres()) {
				Controller.toggleDrawOres();
			}
			Controller.shutdownExecutor();
		}

	}

	@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
	public static class setupMod {
		@SubscribeEvent
		public static void setup(final FMLCommonSetupEvent event) {
			proxy.init();
		}
		private static TagKey<Item> getTagKey(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
		}
		private static void registerPotions(RegisterBrewingRecipesEvent event) {
			for (String name : ModRegistry.EffectsListParsed.keySet()) {
				event.getBuilder().addRecipe(Ingredient.of(getPotion(Potions.MUNDANE)),
				 Ingredient.of(getTagKey("calcinated/" + name)),
				  PotionContents.createItemStack(Items.POTION, ModRegistry.PotionsListParsed.get(name)));
			}

		/* 	event.getBuilder().addRecipe(new CoalPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDCOAL_POWDER.get()),
					getPotion(ModRegistry.COAL_SIGHT)));
			event.getBuilder().addRecipe(new IronPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDIRON_POWDER.get()),
					getPotion(ModRegistry.IRON_SIGHT)));
			event.getBuilder().addRecipe(new RedStonePotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDREDSTONE_POWDER.get()),
					getPotion(ModRegistry.REDSTONE_SIGHT)));
			event.getBuilder().addRecipe(new LapisPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDLAPIS_POWDER.get()),
					getPotion(ModRegistry.LAPIS_SIGHT)));
			event.getBuilder().addRecipe(new GoldPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDGOLD_POWDER.get()),
					getPotion(ModRegistry.GOLD_SIGHT)));
			event.getBuilder().addRecipe(new DiamondPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDDIAMOND_POWDER.get()),
					getPotion(ModRegistry.DIAMOND_SIGHT)));
			event.getBuilder().addRecipe(new EmeraldPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDEMERALD_POWDER.get()),
					getPotion(ModRegistry.EMERALD_SIGHT)));

			event.getBuilder().addRecipe(new AluminiumPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDALUMINIUM_POWDER.get()),
					getPotion(ModRegistry.ALUMINIUM_SIGHT)));
			event.getBuilder().addRecipe(new CopperPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDCOPPER_POWDER.get()),
					getPotion(ModRegistry.COPPER_SIGHT)));
			event.getBuilder().addRecipe(new TinPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDTIN_POWDER.get()),
					getPotion(ModRegistry.TIN_SIGHT)));
			event.getBuilder().addRecipe(new NickelPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDNICKEL_POWDER.get()),
					getPotion(ModRegistry.NICKEL_SIGHT)));
			event.getBuilder().addRecipe(new UraniumPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDURANIUM_POWDER.get()),
					getPotion(ModRegistry.URANIUM_SIGHT)));
			event.getBuilder().addRecipe(new LeadPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDLEAD_POWDER.get()),
					getPotion(ModRegistry.LEAD_SIGHT)));
			event.getBuilder().addRecipe(new SilverPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDSILVER_POWDER.get()),
					getPotion(ModRegistry.SILVER_SIGHT)));
			event.getBuilder().addRecipe(new ZincPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDZINC_POWDER.get()),
					getPotion(ModRegistry.ZINC_SIGHT)));
			event.getBuilder().addRecipe(new OsmiumPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDOSMIUM_POWDER.get()),
					getPotion(ModRegistry.OSMIUM_SIGHT)));
			event.getBuilder().addRecipe(new QuartzPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDQUARTZ_POWDER.get()),
					getPotion(ModRegistry.QUARTZ_SIGHT)));
			event.getBuilder().addRecipe(new BismuthPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDBISMUTH_POWDER.get()),
					getPotion(ModRegistry.BISMUTH_SIGHT)));
			event.getBuilder().addRecipe(new CrimsonIronPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDCRIMSONIRON_POWDER.get()),
					getPotion(ModRegistry.CRIMSONIRON_SIGHT)));
			event.getBuilder().addRecipe(new PlatinumPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDPLATINUM_POWDER.get()),
					getPotion(ModRegistry.PLATINUM_SIGHT)));
			event.getBuilder().addRecipe(new AllthemodiumPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDALLTHEMODIUM_POWDER.get()),
					getPotion(ModRegistry.ALLTHEMODIUM_SIGHT)));
			event.getBuilder().addRecipe(new VibraniumPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDVIBRANIUM_POWDER.get()),
					getPotion(ModRegistry.VIBRANIUM_SIGHT)));
			event.getBuilder().addRecipe(new UnobtainiumPotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDUNOBTAINIUM_POWDER.get()),
					getPotion(ModRegistry.UNOBTAINIUM_SIGHT)));
			event.getBuilder().addRecipe(new NetheritePotionRecipe(
					Ingredient.of(getPotion(Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDNETHERITE_POWDER.get()),
					getPotion(ModRegistry.NETHERITE_SIGHT)));*/
		}
		
		private static ItemStack getPotion(Holder<Potion> potion) {
			ItemStack itemstack = Items.POTION.getDefaultInstance();
			itemstack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
			return itemstack;
		}

	}



}

