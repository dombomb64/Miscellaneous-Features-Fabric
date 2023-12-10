package net.db64.miscfeatures.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.db64.miscfeatures.recipe.ShreddingRecipe;
import net.minecraft.recipe.RecipeEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShreddingDisplay extends BasicDisplay {
	public ShreddingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
		super(inputs, outputs);
	}

	public ShreddingDisplay(RecipeEntry<ShreddingRecipe> recipe) {
		super(getInputList(recipe.value()), List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null)))));
	}

	private static List<EntryIngredient> getInputList(ShreddingRecipe recipe) {
		if (recipe == null)
			return Collections.emptyList();

		List<EntryIngredient> list = new ArrayList<>();
		var ingredients = recipe.getIngredients();

		for (net.minecraft.recipe.Ingredient ingredient : ingredients) {
			list.add(EntryIngredients.ofIngredient(ingredient));
		}

		return list;
	}

	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return ShreddingCategory.QUARTZ_SHREDDER;
	}
}
