package net.db64.miscfeatures.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CustomStatusEffect extends StatusEffect {
	public CustomStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
		super(statusEffectCategory, color);
	}

	/*@Override
	public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
		if (!pLivingEntity.getWorld().isClient()) {
			pLivingEntity.
		}

		super.applyUpdateEffect(pLivingEntity, pAmplifier);
	}*/

	@Override
	public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
		return true;
	}
}
