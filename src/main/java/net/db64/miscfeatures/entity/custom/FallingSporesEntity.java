package net.db64.miscfeatures.entity.custom;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FallingSporesEntity extends FallingBlockEntity {
	public FallingSporesEntity(EntityType<? extends FallingSporesEntity> entityType, World world) {
		super(entityType, world);
		this.setVelocity(new Vec3d(0d, -0.05d, 0d));
		this.block = ModBlocks.SHROOMLIGHT_SPORES.getDefaultState();
	}

	private FallingSporesEntity(World world, double x, double y, double z, BlockState block) {
		this(ModEntities.FALLING_SPORES, world);
		this.block = block;
		this.intersectionChecked = true;
		this.setPosition(x, y, z);
		this.prevX = x;
		this.prevY = y;
		this.prevZ = z;
		this.setFallingBlockPos(this.getBlockPos());
	}

	public static FallingSporesEntity spawnFromBlock(World world, BlockPos pos, BlockState state) {
		FallingSporesEntity fallingSporesEntity = new FallingSporesEntity(world, (double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, false) : state);
		world.spawnEntity(fallingSporesEntity);
		return fallingSporesEntity;
	}

	protected Text getDefaultName() {
		return Text.translatable("entity.miscfeatures.falling_spores_type", this.block.getBlock().getName());
	}

	public boolean isCollidable() {
		return true;
	}

	@Override
	public void tick() {
		if (this.block.isAir()) {
			this.discard();
		} else {
			++this.timeFalling;

			this.move(MovementType.SELF, this.getVelocity());
			if (!this.getWorld().isClient) {
				BlockPos blockPos = this.getBlockPos();

				if (!this.isOnGround()) {
					if (!this.getWorld().isClient && (this.timeFalling > 100 && (blockPos.getY() <= this.getWorld().getBottomY() || blockPos.getY() > this.getWorld().getTopY()) || this.timeFalling > 600)) {
						this.discard();
					}
				} else {
					BlockState blockState = this.getWorld().getBlockState(blockPos);
					this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
					if (!blockState.isOf(Blocks.MOVING_PISTON)) {
						this.discard();
					}
				}
			}
		}
	}

	/*@Nullable
	@Override
	public ItemEntity dropItem(ItemConvertible item) {
		return null;
	}*/
}
