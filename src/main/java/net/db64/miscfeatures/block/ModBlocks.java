package net.db64.miscfeatures.block;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.custom.*;
import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.sound.ModSounds;
import net.db64.miscfeatures.world.tree.RainbowEucalyptusSaplingGenerator;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction.Axis;

public class ModBlocks {
	public static final Block RUBBER_LOG = registerBlock("rubber_log",
		createLogBlock(MapColor.OFF_WHITE, MapColor.STONE_GRAY, ModSounds.BlockSoundGroups.RUBBER_WOOD));
	public static final Block RUBBER_WOOD = registerBlock("rubber_wood",
		createLogBlock(MapColor.STONE_GRAY, ModSounds.BlockSoundGroups.RUBBER_WOOD));

	public static final Block STRIPPED_RUBBER_LOG = registerBlock("stripped_rubber_log",
		createLogBlock(MapColor.OFF_WHITE, ModSounds.BlockSoundGroups.RUBBER_WOOD));
	public static final Block STRIPPED_RUBBER_WOOD = registerBlock("stripped_rubber_wood",
		createLogBlock(MapColor.OFF_WHITE, ModSounds.BlockSoundGroups.RUBBER_WOOD));

	public static final Block DRIPPING_RUBBER_LOG = registerBlock("dripping_rubber_log",
		createLogBlock(MapColor.OFF_WHITE, ModSounds.BlockSoundGroups.RUBBER_WOOD));
	public static final Block DRIPPING_RUBBER_WOOD = registerBlock("dripping_rubber_wood",
		createLogBlock(MapColor.OFF_WHITE, ModSounds.BlockSoundGroups.RUBBER_WOOD));

