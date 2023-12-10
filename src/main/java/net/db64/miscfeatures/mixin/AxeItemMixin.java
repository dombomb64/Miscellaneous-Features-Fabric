package net.db64.miscfeatures.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.db64.miscfeatures.block.custom.CustomLogBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.Optional;

@Mixin(AxeItem.class)
public class AxeItemMixin {
	//@Shadow @Final protected static Map<Block, Block> STRIPPED_BLOCKS;

	/*@Shadow private Optional<BlockState> getStrippedState(BlockState state) {
		return null;
	}*/

	@ModifyVariable(
		method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;",
		at = @At(
			value = "STORE"
		),
		ordinal = 0
	)
	private Optional<BlockState> getStrippedStateCustom(Optional<BlockState> value, @Local(ordinal = 0) ItemUsageContext context) {
		// Variables
		var world = context.getWorld();
		var blockPos = context.getBlockPos();
		var state = world.getBlockState(blockPos);
		var block = state.getBlock();
		/*if (world instanceof ClientWorld) {
			if (block instanceof CustomLogBlock logBlock && logBlock.canBeStripped()) {
				return Optional.of(state);
			}
			return value;
		}*/

		// If it's a CustomLogBlock and it can be stripped
		if (block instanceof CustomLogBlock logBlock && logBlock.canBeStripped()/* && !STRIPPED_BLOCKS.containsKey(block)*/)
		{
			ArrayList<Block> stripOutcomes = logBlock.stripOutcomes;
			ArrayList<Float> stripChances = logBlock.stripChances;

			Block strippedBlock;
			BlockState strippedState;

			if (stripOutcomes.size() > 1) {
				// Weight thingy stolen from StackOverflow lmao
				// Get total weight
				float totalWeight = 0f;
				for (Float stripChance : stripChances) {
					totalWeight += stripChance;
				}

				// Get weighted random index
				int index = 0;
				for (float r = ((float) Math.random()) * totalWeight; index < stripChances.size() - 1; ++index) {
					r -= stripChances.get(index);
					if (r <= 0f) break;
				}

				// Get the correct block
				strippedBlock = stripOutcomes.get(index);
			}
			else {
				strippedBlock = stripOutcomes.get(0);
			}

			strippedState = strippedBlock.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS));
			return Optional.ofNullable(strippedState);
		}

		return value;
	}
}
