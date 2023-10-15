package net.db64.miscfeatures.util;

import net.db64.miscfeatures.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
	private static final Identifier UNDERWATER_RUIN_SMALL_ID = new Identifier("minecraft", "chests/underwater_ruin_small");

	public static void modifyLootTables() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (UNDERWATER_RUIN_SMALL_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.5f))
					.with(ItemEntry.builder(ModItems.STRIPES_ARMOR_TRIM))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

				tableBuilder.pool(poolBuilder.build());
			}
		});
	}
}
