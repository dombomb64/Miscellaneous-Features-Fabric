package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.item.ModItems;
import net.minecraft.entity.passive.GoatBrain;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GoatBrain.class)
public class GoatBrainMixin {
	@Unique
	private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(Items.WHEAT, ModItems.ANIMAL_FEED);

	@Inject(
		method = "getTemptItems",
		at = @At("RETURN"),
		cancellable = true
	)
	private static void setTemptItems(CallbackInfoReturnable<Ingredient> cir) {
		cir.setReturnValue(BREEDING_INGREDIENT);
	}
}
