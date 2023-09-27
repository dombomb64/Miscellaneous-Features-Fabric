package net.db64.miscfeatures.world.gen;

import net.db64.miscfeatures.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModTreeGeneration {
	public static void generateTrees() {
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
			GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.RUBBER_TREE_SPARSE_KEY);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE),
			GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.RUBBER_TREE_KEY);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BAMBOO_JUNGLE),
			GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.RUBBER_TREE_KEY);
	}
}
