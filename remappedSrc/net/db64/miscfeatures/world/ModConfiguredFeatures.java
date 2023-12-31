package net.db64.miscfeatures.world;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {
	public static final RegistryKey<ConfiguredFeature<?, ?>> RUBBER_TREE_KEY = registerKey("rubber_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> RAINBOW_EUCALYPTUS_TREE_KEY = registerKey("rainbow_eucalyptus_tree");

	public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
		register(context, RUBBER_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
			BlockStateProvider.of(ModBlocks.RUBBER_LOG),
			new StraightTrunkPlacer(4, 4, 7),
			BlockStateProvider.of(ModBlocks.RUBBER_LEAVES),
			new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
			new TwoLayersFeatureSize(1, 0, 2)).build());

		register(context, RAINBOW_EUCALYPTUS_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
			BlockStateProvider.of(ModBlocks.RAINBOW_EUCALYPTUS_LOG),
			new CherryTrunkPlacer(7, 1, 0, UniformIntProvider.create(2, 3), UniformIntProvider.create(2, 3), UniformIntProvider.create(-1, 0), UniformIntProvider.create(0, 1)),
			BlockStateProvider.of(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES),
			new CherryFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), ConstantIntProvider.create(4), 0.25f, 0.5f, 0.1f, 0.1f),
			new TwoLayersFeatureSize(1, 0, 2)).build());
	}

	public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MiscFeatures.MOD_ID, name));
	}

	private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}
}
