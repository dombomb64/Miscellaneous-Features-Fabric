package net.db64.miscfeatures.item;

import net.db64.miscfeatures.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
	public static FoodComponent RAINBOW_SAWDUST;
	public static FoodComponent HORRIBLY_MISSPELLED_CHEESEBURGER;

	public static void registerFoodComponents() {
		RAINBOW_SAWDUST = new FoodComponent.Builder().hunger(1).snack().saturationModifier(0.25f)
			.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 400), 1f)
			.statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 400), 1f)
			.statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 400), 1f).build();
		HORRIBLY_MISSPELLED_CHEESEBURGER = new FoodComponent.Builder().hunger(2).snack().meat().saturationModifier(0.25f).alwaysEdible()
			.statusEffect(new StatusEffectInstance(ModEffects.IMMORTALITY, 400), 1f)
			.statusEffect(new StatusEffectInstance(ModEffects.OVERKILL, 400), 1f).build();
	}
}