	public static final Block RUBBERWOOD_PLANKS = registerBlock("rubberwood_planks",
		new Block(FabricBlockSettings.create().mapColor(MapColor.OFF_WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(ModSounds.BlockSoundGroups.RUBBER_WOOD).burnable()));

	public static final Block RUBBERWOOD_STAIRS = registerBlock("rubberwood_stairs",
		new StairsBlock(ModBlocks.RUBBERWOOD_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS)));
	public static final Block RUBBERWOOD_SLAB = registerBlock("rubberwood_slab",
		new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS)));

	public static final Block RUBBERWOOD_FENCE = registerBlock("rubberwood_fence",
		new FenceBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS)));
	public static final Block RUBBERWOOD_FENCE_GATE = registerBlock("rubberwood_fence_gate",
		new FenceGateBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS), WoodTypes.RUBBER_WOOD));

	public static final Block RUBBERWOOD_DOOR = registerBlock("rubberwood_door",
		new DoorBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS).nonOpaque(), BlockSetTypes.RUBBER_WOOD));
	public static final Block RUBBERWOOD_TRAPDOOR = registerBlock("rubberwood_trapdoor",
		new TrapdoorBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS).nonOpaque(), BlockSetTypes.RUBBER_WOOD));

	public static final Block RUBBERWOOD_PRESSURE_PLATE = registerBlock("rubberwood_pressure_plate",
		new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS), BlockSetTypes.RUBBER_WOOD));
	public static final Block RUBBERWOOD_BUTTON = registerBlock("rubberwood_button",
		new ButtonBlock(FabricBlockSettings.copyOf(ModBlocks.RUBBERWOOD_PLANKS), BlockSetTypes.RUBBER_WOOD, 30, true));

	public static final Block RUBBER_LEAVES = registerBlock("rubber_leaves",
		new LeavesBlock(FabricBlockSettings.copyOf(Blocks.CHERRY_LEAVES).mapColor(MapColor.DARK_GREEN)));
	public static final Block RUBBER_SAPLING = registerBlock("rubber_sapling",
		new SaplingBlock(new RubberSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.CHERRY_SAPLING).mapColor(MapColor.DARK_GREEN)));

	public static final Block RAINBOW_EUCALYPTUS_LOG = registerBlock("rainbow_eucalyptus_log",
		createLogBlock(MapColor.BLACK, MapColor.DIRT_BROWN, ModSounds.BlockSoundGroups.RAINBOW_EUCALYPTUS_WOOD));
	public static final Block RAINBOW_EUCALYPTUS_WOOD = registerBlock("rainbow_eucalyptus_wood",
		createLogBlock(MapColor.DIRT_BROWN, ModSounds.BlockSoundGroups.RAINBOW_EUCALYPTUS_WOOD));

	public static final Block STRIPPED_RAINBOW_EUCALYPTUS_LOG = registerBlock("stripped_rainbow_eucalyptus_log",
		createLogBlock(MapColor.BLACK, ModSounds.BlockSoundGroups.RAINBOW_EUCALYPTUS_WOOD));
	public static final Block STRIPPED_RAINBOW_EUCALYPTUS_WOOD = registerBlock("stripped_rainbow_eucalyptus_wood",
		createLogBlock(MapColor.BLACK, ModSounds.BlockSoundGroups.RAINBOW_EUCALYPTUS_WOOD));

	public static final Block RAINBOW_EUCALYPTUS_PLANKS = registerBlock("rainbow_eucalyptus_planks",
		new Block(FabricBlockSettings.create().mapColor(MapColor.BLACK).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(ModSounds.BlockSoundGroups.RAINBOW_EUCALYPTUS_WOOD).burnable()));

	public static final Block RAINBOW_EUCALYPTUS_STAIRS = registerBlock("rainbow_eucalyptus_stairs",
		new StairsBlock(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS)));
	public static final Block RAINBOW_EUCALYPTUS_SLAB = registerBlock("rainbow_eucalyptus_slab",
		new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS)));

	public static final Block RAINBOW_EUCALYPTUS_FENCE = registerBlock("rainbow_eucalyptus_fence",
		new FenceBlock(FabricBlockSettings.copyOf(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS)));
	public static final Block RAINBOW_EUCALYPTUS_FENCE_GATE = registerBlock("rainbow_eucalyptus_fence_gate",
		new FenceGateBlock(FabricBlockSettings.copyOf(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS), WoodTypes.RAINBOW_EUCALYPTUS_WOOD));

	public static final Block RAINBOW_EUCALYPTUS_DOOR = registerBlock("rainbow_eucalyptus_door",
		new DoorBlock(FabricBlockSettings.copyOf(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS).nonOpaque(), BlockSetTypes.RAINBOW_EUCALYPTUS_WOOD));
	public static final Block RAINBOW_EUCALYPTUS_TRAPDOOR = registerBlock("rainbow_eucalyptus_trapdoor",
		new TrapdoorBlock(FabricBlockSettings.copyOf(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS).nonOpaque(), BlockSetTypes.RAINBOW_EUCALYPTUS_WOOD));

	public static final Block RAINBOW_EUCALYPTUS_PRESSURE_PLATE = registerBlock("rainbow_eucalyptus_pressure_plate",
		new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS), BlockSetTypes.RAINBOW_EUCALYPTUS_WOOD));
	public static final Block RAINBOW_EUCALYPTUS_BUTTON = registerBlock("rainbow_eucalyptus_button",
		new ButtonBlock(FabricBlockSettings.copyOf(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS), BlockSetTypes.RAINBOW_EUCALYPTUS_WOOD, 30, true));

	public static final Block RAINBOW_EUCALYPTUS_LEAVES = registerBlock("rainbow_eucalyptus_leaves",
		new LeavesBlock(FabricBlockSettings.copyOf(Blocks.CHERRY_LEAVES).mapColor(MapColor.LIME)));
	public static final Block RAINBOW_EUCALYPTUS_SAPLING = registerBlock("rainbow_eucalyptus_sapling",
		new SaplingBlock(new RainbowEucalyptusSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.CHERRY_SAPLING).mapColor(MapColor.LIME)));

	public static final Block RUBBER_BLOCK = registerBlock("rubber_block",
		new Block(FabricBlockSettings.copyOf(Blocks.DIRT).velocityMultiplier(0.7f).sounds(BlockSoundGroup.SHROOMLIGHT).mapColor(MapColor.BLACK)));

	public static final Block SPIKE_BLOCK = registerBlock("spike_block",
		new SpikeBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).nonOpaque().requiresTool()));

	public static final Block ADVANCED_NOTE_BLOCK = registerBlock("advanced_note_block",
		new AdvancedNoteBlock(FabricBlockSettings.copyOf(Blocks.NOTE_BLOCK)));

	public static final Block QUARTZ_SHREDDER = registerBlock("quartz_shredder",
		new QuartzShredder(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));

	public static final Block STEEL_WOOL = registerBlock("steel_wool",
		new CharrableBlock(FabricBlockSettings.copyOf(Blocks.DIRT).mapColor(MapColor.LIGHT_GRAY).sounds(BlockSoundGroup.HANGING_ROOTS).burnable(), Blocks.STONE.getDefaultState()));
	public static final Block BURNT_STEEL_WOOL = registerBlock("burnt_steel_wool",
		new Block(FabricBlockSettings.copyOf(Blocks.DIRT).mapColor(MapColor.GRAY).sounds(BlockSoundGroup.HANGING_ROOTS).strength(1.5f, 6.0f)));

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

		((CustomLog)RAINBOW_EUCALYPTUS_LOG).stripOutcomes.add(STRIPPED_RAINBOW_EUCALYPTUS_LOG);
		((CustomLog)RAINBOW_EUCALYPTUS_LOG).stripChances.add(1f);

		((CustomLog)RAINBOW_EUCALYPTUS_WOOD).stripOutcomes.add(STRIPPED_RAINBOW_EUCALYPTUS_WOOD);
		((CustomLog)RAINBOW_EUCALYPTUS_WOOD).stripChances.add(1f);

		((CharrableBlock)STEEL_WOOL).turnInto = ModBlocks.BURNT_STEEL_WOOL.getDefaultState();
	}

	private static CustomLog createLogBlock(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
		return new CustomLog(FabricBlockSettings.create().mapColor((state) -> {
			return state.get(PillarBlock.AXIS) == Axis.Y ? topMapColor : sideMapColor;
			}).instrument(Instrument.BASS).strength(2.0F).sounds(soundGroup).burnable());
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

	public static class BlockSetTypes {
		public static BlockSetType RUBBER_WOOD = new BlockSetType("rubber_wood", true, ModSounds.BlockSoundGroups.RUBBER_WOOD, ModSounds.RUBBERWOOD_DOOR_CLOSE, ModSounds.RUBBERWOOD_DOOR_OPEN, ModSounds.RUBBERWOOD_TRAPDOOR_CLOSE, ModSounds.RUBBERWOOD_TRAPDOOR_OPEN, ModSounds.RUBBERWOOD_PRESSURE_PLATE_CLICK_OFF, ModSounds.RUBBERWOOD_PRESSURE_PLATE_CLICK_ON, ModSounds.RUBBERWOOD_BUTTON_CLICK_OFF, ModSounds.RUBBERWOOD_BUTTON_CLICK_ON);
		public static BlockSetType RAINBOW_EUCALYPTUS_WOOD = new BlockSetType("rainbow_eucalyptus_wood", true, ModSounds.BlockSoundGroups.RAINBOW_EUCALYPTUS_WOOD, ModSounds.RAINBOW_EUCALYPTUS_DOOR_CLOSE, ModSounds.RAINBOW_EUCALYPTUS_DOOR_OPEN, ModSounds.RAINBOW_EUCALYPTUS_TRAPDOOR_CLOSE, ModSounds.RAINBOW_EUCALYPTUS_TRAPDOOR_OPEN, ModSounds.RAINBOW_EUCALYPTUS_PRESSURE_PLATE_CLICK_OFF, ModSounds.RAINBOW_EUCALYPTUS_PRESSURE_PLATE_CLICK_ON, ModSounds.RAINBOW_EUCALYPTUS_BUTTON_CLICK_OFF, ModSounds.RAINBOW_EUCALYPTUS_BUTTON_CLICK_ON);
	}

	public static class WoodTypes {
		public static WoodType RUBBER_WOOD = new WoodType("rubber_wood", BlockSetTypes.RUBBER_WOOD, ModSounds.BlockSoundGroups.RUBBER_WOOD, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN, ModSounds.RUBBERWOOD_FENCE_GATE_CLOSE, ModSounds.RUBBERWOOD_FENCE_GATE_OPEN);
		public static WoodType RAINBOW_EUCALYPTUS_WOOD = new WoodType("rainbow_eucalyptus_wood", BlockSetTypes.RAINBOW_EUCALYPTUS_WOOD, ModSounds.BlockSoundGroups.RAINBOW_EUCALYPTUS_WOOD, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN, ModSounds.RAINBOW_EUCALYPTUS_FENCE_GATE_CLOSE, ModSounds.RAINBOW_EUCALYPTUS_FENCE_GATE_OPEN);
	}
}
