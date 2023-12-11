package net.db64.miscfeatures.recipe;

import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
	public static void registerRecipes() {
		Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MiscFeatures.MOD_ID, ShreddingRecipe.Serializer.ID),
			ShreddingRecipe.Serializer.INSTANCE);
		Registry.register(Registries.RECIPE_TYPE, new Identifier(MiscFeatures.MOD_ID, ShreddingRecipe.Type.ID),
			ShreddingRecipe.Type.INSTANCE);
	}
}
