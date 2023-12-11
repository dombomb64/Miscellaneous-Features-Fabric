package net.db64.miscfeatures.effect;

import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
	public static StatusEffect IMMORTALITY;
	public static StatusEffect OVERKILL;
	public static StatusEffect ADAMANCE;
	public static StatusEffect MERCILESSNESS;
	public static StatusEffect INSTANT_DEATH_GOOD;
	public static StatusEffect INSTANT_DEATH_BAD;
	public static StatusEffect SERENITY;

	public static StatusEffect register(String name, StatusEffect statusEffect) {
		return Registry.register(Registries.STATUS_EFFECT, new Identifier(MiscFeatures.MOD_ID, name), statusEffect);
	}

	public static void registerEffects() {
		IMMORTALITY = register("immortality", new CustomStatusEffect(StatusEffectCategory.BENEFICIAL, 0xFFAE0D));
		OVERKILL = register("overkill", new CustomStatusEffect(StatusEffectCategory.BENEFICIAL, 0x7D0015));
		ADAMANCE = register("adamance", new CustomStatusEffect(StatusEffectCategory.BENEFICIAL, 0x8C3A37).addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, "13E4F8A3-A9B7-4CD9-9916-0CD08EADCB5D", 0.5f, EntityAttributeModifier.Operation.ADDITION));
		MERCILESSNESS = register("mercilessness", new MercilessnessStatusEffect(StatusEffectCategory.BENEFICIAL, 0x628C26));
		INSTANT_DEATH_GOOD = register("instant_death_good", new CustomStatusEffect(StatusEffectCategory.NEUTRAL, 0x880DB5));
		INSTANT_DEATH_BAD = register("instant_death_bad", new CustomStatusEffect(StatusEffectCategory.NEUTRAL, 0x880DB5));
		SERENITY = register("serenity", new SerenityStatusEffect(StatusEffectCategory.BENEFICIAL, 0xFCCBE7));
	}
}
