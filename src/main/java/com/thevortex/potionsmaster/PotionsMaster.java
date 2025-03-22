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
import net.minecraft.world.entity.player.Player;
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
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
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

		
		}
		
		private static ItemStack getPotion(Holder<Potion> potion) {
			ItemStack itemstack = Items.POTION.getDefaultInstance();
			itemstack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
			return itemstack;
		}

	}



}

