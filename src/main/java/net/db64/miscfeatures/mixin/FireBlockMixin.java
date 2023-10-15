package net.db64.miscfeatures.mixin;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.sound.ModSounds;
import net.minecraft.block.FireBlock;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public class FireBlockMixin {
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;",
			ordinal = 1
		),
		method = "trySpreadingFire(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/util/math/random/Random;I)V",
		cancellable = true
	)
	private void burnSteelWool(World world, BlockPos pos, int spreadFactor, Random random, int currentAge, CallbackInfo ci) {
		if (world.getBlockState(pos).getBlock().equals(ModBlocks.STEEL_WOOL)) {
			// Make particles
			/*if (world instanceof ClientWorld) {
				for (int i = 0; i < 20; i++) {
					world.addParticle(ParticleTypes.FLAME, (Math.random() % 1) + pos.getX(), (Math.random() % 1) + pos.getY(), (Math.random() % 1) + pos.getZ(), (Math.random() % 0.05) - 0.025, Math.random() % 0.05, (Math.random() % 0.05) - 0.025);
				}
				world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0.05, 0);
			}*/

			// Set block
			world.setBlockState(pos, ModBlocks.BURNT_STEEL_WOOL.getDefaultState());

			// Play sound
			world.playSound(null, pos, ModSounds.CHARRABLE_BLOCK_CHAR, SoundCategory.BLOCKS, 1.0F, 1.0F);

			ci.cancel();
		}
	}
}
