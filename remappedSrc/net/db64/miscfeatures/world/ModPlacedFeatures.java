package net.db64.miscfeatures.world;

import java.util.List;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

public class ModPlacedFeatures {
	public static final RegistryKey<PlacedFeature> RUBBER_TREE_KEY = registerKey("rubber_tree");
	public static final RegistryKey<PlacedFeature> RUBBER_TREE_DENSE_KEY = registerKey("rubber_tree_dense");
	public static final RegistryKey<PlacedFeature> RUBBER_TREE_SPARSE_KEY = registerKey("rubber_tree_sparse");

	public static final RegistryKey<PlacedFeature> RAINBOW_EUCALYPTUS_TREE_KEY = registerKey("rainbow_eucalyptus_tree");
	public static final RegistryKey<PlacedFeature> RAINBOW_EUCALYPTUS_TREE_DENSE_KEY = registerKey("rainbow_eucalyptus_tree_dense");
	public static final RegistryKey<PlacedFeature> RAINBOW_EUCALYPTUS_TREE_SPARSE_KEY = registerKey("rainbow_eucalyptus_tree_sparse");

	public static void bootstrap(Registerable<PlacedFeature> context) {
		var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

		register(context, RUBBER_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RUBBER_TREE_KEY),
			PlacedFeatures.wouldSurvive(ModBlocks.RUBBER_SAPLING));
		register(context, RUBBER_TREE_DENSE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RUBBER_TREE_KEY),
			VegetationPlacedFeatures.treeModifiersWithWouldSurvive(CountPlacementModifier.of(UniformIntProvider.create(5, 30)), ModBlocks.RUBBER_SAPLING));
		register(context, RUBBER_TREE_SPARSE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RUBBER_TREE_KEY),
			VegetationPlacedFeatures.treeModifiersWithWouldSurvive(CountPlacementModifier.of(UniformIntProvider.create(1, 3)), ModBlocks.RUBBER_SAPLING));

		register(context, RAINBOW_EUCALYPTUS_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE_KEY),
			PlacedFeatures.wouldSurvive(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING));
		register(context, RAINBOW_EUCALYPTUS_TREE_DENSE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE_KEY),
			VegetationPlacedFeatures.treeModifiersWithWouldSurvive(CountPlacementModifier.of(UniformIntProvider.create(5, 30)), ModBlocks.RAINBOW_EUCALYPTUS_SAPLING));
		register(context, RAINBOW_EUCALYPTUS_TREE_SPARSE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE_KEY),
			VegetationPlacedFeatures.treeModifiersWithWouldSurvive(CountPlacementModifier.of(UniformIntProvider.create(1, 3)), ModBlocks.RAINBOW_EUCALYPTUS_SAPLING));
	}

	public static RegistryKey<PlacedFeature> registerKey(String name) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MiscFeatures.MOD_ID, name));
	}

	private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
		context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
	}

	private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
		register(context, key, configuration, List.of(modifiers));
	}
}
