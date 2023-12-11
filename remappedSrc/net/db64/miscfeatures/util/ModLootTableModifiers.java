package net.db64.miscfeatures.util;

import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.item.custom.InstantDeathPotionItem;
import net.db64.miscfeatures.potion.ModPotions;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetNbtLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.nbt.NbtCompound;

public class ModLootTableModifiers {
	public static void modifyLootTables() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			// Chests

			if (LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(id)) {
				LootPool.Builder stripesTrim = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.5f))
					.with(ItemEntry.builder(ModItems.STRIPES_ARMOR_TRIM))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

				tableBuilder.pool(stripesTrim.build());
			}

			if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
				LootPool.Builder cheeseburger = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.005f))
					.with(ItemEntry.builder(ModItems.HORRIBLY_MISSPELLED_CHEESEBURGER))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

				LootPool.Builder immortality = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.3f))
					.with(ItemEntry.builder(Items.POTION))
					.apply(SetPotionLootFunction.builder(ModPotions.IMMORTALITY))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

				tableBuilder.pool(cheeseburger.build());
				tableBuilder.pool(immortality.build());
			}

			if (LootTables.STRONGHOLD_CORRIDOR_CHEST.equals(id)) {
				LootPool.Builder stripesTrim = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.1f))
					.with(ItemEntry.builder(Items.POTION))
					.apply(SetPotionLootFunction.builder(ModPotions.OVERKILL))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

				tableBuilder.pool(stripesTrim.build());
			}

			if (LootTables.ANCIENT_CITY_CHEST.equals(id)) {
				LootPool.Builder stripesTrim = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.3f))
					.with(ItemEntry.builder(Items.POTION))
					.apply(SetPotionLootFunction.builder(ModPotions.OVERKILL))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

				tableBuilder.pool(stripesTrim.build());
			}

			// Entities

			if (EntityType.WITCH.getLootTableId().equals(id)) {
				var nbtGood = new NbtCompound();
				nbtGood.put(IEntityDataSaver.dataId, new NbtCompound());
				nbtGood.getCompound(IEntityDataSaver.dataId).putBoolean(InstantDeathPotionItem.IS_EFFECT_GOOD_KEY, true);
				var nbtBad = new NbtCompound();
				nbtBad.put(IEntityDataSaver.dataId, new NbtCompound());
				nbtBad.getCompound(IEntityDataSaver.dataId).putBoolean(InstantDeathPotionItem.IS_EFFECT_GOOD_KEY, false);

				LootPool.Builder instantDeathPotion = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.05f))
					.with(ItemEntry.builder(ModItems.INSTANT_DEATH_POTION)
						.apply(SetNbtLootFunction.builder(nbtGood)))
					.with(ItemEntry.builder(ModItems.INSTANT_DEATH_POTION)
						.apply(SetNbtLootFunction.builder(nbtBad)))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

				tableBuilder.pool(instantDeathPotion.build());
			}
		});
	}
}
