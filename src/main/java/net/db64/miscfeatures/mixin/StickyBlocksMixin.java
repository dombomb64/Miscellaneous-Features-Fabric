package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(PistonHandler.class)
public class StickyBlocksMixin {
	/*@Redirect(
		method = "isAdjacentBlockStuck(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)Z",
		at = @At(
			value = "INVOKE",
			target = ""
		),
		target = ""
	)*/

	private static ArrayList<Block> stickyBlocks = new ArrayList<Block>(List.of(Blocks.SLIME_BLOCK, Blocks.HONEY_BLOCK, ModBlocks.RUBBER_BLOCK));

	private static boolean isBlockSticky(BlockState state) {
		return stickyBlocks.contains(state.getBlock());
	}

	private static boolean isAdjacentBlockStuck(BlockState state, BlockState adjacentState) {
		Block blockA = state.getBlock();
		Block blockB = adjacentState.getBlock();
		boolean stickyA = isBlockSticky(state);
		boolean stickyB = isBlockSticky(adjacentState);
		if (stickyA && stickyB && blockA != blockB) {
			return false;
		}
		else {
			return stickyA || stickyB;
		}
	}
}