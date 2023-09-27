package net.db64.miscfeatures;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.item.ModItemGroups;
import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.sound.ModSounds;
import net.db64.miscfeatures.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

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
		ModSounds.registerModSounds();

		ModWorldGeneration.generateWorldGen();
	}
}