package net.db64.miscfeatures.block.custom;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuartzShredder extends HorizontalFacingBlock {
	protected static final Box BOX = new Box(0.0, 1.0, 0.0, 1.0, 1.25, 1.0);

	private static ArrayList<Recipe> recipes = null;

	public QuartzShredder(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		// I don't feel like just sticking it in the initializer lol
		if (recipes == null) {
			initRecipes();
		}

		// Variables
		List<ItemEntity> itemEntities = world.getEntitiesByClass(ItemEntity.class, BOX.offset(pos), EntityPredicates.VALID_ENTITY);
		if (itemEntities.isEmpty()) {
			//MiscFeatures.LOGGER.info("Shredder could not find any items");
			return ActionResult.SUCCESS;
		}
		ArrayList<ItemEntity> recipeInputs = new ArrayList<>();
		Recipe recipe = null;
		var topPos = new Vec3d(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
		var success = false;

		// Check for recipe
		for (Recipe tempRecipe : recipes) {
			//MiscFeatures.LOGGER.info("Checking shredding recipe: " + tempRecipe.output.getName().toString());
			// If the longest recipe that fits so far isn't longer than the current recipe, there's no point checking for if this one fits
			if (tempRecipe.input.size() <= recipeInputs.size()) {
				continue;
			}

			// Variables
			var tempInputs = new ArrayList<ItemEntity>();
			var matchedInputs = new boolean[tempRecipe.input.size()];

			// Go through and match up correct items
			for (ItemEntity itemEntity : itemEntities) {
				int i = 0;
				for (Item inputItem : tempRecipe.input) {
					// If the input matches with the entity and the input has not been matched before, add the entity to the list and check the next entity
					if (itemEntity.getStack().getItem().equals(inputItem) && !matchedInputs[i]) {
						tempInputs.add(itemEntity);
						matchedInputs[i] = true;
						break;
					}
					i++;
				}
			}

			// If the recipe is valid and it is longer than the longest one so far, use this one instead
			if (tempInputs.size() == tempRecipe.input.size() && tempInputs.size() > recipeInputs.size()) {
				recipeInputs = tempInputs;
				recipe = tempRecipe;
				success = true;
			}
		}

		// Follow the recipe
		if (success) {
			//MiscFeatures.LOGGER.info("Correct shredding recipe: " + recipe.output.getName().toString());
			if (world instanceof ServerWorld) {
				for (ItemEntity itemEntity : recipeInputs) {
					itemEntity.discard();
				}
				//Block.dropStack(world, new BlockPos((int)topPos.x, (int)topPos.y, (int)topPos.z), recipe.output);
				world.spawnEntity(new ItemEntity(world, topPos.x, topPos.y, topPos.z, recipe.output, 0, 0.25, 0));
			}
			else {
				for (ItemEntity itemEntity : recipeInputs) {
					for (int i = 0; i < 10; i++) {
						((ClientWorld) world).addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, itemEntity.getStack()), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), (Math.random() % 0.1) - 0.05, Math.random() % 0.1, (Math.random() % 0.1) - 0.05);
					}
				}
			}
			// Increase used stat of owners?
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	private static void initRecipes() {
		recipes = new ArrayList<>(20);

		// Steel Wool
		recipes.add(new Recipe(
			List.of(Items.IRON_INGOT),
			ModBlocks.STEEL_WOOL.asItem().getDefaultStack()
		));

		/*// Test recipe 1
		recipes.add(new Recipe(
			List.of(Items.IRON_INGOT, Items.DIAMOND),
			Blocks.ACACIA_LOG.asItem().getDefaultStack()
		));

		// Test recipe 2
		recipes.add(new Recipe(
			List.of(Items.GOLD_INGOT, Items.BONE),
			Blocks.WARPED_BUTTON.asItem().getDefaultStack()
		));*/
	}

	private static class Recipe {
		public List<Item> input;
		public ItemStack output;

		public Recipe(List<Item> input, ItemStack output) {
			this.input = input;
			this.output = output;
		}
	}
}
