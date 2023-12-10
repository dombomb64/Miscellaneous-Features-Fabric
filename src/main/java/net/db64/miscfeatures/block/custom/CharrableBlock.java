package net.db64.miscfeatures.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CharrableBlock extends Block implements ICharrableBlock {
	public BlockState turnInto;

	public CharrableBlock(Settings settings, BlockState turnInto) {
		super(settings);
		this.turnInto = turnInto;
	}

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		return useBurnItem(world, pos, player, hand);
	}
}
