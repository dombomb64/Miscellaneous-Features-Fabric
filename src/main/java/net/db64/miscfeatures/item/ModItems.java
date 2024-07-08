package net.db64.miscfeatures.item;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.item.custom.HorriblyMisspelledCheeseburgerItem;
import net.db64.miscfeatures.item.custom.InstantDeathPotionItem;
import net.db64.miscfeatures.item.custom.RainbowSawdustItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
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
	public static Item RAINBOW_SAWDUST;
	public static final Item ANIMAL_FEED = registerItem("animal_feed",
		new Item(new FabricItemSettings()));
	public static Item HORRIBLY_MISSPELLED_CHEESEBURGER;
	public static final Item WARPED_WART = registerItem("warped_wart",
		new AliasedBlockItem(ModBlocks.WARPED_WART, new FabricItemSettings()));
	public static final Item INSTANT_DEATH_POTION = registerItem("instant_death_potion",
		new InstantDeathPotionItem(new FabricItemSettings().maxCount(1)));
	public static final Item SHROOMLIGHT_SPORES = registerItem("shroomlight_spores",
		new AliasedBlockItem(ModBlocks.SHROOMLIGHT_SPORES, new FabricItemSettings()));
	public static final Item CRIMSON_SPORES = registerItem("crimson_spores",
		new AliasedBlockItem(ModBlocks.CRIMSON_SPORES, new FabricItemSettings()));
	public static final Item WARPED_SPORES = registerItem("warped_spores",
		new AliasedBlockItem(ModBlocks.WARPED_SPORES, new FabricItemSettings()));

	private static Item registerItem(String name, Item item) {
		MiscFeatures.LOGGER.debug("Registering item " + MiscFeatures.MOD_ID + ":" + name);
		return Registry.register(Registries.ITEM, new Identifier(MiscFeatures.MOD_ID, name), item);
	}

	public static void registerModItems() {
		MiscFeatures.LOGGER.debug("Registering items for Miscellaneous Features (" + MiscFeatures.MOD_ID + ")");

		RAINBOW_SAWDUST = registerItem("rainbow_sawdust",
			new RainbowSawdustItem(new FabricItemSettings().food(ModFoodComponents.RAINBOW_SAWDUST)));
		HORRIBLY_MISSPELLED_CHEESEBURGER = registerItem("horribly_misspelled_cheeseburger",
			new HorriblyMisspelledCheeseburgerItem(new FabricItemSettings().food(ModFoodComponents.HORRIBLY_MISSPELLED_CHEESEBURGER).maxDamage(100)));

		MiscFeatures.LOGGER.debug("Registering vanilla item groups for Miscellaneous Features (" + MiscFeatures.MOD_ID + ")");

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(VanillaItemGroups::addItemsToBuildingBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(VanillaItemGroups::addItemsToColoredBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(VanillaItemGroups::addItemsToNaturalBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(VanillaItemGroups::addItemsToFunctionalBlocksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(VanillaItemGroups::addItemsToRedstoneBlocksItemGroup);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(VanillaItemGroups::addItemsToToolsAndUtilitiesItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(VanillaItemGroups::addItemsToCombatItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(VanillaItemGroups::addItemsToFoodAndDrinksItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(VanillaItemGroups::addItemsToIngredientsItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(VanillaItemGroups::addItemsToSpawnEggsItemGroup);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(VanillaItemGroups::addItemsToOperatorUtilitiesItemGroup);
	}

	public static ItemStack getInstantDeathPotion(boolean good) {
		var nbt = new NbtCompound();
		nbt.put("miscfeatures:data", new NbtCompound());
		nbt.getCompound("miscfeatures:data").putBoolean(InstantDeathPotionItem.IS_EFFECT_GOOD_KEY, good);

		var item = new ItemStack(ModItems.INSTANT_DEATH_POTION, 1);
		item.setNbt(nbt);

		return item;
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

		public static void addItemsToColoredBlocksItemGroup(FabricItemGroupEntries entries) {
		}

		public static void addItemsToNaturalBlocksItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModBlocks.RUBBER_LOG);
			entries.add(ModBlocks.RUBBER_LEAVES);
			entries.add(ModBlocks.RUBBER_SAPLING);

			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_LOG);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING);

			entries.add(ModBlocks.ICICLE);
		}

		public static void addItemsToFunctionalBlocksItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModBlocks.QUARTZ_SHREDDER);

			entries.add(ModItems.SHROOMLIGHT_SPORES);
			entries.add(ModItems.CRIMSON_SPORES);
			entries.add(ModItems.WARPED_SPORES);

			entries.add(ModBlocks.ICICLE);
		}

		public static void addItemsToRedstoneBlocksItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModBlocks.SPIKE_BLOCK);
			entries.add(ModBlocks.ADVANCED_NOTE_BLOCK);
		}

		public static void addItemsToToolsAndUtilitiesItemGroup(FabricItemGroupEntries entries) {
		}

		public static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
		}

		public static void addItemsToFoodAndDrinksItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModItems.RAINBOW_SAWDUST);
			// The cheeseburger is so rare and overpowered that it's not even in the normal creative inventory

			entries.add(ModItems.getInstantDeathPotion(true));
			entries.add(ModItems.getInstantDeathPotion(false));
		}

		public static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModItems.LATEX_BOTTLE);

			entries.add(ModItems.STRIPES_ARMOR_TRIM);

			entries.add(ModItems.ANIMAL_FEED);

			entries.add(ModItems.WARPED_WART);
		}

		public static void addItemsToSpawnEggsItemGroup(FabricItemGroupEntries entries) {
		}

		public static void addItemsToOperatorUtilitiesItemGroup(FabricItemGroupEntries entries) {
			entries.add(ModItems.HORRIBLY_MISSPELLED_CHEESEBURGER);
		}
	}
}
