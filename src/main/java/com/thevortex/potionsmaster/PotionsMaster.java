package com.thevortex.potionsmaster;

import com.thevortex.potionsmaster.items.potions.recipes.oresight.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thevortex.potionsmaster.events.BlockBreak;
import com.thevortex.potionsmaster.events.PotionExpiry;
import com.thevortex.potionsmaster.init.ModBlocks;
import com.thevortex.potionsmaster.init.ModEntity;
import com.thevortex.potionsmaster.init.ModItems;
import com.thevortex.potionsmaster.init.ModPotionEffects;
import com.thevortex.potionsmaster.init.ModPotions;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.proxy.ClientProxy;
import com.thevortex.potionsmaster.proxy.CommonProxy;
import com.thevortex.potionsmaster.proxy.ServerProxy;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.BlockStoreBuilder;
import com.thevortex.potionsmaster.render.util.xray.Controller;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("deprecation")
@Mod(Reference.MOD_ID)
public class PotionsMaster {

	public static final String MOD_ID = Reference.MOD_ID;
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final ItemGroup GROUP = new ItemGroup(MOD_ID) {
		public ItemStack makeIcon() {
			return new ItemStack(Blocks.BREWING_STAND);
		}
	};
	public static BlockStore blockStore = new BlockStore();
	;
	public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public PotionsMaster() {
		BlockStoreBuilder.init();
		FMLJavaModLoadingContext.get().getModEventBus().register(setupMod.class);
		MinecraftForge.EVENT_BUS.register(PlayerEvents.class);
		MinecraftForge.EVENT_BUS.register(PotionExpiry.class);
		MinecraftForge.EVENT_BUS.register(BlockBreak.class);
		MinecraftForge.EVENT_BUS.addListener(this::onExit);
		//MinecraftForge.EVENT_BUS.register(PotionRemoved.class);
	}

	public static ResourceLocation getId(String pathIn) {
		return new ResourceLocation(MOD_ID, pathIn);
	}

	private void onExit(FMLServerStoppingEvent event) {
		if ((Controller.drawOres())) {
			Controller.toggleDrawOres();
		}
		Controller.shutdownExecutor();
	}

	public static class PlayerEvents {
		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public void onPlayerLogOut(PlayerLoggedOutEvent event) {
			if (Controller.drawOres()) {
				Controller.toggleDrawOres();
			}
			Controller.shutdownExecutor();
		}

	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MOD_ID)
	public static class setupMod {
		@SubscribeEvent
		public static void setup(final FMLCommonSetupEvent event) {

			registerPotions();
			proxy.init();

			PacketHandler.register();

		}


		private static void registerPotions() {
			BrewingRecipeRegistry.addRecipe(new CoalPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDCOAL_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.COAL_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new IronPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDIRON_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.IRON_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new RedStonePotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDREDSTONE_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.REDSTONE_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new LapisPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDLAPIS_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.LAPIS_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new GoldPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDGOLD_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.GOLD_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new DiamondPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDDIAMOND_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.DIAMOND_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new EmeraldPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDEMERALD_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.EMERALD_SIGHT)));

			BrewingRecipeRegistry.addRecipe(new AluminiumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDALUMINIUM_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ALUMINIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new CopperPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDCOPPER_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.COPPER_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new TinPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDTIN_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.TIN_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new NickelPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDNICKEL_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.NICKEL_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new UraniumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDURANIUM_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.URANIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new LeadPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDLEAD_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.LEAD_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new SilverPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDSILVER_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.SILVER_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new ZincPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDZINC_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ZINC_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new OsmiumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDOSMIUM_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.OSMIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new QuartzPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDQUARTZ_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.QUARTZ_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new BismuthPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDBISMUTH_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.BISMUTH_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new CrimsonIronPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDCRIMSONIRON_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.CRIMSONIRON_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new PlatinumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDPLATINUM_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.PLATINUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new AllthemodiumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDALLTHEMODIUM_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ALLTHEMODIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new VibraniumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDVIBRANIUM_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.VIBRANIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new UnobtainiumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDUNOBTAINIUM_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.UNOBTAINIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new NetheritePotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModItems.CALCINATEDNETHERITE_POWDER),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.NETHERITE_SIGHT)));
		}

	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MOD_ID)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
			ModBlocks.init(event);
		}

		@SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {

			ModItems.init(event);
		}

		@SubscribeEvent
		public static void onPotionsRegistry(final RegistryEvent.Register<Potion> event) {
			ModPotions.init(event);
		}

		@SubscribeEvent
		public static void onEffectsRegistry(final RegistryEvent.Register<Effect> event) {

			ModPotionEffects.init(event);

		}

		@SubscribeEvent
		public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
			ModEntity.init(event);
		}

	}


}
