package net.db64.miscfeatures.datagen;

import java.util.concurrent.CompletableFuture;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(WrapperLookup arg) {
		getOrCreateTagBuilder(ModTags.Items.RUBBER_LOGS)
			.add(ModBlocks.RUBBER_LOG.asItem())
			.add(ModBlocks.RUBBER_WOOD.asItem())
			.add(ModBlocks.STRIPPED_RUBBER_LOG.asItem())
			.add(ModBlocks.STRIPPED_RUBBER_WOOD.asItem())
			.add(ModBlocks.DRIPPING_RUBBER_LOG.asItem())
			.add(ModBlocks.DRIPPING_RUBBER_WOOD.asItem());

		getOrCreateTagBuilder(ModTags.Items.RAINBOW_EUCALYPTUS_LOGS)
			.add(ModBlocks.RAINBOW_EUCALYPTUS_LOG.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_WOOD.asItem())
			.add(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_LOG.asItem())
			.add(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_WOOD.asItem());

		getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
			.add(ModItems.STRIPES_ARMOR_TRIM);

		getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
			.addTag(ModTags.Items.RUBBER_LOGS)
			.addTag(ModTags.Items.RAINBOW_EUCALYPTUS_LOGS);

		getOrCreateTagBuilder(ItemTags.PLANKS)
			.add(ModBlocks.RUBBERWOOD_PLANKS.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS.asItem());

		getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
			.add(ModBlocks.RUBBERWOOD_STAIRS.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_STAIRS.asItem());
		getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
			.add(ModBlocks.RUBBERWOOD_SLAB.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_SLAB.asItem());

		getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
			.add(ModBlocks.RUBBERWOOD_FENCE.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_FENCE.asItem());
		getOrCreateTagBuilder(ItemTags.FENCE_GATES)
			.add(ModBlocks.RUBBERWOOD_FENCE_GATE.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_FENCE_GATE.asItem());

		getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
			.add(ModBlocks.RUBBERWOOD_DOOR.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_DOOR.asItem());
		getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
			.add(ModBlocks.RUBBERWOOD_TRAPDOOR.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_TRAPDOOR.asItem());

		getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
			.add(ModBlocks.RUBBERWOOD_PRESSURE_PLATE.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_PRESSURE_PLATE.asItem());
		getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
			.add(ModBlocks.RUBBERWOOD_BUTTON.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_BUTTON.asItem());

		getOrCreateTagBuilder(ItemTags.LEAVES)
			.add(ModBlocks.RUBBER_LEAVES.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES.asItem());
		getOrCreateTagBuilder(ItemTags.SAPLINGS)
			.add(ModBlocks.RUBBER_SAPLING.asItem())
			.add(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING.asItem());
	}
}
