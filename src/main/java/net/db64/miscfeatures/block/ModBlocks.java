package net.db64.miscfeatures.block;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.custom.CustomLog;
import net.db64.miscfeatures.block.custom.SpikeBlock;
import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.world.tree.RubberSaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction.Axis;

public class ModBlocks {
	public static final Block RUBBER_LOG = registerBlock("rubber_log",
		createLogBlock(MapColor.OFF_WHITE, MapColor.STONE_GRAY));
	public static final Block RUBBER_WOOD = registerBlock("rubber_wood",
		createLogBlock(MapColor.STONE_GRAY));

	public static final Block STRIPPED_RUBBER_LOG = registerBlock("stripped_rubber_log",
		createLogBlock(MapColor.OFF_WHITE));
	public static final Block STRIPPED_RUBBER_WOOD = registerBlock("stripped_rubber_wood",
		createLogBlock(MapColor.OFF_WHITE));

	public static final Block DRIPPING_RUBBER_LOG = registerBlock("dripping_rubber_log",
		createLogBlock(MapColor.OFF_WHITE));
	public static final Block DRIPPING_RUBBER_WOOD = registerBlock("dripping_rubber_wood",
		createLogBlock(MapColor.OFF_WHITE));

	public static final Block RUBBERWOOD_PLANKS = registerBlock("rubberwood_planks",
		new Block(FabricBlockSettings.create().mapColor(MapColor.OFF_WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));

	public static final Block RUBBERWOOD_STAIRS = registerBlock("rubberwood_stairs",
		new StairsBlock(ModBlocks.RUBBERWOOD_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS)));
	public static final Block RUBBERWOOD_SLAB = registerBlock("rubberwood_slab",
		new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS)));

	public static final Block RUBBERWOOD_FENCE = registerBlock("rubberwood_fence",
		new FenceBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS)));
	public static final Block RUBBERWOOD_FENCE_GATE = registerBlock("rubberwood_fence_gate",
		new FenceGateBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS), WoodType.OAK));

	public static final Block RUBBERWOOD_DOOR = registerBlock("rubberwood_door",
		new DoorBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS), BlockSetType.OAK));
	public static final Block RUBBERWOOD_TRAPDOOR = registerBlock("rubberwood_trapdoor",
		new TrapdoorBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS), BlockSetType.OAK));

	public static final Block RUBBERWOOD_PRESSURE_PLATE = registerBlock("rubberwood_pressure_plate",
		new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS), BlockSetType.OAK));
	public static final Block RUBBERWOOD_BUTTON = registerBlock("rubberwood_button",
		new ButtonBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS), BlockSetType.OAK, 30, true));

	public static final Block RUBBER_LEAVES = registerBlock("rubber_leaves",
		new LeavesBlock(FabricBlockSettings.copyOf(Blocks.CHERRY_LEAVES).mapColor(MapColor.DARK_GREEN)));
	public static final Block RUBBER_SAPLING = registerBlock("rubber_sapling",
		new SaplingBlock(new RubberSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.CHERRY_SAPLING).mapColor(MapColor.DARK_GREEN)));

	public static final Block RUBBER_BLOCK = registerBlock("rubber_block",
		new Block(FabricBlockSettings.copyOf(Blocks.DIRT).velocityMultiplier(0.7f).sounds(BlockSoundGroup.SHROOMLIGHT).mapColor(MapColor.BLACK)));

	public static final Block SPIKE_BLOCK = registerBlock("spike_block",
		new SpikeBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).nonOpaque().requiresTool()));

	private static Block registerBlock(String name, Block block, boolean obtainable) {
		if (obtainable)
			registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new Identifier(MiscFeatures.MOD_ID, name), block);
	}
	
	private static Block registerBlock(String name, Block block) {
		return registerBlock(name, block, true);
	}

	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registries.ITEM, new Identifier(MiscFeatures.MOD_ID, name),
			new BlockItem(block, new FabricItemSettings()));
	}

	public static void registerModBlocks() {
		MiscFeatures.LOGGER.debug("Registering blocks for " + MiscFeatures.MOD_ID);

		((CustomLog)RUBBER_LOG).stripOutcomes.add(STRIPPED_RUBBER_LOG);
		((CustomLog)RUBBER_LOG).stripOutcomes.add(DRIPPING_RUBBER_LOG);
		((CustomLog)RUBBER_LOG).stripChances.add(5f);
		((CustomLog)RUBBER_LOG).stripChances.add(1f);

		((CustomLog)RUBBER_WOOD).stripOutcomes.add(STRIPPED_RUBBER_WOOD);
		((CustomLog)RUBBER_WOOD).stripOutcomes.add(DRIPPING_RUBBER_WOOD);
		((CustomLog)RUBBER_WOOD).stripChances.add(5f);
		((CustomLog)RUBBER_WOOD).stripChances.add(1f);

		((CustomLog)DRIPPING_RUBBER_LOG).bottleItem = ModItems.LATEX_BOTTLE;
		((CustomLog)DRIPPING_RUBBER_LOG).bottleOutcome = ModBlocks.STRIPPED_RUBBER_LOG;

		((CustomLog)DRIPPING_RUBBER_WOOD).bottleItem = ModItems.LATEX_BOTTLE;
		((CustomLog)DRIPPING_RUBBER_WOOD).bottleOutcome = ModBlocks.STRIPPED_RUBBER_WOOD;
	}

	private static CustomLog createLogBlock(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
		return new CustomLog(FabricBlockSettings.create().mapColor((state) -> {
			return state.get(PillarBlock.AXIS) == Axis.Y ? topMapColor : sideMapColor;
			}).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable());
	}

	private static CustomLog createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
		return createLogBlock(topMapColor, sideMapColor, BlockSoundGroup.WOOD);
	}

	private static CustomLog createLogBlock(MapColor mapColor) {
		return createLogBlock(mapColor, BlockSoundGroup.WOOD);
	}
	
	private static CustomLog createLogBlock(MapColor mapColor, BlockSoundGroup soundGroup) {
		return createLogBlock(mapColor, mapColor, soundGroup);
	}
}
