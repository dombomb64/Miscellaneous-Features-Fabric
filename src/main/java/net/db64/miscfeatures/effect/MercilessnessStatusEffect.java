package net.db64.miscfeatures.effect;

import net.db64.miscfeatures.util.IEntityDataSaver;
import net.db64.miscfeatures.util.MercilessnessData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MercilessnessStatusEffect extends StatusEffect {
	public MercilessnessStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
		super(statusEffectCategory, color);
	}

	/*@Override
	public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
		if (!pLivingEntity.getWorld().isClient()) {
			pLivingEntity.
		}

		super.applyUpdateEffect(pLivingEntity, pAmplifier);
	}*/

	/*@Override
	public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
		return true;
	}*/

	@Override
	public void onApplied(LivingEntity entity, int amplifier) {
		MercilessnessData.breakCombo((IEntityDataSaver)entity);
	}
}
