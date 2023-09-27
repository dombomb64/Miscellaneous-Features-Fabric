package net.db64.miscfeatures.util;

import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
	public static class Blocks {
		public static final TagKey<Block> RUBBER_LOGS = createTag("rubber_logs");

		private static TagKey<Block> createTag(String name) {
			return TagKey.of(RegistryKeys.BLOCK, new Identifier(MiscFeatures.MOD_ID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> RUBBER_LOGS = createTag("rubber_logs");

		private static TagKey<Item> createTag(String name) {
			return TagKey.of(RegistryKeys.ITEM, new Identifier(MiscFeatures.MOD_ID, name));
		}
	}
}
