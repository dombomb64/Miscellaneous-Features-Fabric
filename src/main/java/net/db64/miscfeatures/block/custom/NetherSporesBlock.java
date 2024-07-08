package net.db64.miscfeatures.block.custom;

import net.db64.miscfeatures.entity.custom.FallingSporesEntity;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.List;

public class NetherSporesBlock extends Block implements Fertilizable {
	public static final IntProperty TIMER = IntProperty.of("timer", 0, 1000);
	public static final BooleanProperty COATED = BooleanProperty.of("coated"); // For optimizing light updates with shroomlight spores
	public static final EnumProperty<NetherSporesState> STATE = EnumProperty.of("state", NetherSporesState.class);

	private final int TIMER_DEFAULT = 0;
	private int TIMER_UNSTABLE = 40;
	private int TIMER_REGROWING = 60;

	protected static final Box BOX = new Box(0.0, 1.0, 0.0, 1.0, 1.75, 1.0);

	public NetherSporesBlock(Settings settings, int timerUnstable, int timerRegrowing) {
		super(settings);
		TIMER_UNSTABLE = timerUnstable;
		TIMER_REGROWING = timerRegrowing;
		this.setDefaultState(this.getDefaultState().with(TIMER, TIMER_DEFAULT).with(COATED, false).with(STATE, NetherSporesState.DEFAULT));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(new IntProperty[]{TIMER});
		builder.add(new BooleanProperty[]{COATED});
		builder.add(new EnumProperty[]{STATE});
	}

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);
		//String itemId = Registries.ITEM.getId(itemStack.getItem()).toString();

		if (world.isClient()) { // Reject client
			return ActionResult.SUCCESS;
		}

		if (state.get(COATED)) { // Already coated
			world.setBlockState(pos, state.with(COATED, false));
			/*world.spawnEntity(new ItemEntity(
				world,
				MathHelper.nextDouble(world.random, -0.25, 0.25),
				MathHelper.nextDouble(world.random, -0.25, 0.25),
				MathHelper.nextDouble(world.random, -0.25, 0.25),
				Items.NETHER_BRICK.getDefaultStack(),
				MathHelper.nextDouble(world.random, -0.1, 0.1),
				MathHelper.nextDouble(world.random, 0.0, 0.1),
				MathHelper.nextDouble(world.random, -0.1, 0.1)));*/
			if (!player.isCreative())
				Block.dropStack(world, pos, Items.NETHER_BRICK.getDefaultStack());
			//MiscFeatures.LOGGER.info("coating stripped");

			return ActionResult.CONSUME;
		}
		else if (itemStack.getItem() == Items.NETHER_BRICK) { // Nether Brick
			world.setBlockState(pos, state.with(COATED, true));
			//MiscFeatures.LOGGER.info("coating applied");

			return ActionResult.CONSUME;
		}

		return ActionResult.FAIL;
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.scheduledTick(state, world, pos, random);

		var entities = getEntityList(world, BOX.offset(pos), Entity.class);
		String stateString = state.get(STATE).asString();

		boolean isEntityAbove = false;
		for (Entity entity : entities) {
			if (entity.getY() >= pos.getY() + 1 && entity.isOnGround()) {
				isEntityAbove = true;
				break;
			}
		}

		if (stateString.equals("regrowing")) { // Is regrowing
			int timer = state.get(TIMER) - 1;

			if (timer <= TIMER_DEFAULT) { // Is finished growing
				world.setBlockState(pos, state.with(TIMER, TIMER_DEFAULT).with(STATE, NetherSporesState.DEFAULT));
			}
			else { // Keep ticking
				world.setBlockState(pos, state.with(TIMER, timer));
			}
		}
		else if (isEntityAbove) { // Being stood on
			if (stateString.equals("default")) { // Just stepped on
				world.setBlockState(pos, state.with(TIMER, TIMER_UNSTABLE).with(STATE, NetherSporesState.UNSTABLE));
			}
			else if (stateString.equals("unstable")) { // Already being stood on
				int timer = state.get(TIMER) - 1;

				if (timer <= TIMER_DEFAULT) { // Can no longer bear the weight
					world.setBlockState(pos, state.with(TIMER, TIMER_REGROWING).with(STATE, NetherSporesState.REGROWING));
					world.spawnEntity(FallingSporesEntity.spawnFromBlock(world, pos, state));
				}
				else { // Keep ticking
					world.setBlockState(pos, state.with(TIMER, timer));
				}
			}
		}
		else if (stateString.equals("unstable")) { // Just stepped off
			world.setBlockState(pos, state.with(TIMER, TIMER_DEFAULT).with(STATE, NetherSporesState.DEFAULT));
		}

		world.scheduleBlockTick(pos, this, 1);
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		super.onBlockAdded(state, world, pos, oldState, notify);

		world.scheduleBlockTick(pos, this, 1);
	}

	protected static List<? extends Entity> getEntityList(World world, Box box, Class<? extends Entity> entityClass) {
		return world.getEntitiesByClass(entityClass, box, EntityPredicates.EXCEPT_SPECTATOR.and((entity) -> {
			return !entity.canAvoidTraps();
		}));
	}

	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (state.get(STATE) == NetherSporesState.REGROWING) {
			return VoxelShapes.empty();
		}

		var entity = ((EntityShapeContext)context).getEntity();
		if (entity == null)
			return VoxelShapes.fullCube();

		return (context.isAbove(VoxelShapes.fullCube(), pos, true) && !entity.isSneaking()) ? VoxelShapes.fullCube() : VoxelShapes.empty();
	}

	public static int getShroomlightLuminance(BlockState state) {
		if (state.get(NetherSporesBlock.COATED))
			return 0;
		if (state.get(NetherSporesBlock.STATE).asString().equals("regrowing"))
			return 5;
		return 15;
	}

	@Override
	public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		if (world.random.nextFloat() < 0.5f) {
			if (state.get(COATED)) {
				Block.dropStack(world, pos, Items.NETHER_BRICK.getDefaultStack());
			}
			world.setBlockState(pos, Blocks.SHROOMLIGHT.getDefaultState());
		}
	}

	public enum NetherSporesState implements StringIdentifiable {
		DEFAULT("default"),
		UNSTABLE("unstable"),
		REGROWING("regrowing");

		private final String name;

		private NetherSporesState(String name) {
			this.name = name;
		}

		@Override
		public String asString() {
			return this.name;
		}
	}
}
