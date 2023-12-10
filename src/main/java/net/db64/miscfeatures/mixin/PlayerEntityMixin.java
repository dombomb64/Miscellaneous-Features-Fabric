package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.effect.ModEffects;
import net.db64.miscfeatures.util.IEntityDataSaver;
import net.db64.miscfeatures.util.MercilessnessData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
	@Redirect(
		method = "getAttackCooldownProgressPerTick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/player/PlayerEntity;getAttributeValue(Lnet/minecraft/entity/attribute/EntityAttribute;)D",
			ordinal = 0
		)
	)
	private double applyMercilessnessEffect(PlayerEntity instance, EntityAttribute entityAttribute) {
		var toHiss = (PlayerEntity)(Object)this;
		var effect = toHiss.getStatusEffect(ModEffects.MERCILESSNESS);
		if (effect == null)
			return instance.getAttributeValue(entityAttribute);

		var attackSpeed = instance.getAttributeValue(entityAttribute);
		var combo = ((IEntityDataSaver)toHiss).getPersistentData().getInt("mercilessnessCombo");
		var level = effect.getAmplifier();

		// The max speed, combo break forgiveness, and attack acceleration all increase with the level (y = m * x + b)
		var acceleration = 0.25 * level + 0.25; // m = 0.25, b = 0.25
		var maxSpeed = 1.5 * level + (attackSpeed * 2); // m = 1.5, b = attackSpeed * 2

		return Math.min(acceleration * combo + attackSpeed, maxSpeed);
	}

	@Inject(
		method = "tick",
		at = @At(
			value = "HEAD"
		)
	)
	private void tickMercilessness(CallbackInfo ci) {
		var toHiss = (PlayerEntity)(Object)this;
		if (toHiss.getStatusEffect(ModEffects.MERCILESSNESS) == null || toHiss.getAttackCooldownProgress(0.5f) < 1f)
			return;
		//var nbt = ((IEntityDataSaver)toHiss).getPersistentData();

		if (MercilessnessData.removeComboBreakTicks((IEntityDataSaver)toHiss, 1) == 0) {
			MercilessnessData.breakCombo((IEntityDataSaver)toHiss);
		}
	}

	@Inject(
		method = "attack",
		at = @At(
			value = "HEAD"
		)
	)
	private void mercilessnessAttack(Entity target, CallbackInfo ci) {
		if (target == null || (target instanceof LivingEntity && ((LivingEntity)target).hurtTime > 0))
			return; // Only count successful attacks

		var toHiss = (PlayerEntity)(Object)this;
		//var nbt = ((IEntityDataSaver)toHiss).getPersistentData();
		MercilessnessData.addCombo((IEntityDataSaver)toHiss, 1);
		MercilessnessData.setComboBreakTicksToMax((IEntityDataSaver)toHiss);
	}
}
