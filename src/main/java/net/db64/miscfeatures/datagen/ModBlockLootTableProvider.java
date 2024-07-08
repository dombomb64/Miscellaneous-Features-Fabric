package net.db64.miscfeatures.datagen;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.block.custom.WarpedWartCropBlock;
import net.db64.miscfeatures.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
	public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		addDrop(ModBlocks.RUBBER_LOG);
		addDrop(ModBlocks.RUBBER_WOOD);
		addDrop(ModBlocks.STRIPPED_RUBBER_LOG);
		addDrop(ModBlocks.STRIPPED_RUBBER_WOOD);
		addDrop(ModBlocks.DRIPPING_RUBBER_LOG);
		addDrop(ModBlocks.DRIPPING_RUBBER_WOOD);

		addDrop(ModBlocks.RUBBERWOOD_PLANKS);
		addDrop(ModBlocks.RUBBERWOOD_STAIRS);
		addDrop(ModBlocks.RUBBERWOOD_SLAB, slabDrops(ModBlocks.RUBBERWOOD_SLAB));
		addDrop(ModBlocks.RUBBERWOOD_FENCE);
		addDrop(ModBlocks.RUBBERWOOD_FENCE_GATE);
		addDrop(ModBlocks.RUBBERWOOD_DOOR, doorDrops(ModBlocks.RUBBERWOOD_DOOR));
		addDrop(ModBlocks.RUBBERWOOD_TRAPDOOR);
		addDrop(ModBlocks.RUBBERWOOD_PRESSURE_PLATE);
		addDrop(ModBlocks.RUBBERWOOD_BUTTON);

		addDrop(ModBlocks.RUBBER_LEAVES, leavesDrops(ModBlocks.RUBBER_LEAVES, ModBlocks.RUBBER_SAPLING, SAPLING_DROP_CHANCE));
		addDrop(ModBlocks.RUBBER_SAPLING);

		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_LOG);
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_WOOD);
		addDrop(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_LOG);
		addDrop(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_WOOD);

		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS);
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_STAIRS);
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_SLAB, slabDrops(ModBlocks.RAINBOW_EUCALYPTUS_SLAB));
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_FENCE);
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_FENCE_GATE);
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_DOOR, doorDrops(ModBlocks.RAINBOW_EUCALYPTUS_DOOR));
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_TRAPDOOR);
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_PRESSURE_PLATE);
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_BUTTON);

		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES, leavesDrops(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES, ModBlocks.RAINBOW_EUCALYPTUS_SAPLING, SAPLING_DROP_CHANCE));
		addDrop(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		
		addDrop(ModBlocks.RUBBER_BLOCK);

		addDrop(ModBlocks.SPIKE_BLOCK);

		addDrop(ModBlocks.ADVANCED_NOTE_BLOCK);

		addDrop(ModBlocks.QUARTZ_SHREDDER);

		addDrop(ModBlocks.STEEL_WOOL);
		addDrop(ModBlocks.BURNT_STEEL_WOOL);

		addDrop(ModBlocks.WARPED_WART, (Block block) -> LootTable.builder().pool(this.applyExplosionDecay(block, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with((((LeafEntry.Builder<?>) ItemEntry.builder(ModItems.WARPED_WART).apply((SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(WarpedWartCropBlock.AGE, 3)))))).apply((ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(WarpedWartCropBlock.AGE, 3))))))))));

		addDrop(ModBlocks.SHROOMLIGHT_SPORES, drops(ModItems.SHROOMLIGHT_SPORES));
		addDrop(ModBlocks.CRIMSON_SPORES, drops(ModItems.CRIMSON_SPORES));
		addDrop(ModBlocks.WARPED_SPORES, drops(ModItems.WARPED_SPORES));
	}
}
