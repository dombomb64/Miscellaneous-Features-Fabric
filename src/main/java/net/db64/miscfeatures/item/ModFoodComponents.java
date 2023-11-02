package net.db64.miscfeatures.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
	public static final FoodComponent RAINBOW_SAWDUST = new FoodComponent.Builder().hunger(1).snack().saturationModifier(0.25f)
		.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200), 1f)
		.statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 1200), 1f)
		.statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1200), 1f).build();
}
