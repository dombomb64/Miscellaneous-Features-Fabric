package net.db64.miscfeatures.datagen;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Thickness;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

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

		blockStateModelGenerator.registerCrop(ModBlocks.WARPED_WART, Properties.AGE_3, 0, 1, 1, 2);

		// The nether spores use custom models

		registerIcicle(blockStateModelGenerator);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ModItems.LATEX_BOTTLE, Models.GENERATED);
		itemModelGenerator.register(ModItems.STRIPES_ARMOR_TRIM, Models.GENERATED);
		itemModelGenerator.register(ModItems.RAINBOW_SAWDUST, Models.GENERATED);
		itemModelGenerator.register(ModItems.ANIMAL_FEED, Models.GENERATED);
		itemModelGenerator.register(ModItems.HORRIBLY_MISSPELLED_CHEESEBURGER, Models.GENERATED);
		// The instant death potion uses custom models
		itemModelGenerator.register(ModItems.SHROOMLIGHT_SPORES, Models.GENERATED);
		itemModelGenerator.register(ModItems.CRIMSON_SPORES, Models.GENERATED);
		itemModelGenerator.register(ModItems.WARPED_SPORES, Models.GENERATED);
		itemModelGenerator.register(ModBlocks.ICICLE.asItem(), Models.GENERATED);
	}

	private void registerIcicle(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.excludeFromSimpleItemModelGeneration(ModBlocks.ICICLE);
		BlockStateVariantMap.DoubleProperty<Direction, Thickness> doubleProperty = BlockStateVariantMap.create(Properties.VERTICAL_DIRECTION, Properties.THICKNESS);
		Thickness[] var2 = Thickness.values();
		int var3 = var2.length;

		int var4;
		Thickness thickness;
		for(var4 = 0; var4 < var3; ++var4) {
			thickness = var2[var4];
			doubleProperty.register(Direction.UP, thickness, getIcicleVariant(Direction.UP, thickness, blockStateModelGenerator));
		}

		var2 = Thickness.values();
		var3 = var2.length;

		for(var4 = 0; var4 < var3; ++var4) {
			thickness = var2[var4];
			doubleProperty.register(Direction.DOWN, thickness, getIcicleVariant(Direction.DOWN, thickness, blockStateModelGenerator));
		}

		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.ICICLE).coordinate(doubleProperty));
	}

	public final BlockStateVariant getIcicleVariant(Direction direction, Thickness thickness, BlockStateModelGenerator blockStateModelGenerator) {
		String var10000 = direction.asString();
		String string = "_" + var10000 + "_" + thickness.asString();
		TextureMap textureMap = TextureMap.cross(TextureMap.getSubId(ModBlocks.ICICLE, string));
		return BlockStateVariant.create().put(VariantSettings.MODEL, Models.POINTED_DRIPSTONE.upload(ModBlocks.ICICLE, string, textureMap, blockStateModelGenerator.modelCollector));
	}
}
