package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntityDataSaver {
	@Unique
	private NbtCompound persistentData;

	@Override
	public NbtCompound getPersistentData() {
		if (this.persistentData == null) {
			this.persistentData = new NbtCompound();
		}
		return persistentData;
	}

	@Inject(
		method = "writeNbt",
		at = @At("HEAD")
	)
	protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
		if (persistentData != null) {
			nbt.put(IEntityDataSaver.dataId, persistentData);
		}
	}

	@Inject(
		method = "readNbt",
		at = @At("HEAD")
	)
	protected void injectReadMethod(NbtCompound nbt, CallbackInfo ci) {
		if (nbt.contains(IEntityDataSaver.dataId, 10)) {
			persistentData = nbt.getCompound(IEntityDataSaver.dataId);
		}
	}
}
