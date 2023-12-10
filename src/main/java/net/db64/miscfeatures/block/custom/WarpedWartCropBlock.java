package net.db64.miscfeatures.block.custom;

import net.db64.miscfeatures.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class WarpedWartCropBlock extends NetherWartBlock {
	public WarpedWartCropBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		return new ItemStack(ModItems.WARPED_WART);
	}
}
