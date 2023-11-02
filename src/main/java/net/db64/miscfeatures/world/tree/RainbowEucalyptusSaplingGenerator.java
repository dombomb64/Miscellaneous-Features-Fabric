package net.db64.miscfeatures.world.tree;

import net.db64.miscfeatures.world.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class RainbowEucalyptusSaplingGenerator extends SaplingGenerator {
	@Override
	protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
		return ModConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE_KEY;
	}
}
