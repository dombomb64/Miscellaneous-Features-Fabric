package net.db64.miscfeatures.item;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
	public static final ItemGroup MISCFEATURES_GROUP = Registry.register(Registries.ITEM_GROUP,
		new Identifier(MiscFeatures.MOD_ID, "miscfeatures"),
		FabricItemGroup.builder().displayName(Text.translatable("itemgroup.miscfeatures"))
		.icon(() -> new ItemStack(ModItems.LATEX_BOTTLE)).entries((displayContext, entries) -> {
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

			entries.add(ModBlocks.RUBBER_LEAVES);
			entries.add(ModBlocks.RUBBER_SAPLING);

			entries.add(ModItems.LATEX_BOTTLE);
			entries.add(ModBlocks.RUBBER_BLOCK);

			entries.add(ModBlocks.SPIKE_BLOCK);
			entries.add(ModBlocks.ADVANCED_NOTE_BLOCK);

			entries.add(ModItems.STRIPES_ARMOR_TRIM);

			entries.add(ModBlocks.QUARTZ_SHREDDER);

			entries.add(ModBlocks.STEEL_WOOL);
			entries.add(ModBlocks.BURNT_STEEL_WOOL);
		}).build());

	public static void registerItemGroups() {
		MiscFeatures.LOGGER.debug("Registering item groups for Miscellaneous Features (" + MiscFeatures.MOD_ID + ")");
	}
}
