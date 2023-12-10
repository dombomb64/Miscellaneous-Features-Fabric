package net.db64.miscfeatures.potion;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.effect.ModEffects;
import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
	public static Potion IMMORTALITY;
	public static Potion OVERKILL;
	public static Potion ADAMANCE;
	public static Potion ADAMANCE_LONG;
	public static Potion ADAMANCE_STRONG;
	public static Potion MERCILESSNESS;
	public static Potion MERCILESSNESS_LONG;
	public static Potion MERCILESSNESS_STRONG;
	//public static Potion INSTANT_DEATH_GOOD;
	//public static Potion INSTANT_DEATH_BAD;
	public static Potion SERENITY;
	public static Potion SERENITY_LONG;
	public static Potion SERENITY_STRONG;

	public static Potion register(String name, Potion potion) {
		return Registry.register(Registries.POTION, new Identifier(MiscFeatures.MOD_ID, name), potion);
	}

	public static void registerPotions() {
		IMMORTALITY = register("immortality", new Potion(new StatusEffectInstance(ModEffects.IMMORTALITY, 200, 0)));
		OVERKILL = register("overkill", new Potion(new StatusEffectInstance(ModEffects.OVERKILL, 400, 0)));
		ADAMANCE = register("adamance", new Potion(new StatusEffectInstance(ModEffects.ADAMANCE, 3600, 0)));
		ADAMANCE_LONG = register("adamance_long", new Potion("adamance", new StatusEffectInstance(ModEffects.ADAMANCE, 9600, 0)));
		ADAMANCE_STRONG = register("adamance_strong", new Potion("adamance", new StatusEffectInstance(ModEffects.ADAMANCE, 1800, 1)));
		MERCILESSNESS = register("mercilessness", new Potion(new StatusEffectInstance(ModEffects.MERCILESSNESS, 1800, 0)));
		MERCILESSNESS_LONG = register("mercilessness_long", new Potion("mercilessness", new StatusEffectInstance(ModEffects.MERCILESSNESS, 4800, 0)));
		MERCILESSNESS_STRONG = register("mercilessness_strong", new Potion("mercilessness", new StatusEffectInstance(ModEffects.MERCILESSNESS, 900, 1)));
		//INSTANT_DEATH_GOOD = register("instant_death_good", new Potion(new StatusEffectInstance(ModEffects.INSTANT_DEATH_GOOD, -1, 0)));
		//INSTANT_DEATH_BAD = register("instant_death_bad", new Potion(new StatusEffectInstance(ModEffects.INSTANT_DEATH_BAD, -1, 0)));
		SERENITY = register("serenity", new Potion(new StatusEffectInstance(ModEffects.SERENITY, 1800, 0)));
		SERENITY_LONG = register("serenity_long", new Potion("serenity", new StatusEffectInstance(ModEffects.SERENITY, 4800, 0)));
		SERENITY_STRONG = register("serenity_strong", new Potion("serenity", new StatusEffectInstance(ModEffects.SERENITY, 900, 1)));

		registerPotionRecipes();
	}

	private static void registerPotionRecipes() {
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(OVERKILL, Items.FERMENTED_SPIDER_EYE, IMMORTALITY);

		//BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.DIAMOND, OVERKILL);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(IMMORTALITY, Items.FERMENTED_SPIDER_EYE, OVERKILL);

		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, ModBlocks.STEEL_WOOL.asItem(), ADAMANCE);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ADAMANCE, Items.REDSTONE, ADAMANCE_LONG);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ADAMANCE, Items.GLOWSTONE_DUST, ADAMANCE_STRONG);

		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, ModItems.WARPED_WART, MERCILESSNESS);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(MERCILESSNESS, Items.REDSTONE, MERCILESSNESS_LONG);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(MERCILESSNESS, Items.GLOWSTONE_DUST, MERCILESSNESS_STRONG);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(SERENITY, Items.FERMENTED_SPIDER_EYE, MERCILESSNESS);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(SERENITY_LONG, Items.FERMENTED_SPIDER_EYE, MERCILESSNESS_LONG);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(SERENITY_STRONG, Items.FERMENTED_SPIDER_EYE, MERCILESSNESS_STRONG);

		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.PINK_PETALS, SERENITY);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(SERENITY, Items.REDSTONE, SERENITY_LONG);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(SERENITY, Items.GLOWSTONE_DUST, SERENITY_STRONG);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(MERCILESSNESS, Items.FERMENTED_SPIDER_EYE, SERENITY);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(MERCILESSNESS_LONG, Items.FERMENTED_SPIDER_EYE, SERENITY_LONG);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(MERCILESSNESS_STRONG, Items.FERMENTED_SPIDER_EYE, SERENITY_STRONG);
	}
}
