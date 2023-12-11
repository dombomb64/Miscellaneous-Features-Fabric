package net.db64.miscfeatures.block.custom;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CustomLogBlock extends PillarBlock {
	public ArrayList<Block> stripOutcomes = new ArrayList<Block>();
	public ArrayList<Float> stripChances = new ArrayList<Float>();

	@Nullable
	public Item bottleItem = null;
	@Nullable
	public Block bottleOutcome = null;

	public CustomLogBlock(Settings settings) {
		super(settings);
	}

	public boolean canBeStripped() {
		return stripOutcomes.size() == stripChances.size() && !stripOutcomes.isEmpty();
	}

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);
		//MiscFeatures.LOGGER.info(itemStack.getItem().getClass().getName() + ", client = " + world.isClient());

		// Axe
		/*if (canBeStripped() && itemStack.getItem() instanceof AxeItem) {
			// Reject client and play sound
			if (world.isClient()) {
				return ActionResult.SUCCESS;
			}
			world.playSound(null, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);

			// Weight thingy stolen from StackOverflow lmao
			// Get total weight
			float totalWeight = 0f;
			for (int i = 0; i < stripChances.size(); i++) {
				totalWeight += stripChances.get(i);
			}

			// Get weighted random index
			int index = 0;
			for (float r = ((float)Math.random()) * totalWeight; index < stripChances.size() - 1; ++index) {
				r -= stripChances.get(index);
				if (r <= 0f) break;
			}

			// Get the correct block
			Block strippedBlock = stripOutcomes.get(index);
			BlockState strippedState = strippedBlock.getDefaultState().with(PillarBlock.AXIS, (Direction.Axis)state.get(PillarBlock.AXIS));

			// Use the item
			if (player instanceof ServerPlayerEntity) {
				Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, pos, itemStack);
			}

			// Set the block and damage the tool
			world.setBlockState(pos, strippedState, 11);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, Emitter.of(player, strippedState));
			if (player != null) {
				itemStack.damage(1, player, (p) -> {
				p.sendToolBreakStatus(hand);
				});
			}

			return ActionResult.CONSUME;
		}*/

		// Glass bottle
		if (bottleItem != null && itemStack.isOf(Items.GLASS_BOTTLE)) {
			// Reject client
			if (world.isClient()) {
				return ActionResult.SUCCESS;
			}

			// Do bottle stuff
			itemStack.decrement(1);
			world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (itemStack.isEmpty()) {
				player.setStackInHand(hand, new ItemStack(bottleItem));
			}
			else if (!player.getInventory().insertStack(new ItemStack(bottleItem))) {
				player.dropItem(new ItemStack(bottleItem), false);
			}
            world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);

			// Set the block
			if (bottleOutcome != null)
				world.setBlockState(pos, bottleOutcome.getDefaultState().with(PillarBlock.AXIS, (Direction.Axis)state.get(PillarBlock.AXIS)), 11);

			return ActionResult.CONSUME;
		}

		return ActionResult.FAIL;
	}
}
