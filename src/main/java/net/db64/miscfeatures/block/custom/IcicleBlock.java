package net.db64.miscfeatures.block.custom;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.MapCodec;
import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.entity.ModDamageTypes;
import net.minecraft.block.*;
import net.minecraft.block.enums.Thickness;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class IcicleBlock extends Block implements LandingBlock {
	public static final MapCodec<IcicleBlock> CODEC = createCodec(IcicleBlock::new);
	public static final DirectionProperty VERTICAL_DIRECTION;
	public static final EnumProperty<Thickness> THICKNESS;
	public static final BooleanProperty WATERLOGGED;
	private static final int MAX_STALACTITE_GROWTH = 7;
	private static final int STALACTITE_FLOOR_SEARCH_RANGE = 10;
	private static final VoxelShape TIP_MERGE_SHAPE;
	private static final VoxelShape UP_TIP_SHAPE;
	private static final VoxelShape DOWN_TIP_SHAPE;
	private static final VoxelShape BASE_SHAPE;
	private static final VoxelShape FRUSTUM_SHAPE;
	private static final VoxelShape MIDDLE_SHAPE;
	private static final VoxelShape DRIP_COLLISION_SHAPE;

	public static final Box DANGER_ZONE = new Box(-1.0, -30.0, -1.0, 1.0, 2.0, 2.0);
	public static final Box DANGER_ZONE_CLOSE = new Box(-1.0, -10.0, -1.0, 2.0, 2.0, 2.0);

	public MapCodec<IcicleBlock> getCodec() {
		return CODEC;
	}

	public IcicleBlock(AbstractBlock.Settings settings) {
		super(settings);
		this.setDefaultState((((this.stateManager.getDefaultState()).with(VERTICAL_DIRECTION, Direction.UP)).with(THICKNESS, Thickness.TIP)).with(WATERLOGGED, false));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(VERTICAL_DIRECTION, THICKNESS, WATERLOGGED);
	}

	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return canPlaceAtWithDirection(world, pos, state.get(VERTICAL_DIRECTION));
	}

	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		if (direction != Direction.UP && direction != Direction.DOWN) {
			return state;
		} else {
			Direction direction2 = state.get(VERTICAL_DIRECTION);
			if (direction2 == Direction.DOWN && world.getBlockTickScheduler().isQueued(pos, this)) {
				return state;
			} else if (direction == direction2.getOpposite() && !this.canPlaceAt(state, world, pos)) {
				if (direction2 == Direction.DOWN) {
					world.scheduleBlockTick(pos, this, 2);
				} else {
					world.scheduleBlockTick(pos, this, 1);
				}

				return state;
			} else {
				boolean bl = state.get(THICKNESS) == Thickness.TIP_MERGE;
				Thickness thickness = getThickness(world, pos, direction2, bl);
				return state.with(THICKNESS, thickness);
			}
		}
	}

	public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
		if (!world.isClient) {
			BlockPos blockPos = hit.getBlockPos();
			if (projectile.canModifyAt(world, blockPos) && projectile.canBreakBlocks(world) && projectile instanceof TridentEntity && projectile.getVelocity().length() > 0.6) {
				world.breakBlock(blockPos, true);
			}
		}
	}

	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (state.get(VERTICAL_DIRECTION) == Direction.UP && state.get(THICKNESS) == Thickness.TIP) {
			entity.handleFallDamage(fallDistance + 2.0F, 2.0F, world.getDamageSources().stalagmite());
		} else {
			super.onLandedUpon(world, state, pos, entity, fallDistance);
		}

	}

	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (canDrip(state)) {
			float f = random.nextFloat();
			if (!(f > 0.12F)) {
				getFluid(world, pos, state).filter((fluid) -> {
					return f < 0.02F || isFluidLiquid(fluid.fluid);
				}).ifPresent((fluid) -> {
					createParticle(world, pos, state, fluid.fluid);
				});
			}
		}
	}

	/*@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		world.scheduleBlockTick(pos, this, 4);
	}*/

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		//if (!this.canPlaceAt(state, world, pos)) {
			if (isPointingUp(state)) {
				world.breakBlock(pos, true);
			}
			else {
				spawnFallingBlock(state, world, pos);
			}
		//}
		//else {
		//}

		//world.scheduleBlockTick(pos, this, 4);
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return true;
	}

	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (random.nextFloat() < 0.011377778f && isHeldByIcicle(state, world, pos)) {
			tryGrow(state, world, pos, random);
		}

		if (state.get(VERTICAL_DIRECTION) == Direction.DOWN && random.nextFloat() < 1.0f) {
			if (!world.getEntitiesByClass(Entity.class, DANGER_ZONE_CLOSE.offset(pos), EntityPredicates.EXCEPT_SPECTATOR.and((entity) -> {
				return !entity.canAvoidTraps();
			})).isEmpty()) {
				spawnFallingBlock(state, world, pos);
			} else {
				var entityList = world.getEntitiesByClass(Entity.class, DANGER_ZONE.offset(pos), EntityPredicates.EXCEPT_SPECTATOR.and((entity) -> {
					return !entity.canAvoidTraps();
				}));
				int entityCount = entityList.size();
				if (entityCount > 0) {
					int highestYNotAbove = Integer.MIN_VALUE;
					// Get highest entity
					for (int i = 0; i < entityCount; i++) {
						var entity = entityList.get(i);
						if (entity.getBlockY() > highestYNotAbove) {
							highestYNotAbove = entity.getBlockY();
						}
					}

					boolean shouldFall = true;
					// Check if blocks are in the way
					for (int i = pos.getY(); i > highestYNotAbove; i--) {
						if (i < world.getBottomY()) {
							break;
						}
						var curState = world.getBlockState(new BlockPos(pos.getX(), i, pos.getZ()));
						if (!FallingBlock.canFallThrough(curState)) {
							shouldFall = false;
							break;
						}
					}

					if (shouldFall) {
						spawnFallingBlock(state, world, pos);
					}
				}
			}
		}
	}

	@Nullable
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		WorldAccess worldAccess = ctx.getWorld();
		BlockPos blockPos = ctx.getBlockPos();
		Direction direction = ctx.getVerticalPlayerLookDirection().getOpposite();
		Direction direction2 = getDirectionToPlaceAt(worldAccess, blockPos, direction);
		if (direction2 == null) {
			return null;
		} else {
			boolean bl = !ctx.shouldCancelInteraction();
			Thickness thickness = getThickness(worldAccess, blockPos, direction2, bl);
			return thickness == null ? null : ((this.getDefaultState().with(VERTICAL_DIRECTION, direction2)).with(THICKNESS, thickness)).with(WATERLOGGED, worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER);
		}
	}

	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Thickness thickness = state.get(THICKNESS);
		VoxelShape voxelShape;
		if (thickness == Thickness.TIP_MERGE) {
			voxelShape = TIP_MERGE_SHAPE;
		} else if (thickness == Thickness.TIP) {
			if (state.get(VERTICAL_DIRECTION) == Direction.DOWN) {
				voxelShape = DOWN_TIP_SHAPE;
			} else {
				voxelShape = UP_TIP_SHAPE;
			}
		} else if (thickness == Thickness.FRUSTUM) {
			voxelShape = BASE_SHAPE;
		} else if (thickness == Thickness.MIDDLE) {
			voxelShape = FRUSTUM_SHAPE;
		} else {
			voxelShape = MIDDLE_SHAPE;
		}

		Vec3d vec3d = state.getModelOffset(world, pos);
		return voxelShape.offset(vec3d.x, 0.0, vec3d.z);
	}

	public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}

	public float getMaxHorizontalModelOffset() {
		return 0.125F;
	}

	public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
		if (!fallingBlockEntity.isSilent()) {
			world.syncWorldEvent(1045, pos, 0);
		}
	}

	public DamageSource getDamageSource(Entity attacker) {
		return attacker.getDamageSources().create(ModDamageTypes.FALLING_ICICLE, attacker);
	}

	private static void spawnFallingBlock(BlockState state, ServerWorld world, BlockPos pos) {
		BlockPos.Mutable mutable = pos.mutableCopy();

		for(BlockState blockState = state; isPointingDown(blockState); blockState = world.getBlockState(mutable)) {
			FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, mutable, blockState);
			if (isTip(blockState, true)) {
				int i = Math.max(1 + pos.getY() - mutable.getY(), 6);
				float f = (float)i;
				fallingBlockEntity.setHurtEntities(f, 40);
				break;
			}

			mutable.move(Direction.DOWN);
		}

	}

	@VisibleForTesting
	public static void tryGrow(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		BlockState blockState = world.getBlockState(pos.up(1));
		BlockState blockState2 = world.getBlockState(pos.up(2));
		if (canGrow(blockState, blockState2)) {
			BlockPos blockPos = getTipPos(state, world, pos, MAX_STALACTITE_GROWTH, false);
			if (blockPos != null) {
				BlockState blockState3 = world.getBlockState(blockPos);
				if (canDrip(blockState3) && canGrow(blockState3, world, blockPos)) {
					if (random.nextBoolean()) {
						tryGrow(world, blockPos, Direction.DOWN);
					} else {
						tryGrowStalagmite(world, blockPos);
					}

				}
			}
		}
	}

	private static void tryGrowStalagmite(ServerWorld world, BlockPos pos) {
		BlockPos.Mutable mutable = pos.mutableCopy();

		for(int i = 0; i < STALACTITE_FLOOR_SEARCH_RANGE; ++i) {
			mutable.move(Direction.DOWN);
			BlockState blockState = world.getBlockState(mutable);
			if (!blockState.getFluidState().isEmpty()) {
				return;
			}

			if (isTip(blockState, Direction.UP) && canGrow(blockState, world, mutable)) {
				tryGrow(world, mutable, Direction.UP);
				return;
			}

			if (canPlaceAtWithDirection(world, mutable, Direction.UP) && !world.isWater(mutable.down())) {
				tryGrow(world, mutable.down(), Direction.UP);
				return;
			}

			if (!canDripThrough(world, mutable, blockState)) {
				return;
			}
		}

	}

	public static void tryGrow(ServerWorld world, BlockPos pos, Direction direction) {
		BlockPos blockPos = pos.offset(direction);
		BlockState blockState = world.getBlockState(blockPos);
		if (isTip(blockState, direction.getOpposite())) {
			growMerged(blockState, world, blockPos);
		} else if (blockState.isAir() || blockState.isOf(Blocks.WATER)) {
			place(world, blockPos, direction, Thickness.TIP);
		}
	}

	private static void place(WorldAccess world, BlockPos pos, Direction direction, Thickness thickness) {
		BlockState blockState = ((ModBlocks.ICICLE.getDefaultState().with(VERTICAL_DIRECTION, direction)).with(THICKNESS, thickness)).with(WATERLOGGED, world.getFluidState(pos).getFluid() == Fluids.WATER);
		world.setBlockState(pos, blockState, 3);
	}

	private static void growMerged(BlockState state, WorldAccess world, BlockPos pos) {
		BlockPos blockPos2;
		BlockPos blockPos;
		if (state.get(VERTICAL_DIRECTION) == Direction.UP) {
			blockPos = pos;
			blockPos2 = pos.up();
		} else {
			blockPos2 = pos;
			blockPos = pos.down();
		}

		place(world, blockPos2, Direction.DOWN, Thickness.TIP_MERGE);
		place(world, blockPos, Direction.UP, Thickness.TIP_MERGE);
	}

	public static void createParticle(World world, BlockPos pos, BlockState state) {
		getFluid(world, pos, state).ifPresent((fluid) -> {
			createParticle(world, pos, state, fluid.fluid);
		});
	}

	private static void createParticle(World world, BlockPos pos, BlockState state, Fluid fluid) {
		Vec3d vec3d = state.getModelOffset(world, pos);
		double e = (double)pos.getX() + 0.5 + vec3d.x;
		double f = (double)((float)(pos.getY() + 1) - 0.6875F) - 0.0625;
		double g = (double)pos.getZ() + 0.5 + vec3d.z;
		Fluid fluid2 = getDripFluid(world, fluid);
		ParticleEffect particleEffect = fluid2.isIn(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
		world.addParticle(particleEffect, e, f, g, 0.0, 0.0, 0.0);
	}

	@Nullable
	private static BlockPos getTipPos(BlockState state, WorldAccess world, BlockPos pos, int range, boolean allowMerged) {
		if (isTip(state, allowMerged)) {
			return pos;
		} else {
			Direction direction = state.get(VERTICAL_DIRECTION);
			BiPredicate<BlockPos, BlockState> biPredicate = (posx, statex) -> {
				return statex.isOf(ModBlocks.ICICLE) && statex.get(VERTICAL_DIRECTION) == direction;
			};
			return searchInDirection(world, pos, direction.getDirection(), biPredicate, (statex) -> {
				return isTip(statex, allowMerged);
			}, range).orElse(null);
		}
	}

	@Nullable
	private static Direction getDirectionToPlaceAt(WorldView world, BlockPos pos, Direction direction) {
		Direction direction2;
		if (canPlaceAtWithDirection(world, pos, direction)) {
			direction2 = direction;
		} else {
			if (!canPlaceAtWithDirection(world, pos, direction.getOpposite())) {
				return null;
			}

			direction2 = direction.getOpposite();
		}

		return direction2;
	}

	private static Thickness getThickness(WorldView world, BlockPos pos, Direction direction, boolean tryMerge) {
		Direction direction2 = direction.getOpposite();
		BlockState blockState = world.getBlockState(pos.offset(direction));
		if (isIcicleFacingDirection(blockState, direction2)) {
			return !tryMerge && blockState.get(THICKNESS) != Thickness.TIP_MERGE ? Thickness.TIP : Thickness.TIP_MERGE;
		} else if (!isIcicleFacingDirection(blockState, direction)) {
			return Thickness.TIP;
		} else {
			Thickness thickness = blockState.get(THICKNESS);
			if (thickness != Thickness.TIP && thickness != Thickness.TIP_MERGE) {
				BlockState blockState2 = world.getBlockState(pos.offset(direction2));
				return !isIcicleFacingDirection(blockState2, direction) ? Thickness.BASE : Thickness.MIDDLE;
			} else {
				return Thickness.FRUSTUM;
			}
		}
	}

	public static boolean canDrip(BlockState state) {
		return isPointingDown(state) && state.get(THICKNESS) == Thickness.TIP && !(Boolean)state.get(WATERLOGGED);
	}

	private static boolean canGrow(BlockState state, ServerWorld world, BlockPos pos) {
		Direction direction = state.get(VERTICAL_DIRECTION);
		BlockPos blockPos = pos.offset(direction);
		BlockState blockState = world.getBlockState(blockPos);
		if (!blockState.getFluidState().isEmpty()) {
			return false;
		} else {
			return blockState.isAir() || isTip(blockState, direction.getOpposite());
		}
	}

	private static Optional<BlockPos> getSupportingPos(World world, BlockPos pos, BlockState state, int range) {
		Direction direction = state.get(VERTICAL_DIRECTION);
		BiPredicate<BlockPos, BlockState> biPredicate = (posx, statex) -> {
			return statex.isOf(ModBlocks.ICICLE) && statex.get(VERTICAL_DIRECTION) == direction;
		};
		return searchInDirection(world, pos, direction.getOpposite().getDirection(), biPredicate, (statex) -> {
			return !statex.isOf(ModBlocks.ICICLE);
		}, range);
	}

	private static boolean canPlaceAtWithDirection(WorldView world, BlockPos pos, Direction direction) {
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState blockState = world.getBlockState(blockPos);
		return blockState.isSideSolidFullSquare(world, blockPos, direction) || isIcicleFacingDirection(blockState, direction);
	}

	private static boolean isTip(BlockState state, boolean allowMerged) {
		if (!state.isOf(ModBlocks.ICICLE)) {
			return false;
		} else {
			Thickness thickness = state.get(THICKNESS);
			return thickness == Thickness.TIP || allowMerged && thickness == Thickness.TIP_MERGE;
		}
	}

	private static boolean isTip(BlockState state, Direction direction) {
		return isTip(state, false) && state.get(VERTICAL_DIRECTION) == direction;
	}

	private static boolean isPointingDown(BlockState state) {
		return isIcicleFacingDirection(state, Direction.DOWN);
	}

	private static boolean isPointingUp(BlockState state) {
		return isIcicleFacingDirection(state, Direction.UP);
	}

	private static boolean isHeldByIcicle(BlockState state, WorldView world, BlockPos pos) {
		return isPointingDown(state) && !world.getBlockState(pos.up()).isOf(ModBlocks.ICICLE);
	}

	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}

	private static boolean isIcicleFacingDirection(BlockState state, Direction direction) {
		return state.isOf(ModBlocks.ICICLE) && state.get(VERTICAL_DIRECTION) == direction;
	}

	private static Optional<IcicleBlock.DrippingFluid> getFluid(World world, BlockPos pos, BlockState state) {
		return !isPointingDown(state) ? Optional.empty() : getSupportingPos(world, pos, state, 11).map((posx) -> {
			BlockPos blockPos = posx.up();
			BlockState blockState = world.getBlockState(blockPos);
			Fluid fluid;
			if (blockState.isOf(Blocks.MUD) && !world.getDimension().ultrawarm()) {
				fluid = Fluids.WATER;
			} else {
				fluid = world.getFluidState(blockPos).getFluid();
			}

			return new IcicleBlock.DrippingFluid(blockPos, fluid, blockState);
		});
	}

	private static boolean isFluidLiquid(Fluid fluid) {
		return fluid == Fluids.LAVA || fluid == Fluids.WATER;
	}

	private static boolean canGrow(BlockState dripstoneBlockState, BlockState waterState) {
		return dripstoneBlockState.isOf(Blocks.DRIPSTONE_BLOCK) && waterState.isOf(Blocks.WATER) && waterState.getFluidState().isStill();
	}

	private static Fluid getDripFluid(World world, Fluid fluid) {
		if (fluid.matchesType(Fluids.EMPTY)) {
			return world.getDimension().ultrawarm() ? Fluids.LAVA : Fluids.WATER;
		} else {
			return fluid;
		}
	}

	private static Optional<BlockPos> searchInDirection(WorldAccess world, BlockPos pos, Direction.AxisDirection direction, BiPredicate<BlockPos, BlockState> continuePredicate, Predicate<BlockState> stopPredicate, int range) {
		Direction direction2 = Direction.get(direction, Direction.Axis.Y);
		BlockPos.Mutable mutable = pos.mutableCopy();

		for(int i = 1; i < range; ++i) {
			mutable.move(direction2);
			BlockState blockState = world.getBlockState(mutable);
			if (stopPredicate.test(blockState)) {
				return Optional.of(mutable.toImmutable());
			}

			if (world.isOutOfHeightLimit(mutable.getY()) || !continuePredicate.test(mutable, blockState)) {
				return Optional.empty();
			}
		}

		return Optional.empty();
	}

	private static boolean canDripThrough(BlockView world, BlockPos pos, BlockState state) {
		if (state.isAir()) {
			return true;
		} else if (state.isOpaqueFullCube(world, pos)) {
			return false;
		} else if (!state.getFluidState().isEmpty()) {
			return false;
		} else {
			VoxelShape voxelShape = state.getCollisionShape(world, pos);
			return !VoxelShapes.matchesAnywhere(DRIP_COLLISION_SHAPE, voxelShape, BooleanBiFunction.AND);
		}
	}

	static {
		VERTICAL_DIRECTION = Properties.VERTICAL_DIRECTION;
		THICKNESS = Properties.THICKNESS;
		WATERLOGGED = Properties.WATERLOGGED;
		TIP_MERGE_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
		UP_TIP_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 11.0, 11.0);
		DOWN_TIP_SHAPE = Block.createCuboidShape(5.0, 5.0, 5.0, 11.0, 16.0, 11.0);
		BASE_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
		FRUSTUM_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
		MIDDLE_SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
		DRIP_COLLISION_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
	}

	static record DrippingFluid(BlockPos pos, Fluid fluid, BlockState sourceState) {
		DrippingFluid(BlockPos pos, Fluid fluid, BlockState sourceState) {
			this.pos = pos;
			this.fluid = fluid;
			this.sourceState = sourceState;
		}

		public BlockPos pos() {
			return this.pos;
		}

		public Fluid fluid() {
			return this.fluid;
		}

		public BlockState sourceState() {
			return this.sourceState;
		}
	}
}
