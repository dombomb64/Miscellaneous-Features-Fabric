package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.block.custom.IcicleBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IceBlock;
import net.minecraft.block.enums.Thickness;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin {
	@Inject(
		method = "randomTick",
		at = @At(
			value = "HEAD"
		)
	)
	private void growIcicle(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
		if (random.nextFloat() < 0.011377778F // Chance
			&& world.getFluidState(pos.up()).isIn(FluidTags.WATER) // Water above
			&& (world.getBlockState(pos.down()).isAir() || (world.getBlockState(pos.down()).isOf(Blocks.WATER)))) { // Free below
			IcicleBlock.tryGrow(world, pos, Direction.DOWN);
		}
	}
}
