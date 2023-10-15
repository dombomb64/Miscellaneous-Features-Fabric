package net.db64.miscfeatures;

import net.db64.miscfeatures.block.ModBlocks;
//import net.db64.miscfeatures.block.entity.ModBlockEntities;
import net.db64.miscfeatures.item.ModItemGroups;
import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.sound.ModSounds;
import net.db64.miscfeatures.util.ModLootTableModifiers;
import net.db64.miscfeatures.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiscFeatures implements ModInitializer {
	public static final String MOD_ID = "miscfeatures";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.debug("what did mario say to luigi when he couldn't find the tv remote\nholy sh*t luigi\n...well i thought it was funny enough to use in quiplash");
		
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		//ModBlockEntities.registerModBlockEntities();
		ModSounds.registerModSounds();

		ModWorldGeneration.generateWorldGen();

		ModLootTableModifiers.modifyLootTables();

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_RUBBER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_RUBBER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIPPING_RUBBER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIPPING_RUBBER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBERWOOD_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.RUBBER_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STEEL_WOOL, 30, 60);
	}
}