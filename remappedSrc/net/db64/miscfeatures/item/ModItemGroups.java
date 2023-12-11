package net.db64.miscfeatures.item;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.item.custom.InstantDeathPotionItem;
import net.db64.miscfeatures.potion.ModPotions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItemGroups {
	public static final ItemGroup MISCFEATURES_GROUP = Registry.register(Registries.ITEM_GROUP,
		new Identifier(MiscFeatures.MOD_ID, "miscfeatures"),
		FabricItemGroup.builder().displayName(Text.translatable("itemgroup.miscfeatures"))
		.icon(() -> new ItemStack(ModItems.LATEX_BOTTLE)).entries((displayContext, entries) -> {
			entries.add(ModBlocks.RUBBER_LOG);
			entries.add(ModBlocks.RUBBER_WOOD);
			entries.add(ModBlocks.STRIPPED_RUBBER_LOG);
			entries.add(ModBlocks.STRIPPED_RUBBER_WOOD);
			entries.add(ModBlocks.DRIPPING_RUBBER_LOG);
			entries.add(ModBlocks.DRIPPING_RUBBER_WOOD);

			entries.add(ModBlocks.RUBBERWOOD_PLANKS);
			entries.add(ModBlocks.RUBBERWOOD_STAIRS);
			entries.add(ModBlocks.RUBBERWOOD_SLAB);
			entries.add(ModBlocks.RUBBERWOOD_FENCE);
			entries.add(ModBlocks.RUBBERWOOD_FENCE_GATE);
			entries.add(ModBlocks.RUBBERWOOD_DOOR);
			entries.add(ModBlocks.RUBBERWOOD_TRAPDOOR);
			entries.add(ModBlocks.RUBBERWOOD_PRESSURE_PLATE);
			entries.add(ModBlocks.RUBBERWOOD_BUTTON);

			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_LOG);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_WOOD);
			entries.add(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_LOG);
			entries.add(ModBlocks.STRIPPED_RAINBOW_EUCALYPTUS_WOOD);

			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_PLANKS);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_STAIRS);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_SLAB);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_FENCE);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_FENCE_GATE);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_DOOR);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_TRAPDOOR);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_PRESSURE_PLATE);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_BUTTON);

			entries.add(ModBlocks.RUBBER_LEAVES);
			entries.add(ModBlocks.RUBBER_SAPLING);

			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_LEAVES);
			entries.add(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING);

			entries.add(ModItems.LATEX_BOTTLE);
			entries.add(ModBlocks.RUBBER_BLOCK);

			entries.add(ModBlocks.SPIKE_BLOCK);
			entries.add(ModBlocks.ADVANCED_NOTE_BLOCK);

			entries.add(ModItems.STRIPES_ARMOR_TRIM);

			entries.add(ModBlocks.QUARTZ_SHREDDER);

			entries.add(ModBlocks.STEEL_WOOL);
			entries.add(ModBlocks.BURNT_STEEL_WOOL);

			entries.add(ModItems.RAINBOW_SAWDUST);

			entries.add(ModItems.ANIMAL_FEED);

			entries.add(ModItems.WARPED_WART);

			addPotions(entries);
		}).build());

	public static void registerItemGroups() {
		MiscFeatures.LOGGER.debug("Registering item groups for Miscellaneous Features (" + MiscFeatures.MOD_ID + ")");
	}

	private static void addPotions(ItemGroup.Entries entries) {
		var potionList = List.of(ModPotions.IMMORTALITY,
			ModPotions.OVERKILL,
			ModPotions.ADAMANCE, ModPotions.ADAMANCE_LONG, ModPotions.ADAMANCE_STRONG,
			ModPotions.MERCILESSNESS, ModPotions.MERCILESSNESS_LONG, ModPotions.MERCILESSNESS_STRONG,
			Potions.WATER, Potions.AWKWARD,
			ModPotions.SERENITY, ModPotions.SERENITY_LONG, ModPotions.SERENITY_STRONG);

		// Normal Potions
		for (Potion potion : potionList) {
			if (potion == Potions.WATER) {
				// Instant Death (Good)
				entries.add(ModItems.getInstantDeathPotion(true));
			}
			else if (potion == Potions.AWKWARD) {
				// Instant Death (Bad)
				entries.add(ModItems.getInstantDeathPotion(false));
			}
			else {
				// Other
				entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), potion));
			}
		}

		// Splash Potions
		for (Potion potion : potionList) {
			if (potion != Potions.WATER && potion != Potions.AWKWARD) {
				entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
			}
		}

		// Lingering Potions
		for (Potion potion : potionList) {
			if (potion != Potions.WATER && potion != Potions.AWKWARD) {
				entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), potion));
			}
		}

		// Tipped Arrows
		for (Potion potion : potionList) {
			if (potion != Potions.WATER && potion != Potions.AWKWARD) {
				entries.add(PotionUtil.setPotion(new ItemStack(Items.TIPPED_ARROW), potion));
			}
		}
	}
}
