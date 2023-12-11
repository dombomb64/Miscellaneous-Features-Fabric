package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity {
	@Unique
	private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(Items.WHEAT, ModItems.ANIMAL_FEED);

	protected SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_INGREDIENT.test(stack);
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
