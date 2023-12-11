package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.sound.ModSounds;
import net.db64.miscfeatures.util.IEntityDataSaver;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnifferEntity.class)
public abstract class SnifferEntityMixin {
	/*@Unique
	private static final TrackedData<ItemStack> FIREWORKS_ITEM = DataTracker.registerData(FireworkRocketEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
	@Unique
	private static final TrackedData<Boolean> IS_FLYING = DataTracker.registerData(FireworkRocketEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private int flightTime;
	private int flightTimeTotal;*/

	@Inject(
		at = @At(
			value = "RETURN"
		),
		method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V"
	)
	private void setFlightData(EntityType<? extends AnimalEntity> entityType, World world, CallbackInfo ci) {
		SnifferEntity entity = (SnifferEntity)(Object)this;
		NbtCompound nbt = ((IEntityDataSaver)entity).getPersistentData();
		nbt.putBoolean("isFlying", false);
		nbt.putInt("flightTime", 0);
		nbt.putInt("flightTimeTotal", 30);
	}

	@Inject(
		at = @At(
			value = "RETURN"
		),
		method = "interactMob(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;",
		cancellable = true
	)
	private void eatSawdust(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
		//ActionResult actionResult = cir.getReturnValue();
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.isOf(ModItems.RAINBOW_SAWDUST)) {
			SnifferEntity entity = (SnifferEntity)(Object)this;
			NbtCompound nbt = ((IEntityDataSaver)entity).getPersistentData();
			//entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200, 4, false, false));
			nbt.putBoolean("isFlying", true);
			entity.method_48926().playSoundFromEntity(null, entity, ModSounds.SNIFFER_SCREAM, SoundCategory.NEUTRAL, 1.0f, 1.0f);
			entity.method_48926().playSound(null, entity.getBlockPos(), ModSounds.SNIFFER_LAUNCH, SoundCategory.NEUTRAL, 1.0f, 1.0f);
			cir.setReturnValue(ActionResult.CONSUME);
		}
	}

	@Inject(
		at = @At(
			value = "RETURN"
		),
		method = "tick()V"
	)
	private void sawdustTick(CallbackInfo ci) {
		SnifferEntity entity = (SnifferEntity)(Object)this;
		NbtCompound nbt = ((IEntityDataSaver)entity).getPersistentData();
		boolean isFlying = nbt.getBoolean("isFlying");
		int flightTime = nbt.getInt("flightTime");
		int flightTimeTotal = nbt.getInt("flightTimeTotal");
		if (isFlying) {
			entity.setVelocity(entity.getVelocity().add(0.0, 0.2, 0.0));
			if (flightTime + 1 >= flightTimeTotal && !entity.method_48926().isClient) {
				//flightTime = flightTimeTotal;
				entity.method_48926().createExplosion(entity, entity.getX(), entity.getY(), entity.getZ(), 3f, World.ExplosionSourceType.MOB);
				entity.discard();
			}
			else {
				flightTime++;
				nbt.putInt("flightTime", flightTime);
			}
		}
	}
}
