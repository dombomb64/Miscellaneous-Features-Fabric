package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.util.IEntityDataSaver;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {
	/*@Inject(
		method = "canTarget",
		at = @At(
			value = "RETURN"
		),
		cancellable = true
	)
	private void serenityPreventTargeting(EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
		//var toHiss = (MobEntity)(Object)this;
		var nbt = ((IEntityDataSaver)this).getPersistentData();
		cir.setReturnValue(cir.getReturnValueZ() && !nbt.getBoolean("serenityInRange"));
	}*/

	@Inject(
		method = "tickNewAi",
		at = @At(
			value = "RETURN"
		)
	)
	private void serenityAllowTargeting(CallbackInfo ci) {
		((IEntityDataSaver)this).getPersistentData().putBoolean("serenityInRange", false);
	}
}
