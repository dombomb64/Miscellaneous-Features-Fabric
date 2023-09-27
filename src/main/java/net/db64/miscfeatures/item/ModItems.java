package net.db64.miscfeatures.item;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item LATEX_BOTTLE = registerItem("latex_bottle", new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE)));

	private static Item registerItem(String name, Item item) {
		MiscFeatures.LOGGER.debug("Registering item " + MiscFeatures.MOD_ID + ":" + name);
		return Registry.register(Registries.ITEM, new Identifier(MiscFeatures.MOD_ID, name), item);
	}

	public static void registerModItems() {
		MiscFeatures.LOGGER.debug("Registering vanilla item groups for Miscellaneous Features (" + MiscFeatures.MOD_ID + ")");

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBuildingBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemsToNaturalBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(ModItems::addItemsToRedstoneItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientsItemGroup);
	}

	private static void addItemsToBuildingBlocksItemGroup(FabricItemGroupEntries entries) {
		entries.add(ModBlocks.RUBBER_LOG);
		entries.add(ModBlocks.RUBBER_WOOD);
		entries.add(ModBlocks.STRIPPED_RUBBER_LOG);
		entries.add(ModBlocks.STRIPPED_RUBBER_WOOD);
		entries.add(ModBlocks.DRIPPING_RUBBER_LOG);
		entries.add(ModBlocks.DRIPPING_RUBBER_WOOD);

		entries.add(ModBlocks.RUBBERWOOD_PLANKS);
		entries.add(ModBlocks.RUBBERWOOD_STAIRS);
		entries.add(ModBlocks.RUBBERWOOD_SLAB);
		entries.add(ModBlocks.RUBBERWOOD_FENCE);
		entries.add(ModBlocks.RUBBERWOOD_FENCE_GATE);
		entries.add(ModBlocks.RUBBERWOOD_DOOR);
		entries.add(ModBlocks.RUBBERWOOD_TRAPDOOR);
		entries.add(ModBlocks.RUBBERWOOD_PRESSURE_PLATE);
		entries.add(ModBlocks.RUBBERWOOD_BUTTON);
	}

	private static void addItemsToNaturalBlocksItemGroup(FabricItemGroupEntries entries) {
		entries.add(ModBlocks.RUBBER_LOG);
		entries.add(ModBlocks.RUBBER_LEAVES);
		entries.add(ModBlocks.RUBBER_SAPLING);
	}
	
	private static void addItemsToRedstoneItemGroup(FabricItemGroupEntries entries) {
		entries.add(ModBlocks.SPIKE_BLOCK);
	}

	private static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
		entries.add(ModItems.LATEX_BOTTLE);
	}
}
