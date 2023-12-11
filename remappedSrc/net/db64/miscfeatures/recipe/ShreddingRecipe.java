package net.db64.miscfeatures.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;

public class ShreddingRecipe implements Recipe<SimpleInventory> {
	private final ItemStack output;
	private final List<Ingredient> recipeItems;

	public ShreddingRecipe(List<Ingredient> ingredients, ItemStack output) {
		this.output = output;
		this.recipeItems = ingredients;
	}

	@Override
	public boolean matches(SimpleInventory inventory, World world) {
		if (world.isClient()) {
			return false;
		}

		// Get the items
		var givenItems = new java.util.ArrayList<>(List.of(ItemStack.EMPTY));
		givenItems.remove(0);
		for (int i = 0; i < inventory.size() - 1; i++) {
			if (!inventory.getStack(i).isEmpty()) {
				givenItems.add(inventory.getStack(i));
			}
		}
		//MiscFeatures.LOGGER.info("Recipe output: " + output + ", Given item list: " + givenItems);

		// Not the same size
		if (givenItems.size() != recipeItems.size()) {
			return false;
		}

		// Check if they match
		for (Ingredient recipeItem : recipeItems) {
			for (int i = 0; i < givenItems.size(); i++) {
				if (recipeItem.test(givenItems.get(i))) {
					givenItems.remove(i);
					break;
				}
			}
		}
		//MiscFeatures.LOGGER.info("Recipe output: " + output + ", Given item list after removal of matches: " + givenItems);

		// If everything checked out, return true
		return givenItems.isEmpty();
	}

	@Override
	public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
		return output;
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResult(DynamicRegistryManager registryManager) {
		return output;
	}

	@Override
	public DefaultedList<Ingredient> getIngredients() {
		DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
		list.addAll(recipeItems);
		return list;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	@Override
	public boolean isIgnoredInRecipeBook() {
		return true;
	}

	public static class Type implements RecipeType<ShreddingRecipe> {
		private Type() {}
		public static final Type INSTANCE = new Type();
		public static final String ID = "quartz_shredder";
	}
	
	public static class Serializer implements RecipeSerializer<ShreddingRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final String ID = "quartz_shredder";

		private static final Codec<ShreddingRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
			validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9).fieldOf("ingredients").forGetter(ShreddingRecipe::getIngredients),
			ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(r -> r.output)
		).apply(in, ShreddingRecipe::new));

		private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
			return Codecs.validate(Codecs.validate(
				delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
			), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
		}

		@Override
		public Codec<ShreddingRecipe> codec() {
			return CODEC;
		}

		@Override
		public ShreddingRecipe read(PacketByteBuf buf) {
			DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

			for (int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromPacket(buf));
			}

			ItemStack output = buf.readItemStack();
			return new ShreddingRecipe(inputs, output);
		}

		@Override
		public void write(PacketByteBuf buf, ShreddingRecipe recipe) {
			buf.writeInt(recipe.getIngredients().size());

			for (Ingredient ingredient : recipe.getIngredients()) {
				ingredient.write(buf);
			}

			buf.writeItemStack(recipe.getResult(null));
		}
	}
}
