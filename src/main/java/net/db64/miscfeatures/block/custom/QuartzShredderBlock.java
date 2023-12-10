package net.db64.miscfeatures.block.custom;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.block.entity.ModBlockEntities;
import net.db64.miscfeatures.block.entity.QuartzShredderBlockEntity;
import net.db64.miscfeatures.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuartzShredderBlock extends BlockWithEntity implements BlockEntityProvider {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

	protected static final Box BOX = new Box(0.0, 1.0, 0.0, 1.0, 1.25, 1.0);
	private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

	//private static ArrayList<Recipe> recipes = null;

	public QuartzShredderBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new QuartzShredderBlockEntity(pos, state);
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof QuartzShredderBlockEntity) {
				ItemScatterer.spawn(world, pos, (QuartzShredderBlockEntity)blockEntity);
				world.updateComparators(pos, this);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return validateTicker(type, ModBlockEntities.QUARTZ_SHREDDER,
			(world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			NamedScreenHandlerFactory screenHandlerFactory = ((QuartzShredderBlockEntity) world.getBlockEntity(pos));

			if (screenHandlerFactory != null) {
				player.openHandledScreen(screenHandlerFactory);
			}
		}

		return ActionResult.SUCCESS;
		//return shredStuff(state, world, pos);
	}

	// Too buggy :( Edit: Now shreds automatically :)
	/*@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		if (world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.up())) {
			shredStuff(state, world, pos);
		}
	}*/

	/*public ActionResult shredStuff(BlockState state, World world, BlockPos pos) {
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
			if (world instanceof ServerWorld serverWorld) {
				for (ItemEntity itemEntity : recipeInputs) {
					MiscFeatures.LOGGER.info("Decrementing stack of item " + itemEntity.getName().toString());
					itemEntity.getStack().decrement(1);
				}
				//Block.dropStack(world, new BlockPos((int)topPos.x, (int)topPos.y, (int)topPos.z), recipe.output);
				serverWorld.spawnEntity(new ItemEntity(serverWorld, topPos.x, topPos.y, topPos.z, recipe.output, 0, 0.25, 0));
			}
			else {
				for (ItemEntity itemEntity : recipeInputs) {
					for (int i = 0; i < 10; i++) {
						world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, itemEntity.getStack()), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), (Math.random() % 0.1) - 0.05, Math.random() % 0.1, (Math.random() % 0.1) - 0.05);
					}
				}
			}
			// Increase used stat of owners?
		}

		return ActionResult.SUCCESS;
	}*/

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return (BlockState)state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	/*private static void initRecipes() {
		recipes = new ArrayList<>(20);

		// Steel Wool
		recipes.add(new Recipe(
			List.of(Items.IRON_INGOT),
			ModBlocks.STEEL_WOOL.asItem().getDefaultStack()
		));

		// Rainbow Sawdust
		recipes.add(new Recipe(
			List.of(ModBlocks.RAINBOW_EUCALYPTUS_LOG.asItem()),
			ModItems.RAINBOW_SAWDUST.getDefaultStack()
		));
		recipes.add(new Recipe(
			List.of(ModBlocks.RAINBOW_EUCALYPTUS_WOOD.asItem()),
			ModItems.RAINBOW_SAWDUST.getDefaultStack()
		));

		// Animal Feed
		recipes.add(new Recipe(
			List.of(Items.WHEAT, Items.WHEAT_SEEDS, Items.CARROT),
			ModItems.ANIMAL_FEED.getDefaultStack()
		));

		// Nether Wart
		recipes.add(new Recipe(
			List.of(Blocks.NETHER_WART_BLOCK.asItem()),
			new ItemStack(Items.NETHER_WART, 9)
		));

		// Warped Wart
		recipes.add(new Recipe(
			List.of(Blocks.WARPED_WART_BLOCK.asItem()),
			new ItemStack(ModItems.WARPED_WART, 9)
		));

		// Test recipe 1
		recipes.add(new Recipe(
			List.of(Items.IRON_INGOT, Items.DIAMOND),
			Blocks.ACACIA_LOG.asItem().getDefaultStack()
		));

		// Test recipe 2
		recipes.add(new Recipe(
			List.of(Items.GOLD_INGOT, Items.BONE),
			Blocks.WARPED_BUTTON.asItem().getDefaultStack()
		));
	}

	private static class Recipe {
		public List<Item> input;
		public ItemStack output;

		public Recipe(List<Item> input, ItemStack output) {
			this.input = input;
			this.output = output;
		}
	}*/
}
