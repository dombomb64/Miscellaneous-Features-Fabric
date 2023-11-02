package net.db64.miscfeatures.item;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.item.custom.RainbowSawdust;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.trim.ArmorTrimPatterns;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item LATEX_BOTTLE = registerItem("latex_bottle",
		new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE)));
	public static final Item STRIPES_ARMOR_TRIM = registerItem("stripes_armor_trim",
		SmithingTemplateItem.of(RegistryKey.of(RegistryKeys.TRIM_PATTERN, new Identifier(MiscFeatures.MOD_ID, "stripes"))));
	public static final Item RAINBOW_SAWDUST = registerItem("rainbow_sawdust",
		new RainbowSawdust(new FabricItemSettings().food(ModFoodComponents.RAINBOW_SAWDUST)));
	public static final Item ANIMAL_FEED = registerItem("animal_feed",
		new Item(new FabricItemSettings()));

	private static Item registerItem(String name, Item item) {
		MiscFeatures.LOGGER.debug("Registering item " + MiscFeatures.MOD_ID + ":" + name);
		return Registry.register(Registries.ITEM, new Identifier(MiscFeatures.MOD_ID, name), item);
	}

	public static void registerModItems() {
		MiscFeatures.LOGGER.debug("Registering vanilla item groups for Miscellaneous Features (" + MiscFeatures.MOD_ID + ")");

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(VanillaItemGroups::addItemsToBuildingBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(VanillaItemGroups::addItemsToNaturalBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(VanillaItemGroups::addItemsToFunctionalBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(VanillaItemGroups::addItemsToRedstoneItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(VanillaItemGroups::addItemsToFoodAndDrinkItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(VanillaItemGroups::addItemsToIngredientsItemGroup);
	}

	private static class VanillaItemGroups {
		public static void addItemsToBuildingBlocksItemGroup(FabricItemGroupEntries entries) {
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

			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_LOG);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_WOOD);
			entries.add(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_LOG);
			entries.add(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_WOOD);

			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_STAIRS);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_SLAB);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_FENCE);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_FENCE_GATE);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_DOOR);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_TRAPDOOR);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_PRESSURE_PLATE);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_BUTTON);

			entries.add(ModBlocks.STEEL_WOOL);
			entries.add(ModBlocks.BURNT_STEEL_WOOL);
		}

		public static void addItemsToNaturalBlocksItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModBlocks.RUBBER_LOG);
			entries.add(ModBlocks.RUBBER_LEAVES);
			entries.add(ModBlocks.RUBBER_SAPLING);

			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_LOG);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		}

		public static void addItemsToFunctionalBlocksItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModBlocks.QUARTZ_SHREDDER);
		}

		public static void addItemsToRedstoneItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModBlocks.SPIKE_BLOCK);
			entries.add(ModBlocks.ADVANCED_NOTE_BLOCK);
		}

		public static void addItemsToFoodAndDrinkItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModItems.RAINBOW_SAWDUST);
		}

		public static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModItems.LATEX_BOTTLE);
			entries.add(ModItems.STRIPES_ARMOR_TRIM);
			entries.add(ModItems.ANIMAL_FEED);
		}
	}
}
