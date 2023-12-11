package net.db64.miscfeatures.effect;

import Z;
import java.util.List;
import net.db64.miscfeatures.util.IEntityDataSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class SerenityStatusEffect extends StatusEffect {
	public static final Box maxBox = new Box(-32, -32, -32, 32, 32, 32);
	public static final Box maxBoxCheats = new Box(-128, -128, -128, 128, 128, 128);
	public static final float distance = 16;

	public SerenityStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
		super(statusEffectCategory, color);
	}

	@Override
	public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
		World world = pLivingEntity.method_48926();
		if (!world.isClient()) {
			var box = (pAmplifier == 0 || pAmplifier == 1) ? maxBox : maxBoxCheats;
			var entities = world.getEntitiesByClass(MobEntity.class, box.offset(pLivingEntity.getPos()), EntityPredicates.EXCEPT_SPECTATOR);

			// Pacify nearby entities
			for (MobEntity affectedEntity : entities) {
				var temp = pLivingEntity.distanceTo(affectedEntity) <= (pAmplifier + 1) * distance;
				if (temp) {
					//MiscFeatures.LOGGER.info(affectedEntity.getEntityName());
					affectedEntity.setTarget(null);
					((IEntityDataSaver)affectedEntity).getPersistentData().putBoolean("serenityInRange", true);
				}
			}

			// Pacify this entity
			if (pLivingEntity instanceof MobEntity) {
				((MobEntity)pLivingEntity).setTarget(null);
				((IEntityDataSaver)pLivingEntity).getPersistentData().putBoolean("serenityInRange", true);
			}
		}

		super.applyUpdateEffect(pLivingEntity, pAmplifier);
	}

	@Override
	public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
		return true;
	}
}
