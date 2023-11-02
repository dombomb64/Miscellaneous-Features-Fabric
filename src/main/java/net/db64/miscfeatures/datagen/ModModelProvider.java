package net.db64.miscfeatures.datagen;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerLog(ModBlocks.RUBBER_LOG).log(ModBlocks.RUBBER_LOG).wood(ModBlocks.RUBBER_WOOD);
		blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_RUBBER_LOG).log(ModBlocks.STRIPPED_RUBBER_LOG).wood(ModBlocks.STRIPPED_RUBBER_WOOD);
		blockStateModelGenerator.registerLog(ModBlocks.DRIPPING_RUBBER_LOG).log(ModBlocks.DRIPPING_RUBBER_LOG).wood(ModBlocks.DRIPPING_RUBBER_WOOD);

		BlockStateModelGenerator.BlockTexturePool rubberwoodPlanksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBBERWOOD_PLANKS);
		rubberwoodPlanksPool.stairs(ModBlocks.RUBBERWOOD_STAIRS);
		rubberwoodPlanksPool.slab(ModBlocks.RUBBERWOOD_SLAB);
		rubberwoodPlanksPool.fence(ModBlocks.RUBBERWOOD_FENCE);
		rubberwoodPlanksPool.fenceGate(ModBlocks.RUBBERWOOD_FENCE_GATE);
		blockStateModelGenerator.registerDoor(ModBlocks.RUBBERWOOD_DOOR);
		blockStateModelGenerator.registerTrapdoor(ModBlocks.RUBBERWOOD_TRAPDOOR);
		rubberwoodPlanksPool.pressurePlate(ModBlocks.RUBBERWOOD_PRESSURE_PLATE);
		rubberwoodPlanksPool.button(ModBlocks.RUBBERWOOD_BUTTON);

		blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBBER_LEAVES);
		blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.RUBBER_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
		blockStateModelGenerator.registerItemModel(ModBlocks.RUBBER_SAPLING); // So that the sapling is 2D in the inventory

		blockStateModelGenerator.registerLog(ModBlocks.RAINBOW_EUCALYPTUS_LOG).log(ModBlocks.RAINBOW_EUCALYPTUS_LOG).wood(ModBlocks.RAINBOW_EUCALYPTUS_WOOD);
		blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_LOG).log(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_LOG).wood(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_WOOD);

		BlockStateModelGenerator.BlockTexturePool rainbowEucalyptusPlanksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS);
		rainbowEucalyptusPlanksPool.stairs(ModBlocks.RAINBOW_EUCALYPTUS_STAIRS);
		rainbowEucalyptusPlanksPool.slab(ModBlocks.RAINBOW_EUCALYPTUS_SLAB);
		rainbowEucalyptusPlanksPool.fence(ModBlocks.RAINBOW_EUCALYPTUS_FENCE);
		rainbowEucalyptusPlanksPool.fenceGate(ModBlocks.RAINBOW_EUCALYPTUS_FENCE_GATE);
		blockStateModelGenerator.registerDoor(ModBlocks.RAINBOW_EUCALYPTUS_DOOR);
		blockStateModelGenerator.registerTrapdoor(ModBlocks.RAINBOW_EUCALYPTUS_TRAPDOOR);
		rainbowEucalyptusPlanksPool.pressurePlate(ModBlocks.RAINBOW_EUCALYPTUS_PRESSURE_PLATE);
		rainbowEucalyptusPlanksPool.button(ModBlocks.RAINBOW_EUCALYPTUS_BUTTON);

		blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES);
		blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
		blockStateModelGenerator.registerItemModel(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING); // So that the sapling is 2D in the inventory

		blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBBER_BLOCK);

		// The spike block uses custom models

		blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ADVANCED_NOTE_BLOCK);

		blockStateModelGenerator.registerNorthDefaultHorizontalRotated(ModBlocks.QUARTZ_SHREDDER, TexturedModel.ORIENTABLE);

		blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.STEEL_WOOL);
		blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BURNT_STEEL_WOOL);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ModItems.LATEX_BOTTLE, Models.GENERATED);
		itemModelGenerator.register(ModItems.STRIPES_ARMOR_TRIM, Models.GENERATED);
		itemModelGenerator.register(ModItems.RAINBOW_SAWDUST, Models.GENERATED);
		itemModelGenerator.register(ModItems.ANIMAL_FEED, Models.GENERATED);
	}
}
