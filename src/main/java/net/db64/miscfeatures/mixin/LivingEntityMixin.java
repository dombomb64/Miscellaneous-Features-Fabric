package net.db64.miscfeatures.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.db64.miscfeatures.effect.ModEffects;
import net.db64.miscfeatures.entity.ModDamageTypes;
import net.db64.miscfeatures.item.custom.DurabilityFood;
import net.db64.miscfeatures.util.ExtraMath;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@WrapWithCondition(
		method = "eatFood",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/ItemStack;decrement(I)V"
		)
	)
	private boolean useFoodDurabilityFirst(ItemStack stack, int amount) {
		if (stack.getItem() instanceof DurabilityFood && stack.getDamage() + 1 < stack.getMaxDamage()) {
			stack.damage(1, (LivingEntity)(Object)this, player -> player.setHealth(player.getHealth()));
			return false;
		}
		return true;
	}

	@Inject(
		method = "modifyAppliedDamage",
		at = @At(
			value = "HEAD"
		)
	)
	private void effectsModifyDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir, @Local(ordinal = 0) LocalFloatRef amountRef) {
		if (source.getAttacker() == null || !(source.getAttacker() instanceof LivingEntity theHisser))
			return;
		var toHiss = (LivingEntity)(Object)this;
		//var hissWorld = toHiss.getWorld();

		if (theHisser.hasStatusEffect(ModEffects.OVERKILL)) {
			var maxHealth = toHiss.getMaxHealth();

			// Basically, big enemy, big damage, small enemy, small damage
			var baseFormula = (float)Math.min(((amount * maxHealth) - 1) * (amount / 40) + 1, Math.min(maxHealth * 1.5, Math.max(amount, 50)));

			var minDamage = ExtraMath.average(baseFormula, amount);

			// Nerf it for stronger targets
			amountRef.set(ExtraMath.clamp(ExtraMath.lerp(baseFormula, minDamage, maxHealth * 0.01f), minDamage, baseFormula));

			//MiscFeatures.LOGGER.info("amount: " + amount + ", baseFormula: " + baseFormula + ", minDamage: " + minDamage + ", Final value: " + amountRef.get());
		}

		if (theHisser.hasStatusEffect(ModEffects.INSTANT_DEATH_GOOD)) {
			amountRef.set(amountRef.get() * 20);
			theHisser.removeStatusEffect(ModEffects.INSTANT_DEATH_GOOD);
		}

		if (toHiss.hasStatusEffect(ModEffects.INSTANT_DEATH_BAD)) {
			amountRef.set(amountRef.get() * 20);
			toHiss.removeStatusEffect(ModEffects.INSTANT_DEATH_BAD);
		}
	}

	@Inject(
		method = "damage",
		at = @At(
			value = "HEAD"
		),
		cancellable = true
	)
	private void immortalityRefuseDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		var toHiss = ((LivingEntity)(Object)this);
		var hissWorld = toHiss.getWorld();
		if (toHiss.hasStatusEffect(ModEffects.IMMORTALITY)) {
			//MiscFeatures.LOGGER.info("Recieving damage of amount " + amount + " while immortal");
			if (Objects.equals(source.getType().msgId(), toHiss.getDamageSources().create(ModDamageTypes.ACHILLES_HEEL).getType().msgId())) {
				//MiscFeatures.LOGGER.info("Recieving Achilles' Heel damage of amount " + amount);
				return;
			}
			if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY) || source.isSourceCreativePlayer()) {
				//MiscFeatures.LOGGER.info("Dealing Achilles' Heel damage of amount " + amount);
				toHiss.damage(ModDamageTypes.of(hissWorld, ModDamageTypes.ACHILLES_HEEL), amount); // Switch the damage type to change the death message
			}
			cir.setReturnValue(false);
		}
	}
}
