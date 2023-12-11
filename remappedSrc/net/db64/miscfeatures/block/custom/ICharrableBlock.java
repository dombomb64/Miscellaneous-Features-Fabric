package net.db64.miscfeatures.block.custom;

import net.db64.miscfeatures.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICharrableBlock {
	BlockState turnInto = Blocks.STONE.getDefaultState();

	default ActionResult useBurnItem(World world, BlockPos pos, PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);

		if (itemStack.getItem() instanceof FlintAndSteelItem || itemStack.getItem() instanceof FireChargeItem) {
			// Do client stuff
			if (world.isClient()) {
				for (int i = 0; i < 20; i++) {
					world.addParticle(ParticleTypes.FLAME, (Math.random() % 1) + pos.getX(), (Math.random() % 1) + pos.getY(), (Math.random() % 1) + pos.getZ(), (Math.random() % 0.05) - 0.025, Math.random() % 0.05, (Math.random() % 0.05) - 0.025);
				}
				world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0.05, 0);

				return ActionResult.SUCCESS;
			}

			// Set block
			world.setBlockState(pos, turnInto);

			// Play sound
			world.playSound(null, pos, ModSounds.CHARRABLE_BLOCK_CHAR, SoundCategory.BLOCKS, 1.0F, 1.0F);

			//((ServerWorld)world).spawnParticles(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), 20, 0.25, 0.25, 0.25, 0.05);

			return ActionResult.CONSUME;
		}

		return ActionResult.FAIL;
	}
}
