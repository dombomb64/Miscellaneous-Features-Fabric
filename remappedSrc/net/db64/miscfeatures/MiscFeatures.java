package net.db64.miscfeatures;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.block.entity.ModBlockEntities;
import net.db64.miscfeatures.effect.ModEffects;
import net.db64.miscfeatures.item.ModFoodComponents;
import net.db64.miscfeatures.item.ModItemGroups;
import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.potion.ModPotions;
import net.db64.miscfeatures.recipe.ModRecipes;
import net.db64.miscfeatures.screen.ModScreenHandlers;
import net.db64.miscfeatures.sound.ModSounds;
import net.db64.miscfeatures.util.ModLootTableModifiers;
import net.db64.miscfeatures.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiscFeatures implements ModInitializer {
	public static final String MOD_ID = "miscfeatures";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.debug("what did mario say to luigi when he couldn't find the tv remote\nholy sh*t luigi\n...well i thought it was funny enough to use in quiplash");

		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModFoodComponents.registerFoodComponents();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModSounds.registerModSounds();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();

		ModWorldGeneration.generateWorldGen();

		ModLootTableModifiers.modifyLootTables();

		//ModMessages.registerC2SPackets();

		addFlammableBlocks();

		PigEntity.BREEDING_INGREDIENT = Ingredient.ofItems(Items.CARROT, Items.POTATO, Items.BEETROOT, ModItems.ANIMAL_FEED);
		ChickenEntity.BREEDING_INGREDIENT = Ingredient.ofItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.TORCHFLOWER_SEEDS, Items.PITCHER_POD, ModItems.ANIMAL_FEED);
	}

	private static void addFlammableBlocks() {
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_RUBBER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_RUBBER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIPPING_RUBBER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIPPING_RUBBER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RAINBOW_EUCALYPTUS_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RAINBOW_EUCALYPTUS_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RAINBOW_EUCALYPTUS_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RAINBOW_EUCALYPTUS_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RAINBOW_EUCALYPTUS_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RAINBOW_EUCALYPTUS_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STEEL_WOOL, 30, 60);
	}
}