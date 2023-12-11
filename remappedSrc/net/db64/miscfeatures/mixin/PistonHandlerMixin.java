package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonHandler.class)
public class PistonHandlerMixin {
	/*@Redirect(
		method = "isAdjacentBlockStuck(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)Z",
		at = @At(
			value = "INVOKE",
			target = ""
		),
		target = ""
	)*/

	@Unique
	private static final ArrayList<Block> stickyBlocks = new ArrayList<Block>(List.of(Blocks.SLIME_BLOCK, Blocks.HONEY_BLOCK, ModBlocks.RUBBER_BLOCK));

	/*private void trickJava() {
		MiscFeatures.LOGGER.info(((PistonHandler) (Object) this).getClass().getName());
	}*/

	@Inject(
			at = @At(
					value = "RETURN"
			),
			method = "isBlockSticky(Lnet/minecraft/block/BlockState;)Z",
			cancellable = true
	)
	private static void isBlockSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(stickyBlocks.contains(state.getBlock()));
	}

	@Unique
	private static boolean isBlockSticky2(BlockState state) {
		return stickyBlocks.contains(state.getBlock());
	}

	@Inject(
			at = @At(
					value = "RETURN"
			),
			method = "isAdjacentBlockStuck(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)Z",
			cancellable = true
	)
	private static void isAdjacentBlockStuck(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
		Block blockA = state.getBlock();
		Block blockB = adjacentState.getBlock();
		boolean stickyA = isBlockSticky2(state);
		boolean stickyB = isBlockSticky2(adjacentState);
		if (stickyA && stickyB && blockA != blockB) {
			cir.setReturnValue(false);
		}
		else {
			cir.setReturnValue(stickyA || stickyB);
		}
	}
}