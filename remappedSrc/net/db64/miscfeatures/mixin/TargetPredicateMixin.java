package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.util.IEntityDataSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TargetPredicate.class)
public class TargetPredicateMixin {
	@Inject(
		method = "test",
		at = @At(
			value = "HEAD"
		),
		cancellable = true
	)
	private void serenityPreventTargeting(LivingEntity baseEntity, LivingEntity targetEntity, CallbackInfoReturnable<Boolean> cir) {
		if (baseEntity != null && ((IEntityDataSaver)baseEntity).getPersistentData().getBoolean("serenityInRange")) {
			cir.setReturnValue(false);
		}
	}
}
