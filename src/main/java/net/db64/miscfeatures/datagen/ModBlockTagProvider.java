package net.db64.miscfeatures.datagen;

import java.util.concurrent.CompletableFuture;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(WrapperLookup arg) {
		getOrCreateTagBuilder(ModTags.Blocks.RUBBER_LOGS)
			.add(ModBlocks.RUBBER_LOG)
			.add(ModBlocks.RUBBER_WOOD)
			.add(ModBlocks.STRIPPED_RUBBER_LOG)
			.add(ModBlocks.STRIPPED_RUBBER_WOOD)
			.add(ModBlocks.DRIPPING_RUBBER_LOG)
			.add(ModBlocks.DRIPPING_RUBBER_WOOD);

		getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
			.forceAddTag(ModTags.Blocks.RUBBER_LOGS);

		getOrCreateTagBuilder(BlockTags.PLANKS)
			.add(ModBlocks.RUBBERWOOD_PLANKS);

		getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
			.add(ModBlocks.RUBBERWOOD_STAIRS);
		getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
			.add(ModBlocks.RUBBERWOOD_SLAB);

		getOrCreateTagBuilder(BlockTags.FENCES)
			.add(ModBlocks.RUBBERWOOD_FENCE);
		getOrCreateTagBuilder(BlockTags.FENCE_GATES)
			.add(ModBlocks.RUBBERWOOD_FENCE_GATE);

		getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
			.add(ModBlocks.RUBBERWOOD_DOOR);
		getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
			.add(ModBlocks.RUBBERWOOD_TRAPDOOR);

		getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
			.add(ModBlocks.RUBBERWOOD_PRESSURE_PLATE);
		getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
			.add(ModBlocks.RUBBERWOOD_BUTTON);

		getOrCreateTagBuilder(BlockTags.LEAVES)
			.add(ModBlocks.RUBBER_LEAVES);
		getOrCreateTagBuilder(BlockTags.SAPLINGS)
			.add(ModBlocks.RUBBER_SAPLING);

		//getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
			//.add(ModBlocks.RUBBERWOOD_PLANKS);

		//getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
			//.add(ModBlocks.RUBBERWOOD_PLANKS);

		getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
			.add(ModBlocks.SPIKE_BLOCK);

		getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
			.add(ModBlocks.RUBBER_BLOCK);

		//getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
			//.add(ModBlocks.RUBBERWOOD_PLANKS);

		//getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
			//.add(ModBlocks.RUBBERWOOD_PLANKS);

		//getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
			//.add(ModBlocks.RUBBERWOOD_PLANKS);

		//getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
			//.add(ModBlocks.RUBBERWOOD_PLANKS);
	}
}
