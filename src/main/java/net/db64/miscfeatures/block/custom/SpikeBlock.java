package net.db64.miscfeatures.block.custom;

import net.db64.miscfeatures.entity.ModDamageTypes;
import net.db64.miscfeatures.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SpikeBlock extends Block {
	public static final BooleanProperty EXTENDED = BooleanProperty.of("extended");
	public static final BooleanProperty FORCED = BooleanProperty.of("forced");
	protected static final Box BOX = new Box(0.0, 1.0, 0.0, 1.0, 1.25, 1.0);

	public SpikeBlock(Settings settings) {
		super(settings);
		this.setDefaultState((BlockState)this.getDefaultState().with(EXTENDED, false).with(FORCED, false));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(new BooleanProperty[]{EXTENDED});
		builder.add(new BooleanProperty[]{FORCED});
	}

	
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);

		// Empty hand
		if (itemStack.getItem() instanceof AirBlockItem) {
			// Reject client
			if (world.isClient()) {
				return ActionResult.SUCCESS;
			}

			// Extend spike
			if (!(Boolean)state.get(FORCED) && !(Boolean)state.get(EXTENDED)) {
				extend(world, pos, state);
				world.scheduleBlockTick(new BlockPos(pos), this, 20);
			}

			return ActionResult.CONSUME;
		}

		return ActionResult.FAIL;
	}

	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (world.isClient) {
			return;
		}

		if (!(Boolean)state.get(FORCED)) {
			if (!(Boolean)state.get(EXTENDED)) {
				extend(world, pos, state);
			}
			world.scheduleBlockTick(new BlockPos(pos), this, 6);
		}

		if (!entity.bypassesSteppingEffects() && (Boolean)state.get(EXTENDED)) {
			entity.damage(ModDamageTypes.of(world, ModDamageTypes.SPIKE_BLOCK), 4.0f);
		}
	}

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!(Boolean)state.get(FORCED)) {
			int entityCount = getEntityCount(world, BOX.offset(pos), Entity.class);
			if (entityCount == 0 && (Boolean)state.get(EXTENDED)) {
				retract(world, pos, state);
			}
		}
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		updateSelf(state, world, pos);
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		updateSelf(state, world, pos);
	}

	private void updateSelf(BlockState state, World world, BlockPos pos) {
		boolean powered = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.up());
		boolean wasForced = state.get(FORCED);
		if (powered && !wasForced) {
			force(world, pos, state);
		} else if (!powered && wasForced) {
			unforce(world, pos, state);
			world.scheduleBlockTick(new BlockPos(pos), this, 2);
		}
	}

	protected static int getEntityCount(World world, Box box, Class<? extends Entity> entityClass) {
		return world.getEntitiesByClass(entityClass, box, EntityPredicates.EXCEPT_SPECTATOR.and((entity) -> {
			return !entity.canAvoidTraps();
		})).size();
	}

	private void extend(World world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, (BlockState)state.with(EXTENDED, true), 2);
		world.playSound(null, pos, ModSounds.SPIKE_BLOCK_EXTEND, SoundCategory.BLOCKS);
	}

	private void retract(World world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, (BlockState)state.with(EXTENDED, false), 2);
		world.playSound(null, pos, ModSounds.SPIKE_BLOCK_RETRACT, SoundCategory.BLOCKS);
	}

	private void force(World world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, (BlockState)state.with(FORCED, true), 2);
	}

	private void unforce(World world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, (BlockState)state.with(FORCED, false), 2);
	}
}
