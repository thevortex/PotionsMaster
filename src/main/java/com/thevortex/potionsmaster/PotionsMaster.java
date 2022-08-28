package com.thevortex.potionsmaster;

import com.thevortex.potionsmaster.init.*;
import com.thevortex.potionsmaster.items.potions.recipes.oresight.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import com.thevortex.potionsmaster.events.PotionExpiry;
import com.thevortex.potionsmaster.network.PacketHandler;
import com.thevortex.potionsmaster.proxy.ClientProxy;
import com.thevortex.potionsmaster.proxy.CommonProxy;
import com.thevortex.potionsmaster.proxy.ServerProxy;
import com.thevortex.potionsmaster.reference.Reference;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.BlockStoreBuilder;
import com.thevortex.potionsmaster.render.util.xray.Controller;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings("deprecation")
@Mod(Reference.MOD_ID)
public class PotionsMaster {

	public static final String MOD_ID = Reference.MOD_ID;
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final CreativeModeTab GROUP = new CreativeModeTab(MOD_ID) {
		public ItemStack makeIcon() {
			return new ItemStack(Blocks.BREWING_STAND);
		}
	};
	public static BlockStore blockStore = new BlockStore();

	public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public PotionsMaster() {
		ModRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModRegistry.MOBEFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModRegistry.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());

		BlockStoreBuilder.init();
		FMLJavaModLoadingContext.get().getModEventBus().register(setupMod.class);
		MinecraftForge.EVENT_BUS.register(PlayerEvents.class);
		MinecraftForge.EVENT_BUS.register(PotionExpiry.class);
		//MinecraftForge.EVENT_BUS.register(PotionRemoved.class);
	}

	public static ResourceLocation getId(String pathIn) {
		return new ResourceLocation(MOD_ID, pathIn);
	}



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
					Ingredient.of(ModRegistry.CALCINATEDCOAL_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.COAL_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new IronPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDIRON_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.IRON_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new RedStonePotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDREDSTONE_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.REDSTONE_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new LapisPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDLAPIS_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.LAPIS_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new GoldPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDGOLD_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.GOLD_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new DiamondPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDDIAMOND_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.DIAMOND_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new EmeraldPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDEMERALD_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.EMERALD_SIGHT)));

			BrewingRecipeRegistry.addRecipe(new AluminiumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDALUMINIUM_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ALUMINIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new CopperPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDCOPPER_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.COPPER_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new TinPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDTIN_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.TIN_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new NickelPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDNICKEL_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.NICKEL_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new UraniumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDURANIUM_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.URANIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new LeadPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDLEAD_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.LEAD_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new SilverPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDSILVER_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.SILVER_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new ZincPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDZINC_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ZINC_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new OsmiumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDOSMIUM_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.OSMIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new QuartzPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDQUARTZ_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.QUARTZ_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new BismuthPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDBISMUTH_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.BISMUTH_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new CrimsonIronPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDCRIMSONIRON_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.CRIMSONIRON_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new PlatinumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDPLATINUM_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.PLATINUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new AllthemodiumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDALLTHEMODIUM_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ALLTHEMODIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new VibraniumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDVIBRANIUM_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.VIBRANIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new UnobtainiumPotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDUNOBTAINIUM_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.UNOBTAINIUM_SIGHT)));
			BrewingRecipeRegistry.addRecipe(new NetheritePotionRecipe(
					Ingredient
							.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)),
					Ingredient.of(ModRegistry.CALCINATEDNETHERITE_POWDER.get()),
					PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.NETHERITE_SIGHT)));


		}

	}




}
