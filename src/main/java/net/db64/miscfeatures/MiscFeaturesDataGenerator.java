package net.db64.miscfeatures;

import net.db64.miscfeatures.datagen.ModBlockTagProvider;
import net.db64.miscfeatures.datagen.ModItemTagProvider;
import net.db64.miscfeatures.datagen.ModLootTableProvider;
import net.db64.miscfeatures.datagen.ModModelProvider;
import net.db64.miscfeatures.datagen.ModRecipeProvider;
import net.db64.miscfeatures.datagen.ModWorldProvider;
import net.db64.miscfeatures.world.ModConfiguredFeatures;
import net.db64.miscfeatures.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class MiscFeaturesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModWorldProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, registerable -> ModConfiguredFeatures.bootstrap(registerable));
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, registerable -> ModPlacedFeatures.bootstrap(registerable));
	}
}
