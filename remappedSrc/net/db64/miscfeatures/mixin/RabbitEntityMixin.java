package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RabbitEntity.class)
public class RabbitEntityMixin {
	@Unique
	private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(Items.CARROT, Items.GOLDEN_CARROT, Blocks.DANDELION, ModItems.ANIMAL_FEED);

	@Inject(
		method = "isTempting",
		at = @At("RETURN"),
		cancellable = true
	)
	private static void setTemptingItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(BREEDING_INGREDIENT.test(stack));
	}

	@Redirect(
		method = "initGoals",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/recipe/Ingredient;ofItems([Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/recipe/Ingredient;"
		)
	)
	private Ingredient setBreedingItems(ItemConvertible[] items) {
		return BREEDING_INGREDIENT;
	}
}
