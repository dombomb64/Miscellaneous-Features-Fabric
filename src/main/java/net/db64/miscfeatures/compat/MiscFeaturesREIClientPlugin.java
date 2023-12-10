package net.db64.miscfeatures.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.recipe.ShreddingRecipe;
import net.db64.miscfeatures.screen.QuartzShredderScreen;

public class MiscFeaturesREIClientPlugin implements REIClientPlugin {
	@Override
	public void registerCategories(CategoryRegistry registry) {
		registry.add(new ShreddingCategory());

		registry.addWorkstations(ShreddingCategory.QUARTZ_SHREDDER, EntryStacks.of(ModBlocks.QUARTZ_SHREDDER));
	}

	@Override
	public void registerDisplays(DisplayRegistry registry) {
		registry.registerRecipeFiller(ShreddingRecipe.class, ShreddingRecipe.Type.INSTANCE,
			ShreddingDisplay::new);
	}

	@Override
	public void registerScreens(ScreenRegistry registry) {
		registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30),
			QuartzShredderScreen.class,
			ShreddingCategory.QUARTZ_SHREDDER);
	}
}
