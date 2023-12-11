package net.db64.miscfeatures.block.entity;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.recipe.ShreddingRecipe;
import net.db64.miscfeatures.screen.QuartzShredderScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class QuartzShredderBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);

	private static final int INPUT_START_SLOT = 0;
	private static final int INPUT_END_SLOT = 8;
	private static final int OUTPUT_SLOT = 9;

	private static final String PROGRESS_KEY = "quartz_shredder_progress";

	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 4;
	
	public QuartzShredderBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.QUARTZ_SHREDDER, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> QuartzShredderBlockEntity.this.progress;
					case 1 -> QuartzShredderBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0 -> QuartzShredderBlockEntity.this.progress = value;
					case 1 -> QuartzShredderBlockEntity.this.maxProgress = value;
				};
			}

			@Override
			public int size() {
				return 2;
			}
		};
	}

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(this.pos);
	}

	@Override
	public Text getDisplayName() {
		return Text.translatable("container.miscfeatures.quartz_shredder");
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
		nbt.putInt(PROGRESS_KEY, progress);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
		progress = nbt.getInt(PROGRESS_KEY);
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return new QuartzShredderScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
	}

	public void tick(World world, BlockPos pos, BlockState state) {
		if (world.isClient()) {
			return;
		}

		if (isOutputSlotEmptyOrRecievable()) {
			if (this.hasRecipe()) {
				//MiscFeatures.LOGGER.info("Shredder at " + pos.toShortString() + " is rapidly approaching recipe completion");
				this.increaseCraftProgress();
				markDirty(world, pos, state);

				if (hasCraftingFinished()) {
					this.craftItem();
					this.resetProgress();
				}
			}
			else {
				this.resetProgress();
			}
		}
		else {
			this.resetProgress();
			markDirty(world, pos, state);
		}
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private void craftItem() {
		Optional<RecipeEntry<ShreddingRecipe>> recipe = getCurrentRecipe();

		for (int i = INPUT_START_SLOT; i <= INPUT_END_SLOT; i++) {
			this.removeStack(i, 1);
		}

		this.setStack(OUTPUT_SLOT,
			new ItemStack(recipe.get().value().getResult(null).getItem(),
			getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
	}

	private boolean hasCraftingFinished() {
		return progress >= maxProgress;
	}

	private void increaseCraftProgress() {
		progress++;
	}

	private boolean hasRecipe() {
		Optional<RecipeEntry<ShreddingRecipe>> recipe = getCurrentRecipe();

		/*if (recipe.isPresent()) {
			MiscFeatures.LOGGER.info("Shredding recipe present: true, Recipe ID: " + recipe.get().id());
		}
		else {
			//MiscFeatures.LOGGER.info("Shredding recipe present: false");
		}*/

		//MiscFeatures.LOGGER.info("Shredder at " + pos.toShortString() + ": " + hasInput + "&&" + canInsertAmountIntoOutputSlot(result) + "&&" + canInsertItemIntoOutputSlot(result.getItem()));
		return recipe.isPresent()
			&& canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
			&& canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
	}

	private Optional<RecipeEntry<ShreddingRecipe>> getCurrentRecipe() {
		//StringBuilder temp = new StringBuilder();
		SimpleInventory inv = new SimpleInventory(this.size());
		for (int i = 0; i < this.size(); i++) {
			inv.setStack(i, this.getStack(i));
			//temp.append(this.getStack(i).getItem().toString()).append(", ");
		}
		//MiscFeatures.LOGGER.info("Shredder inventory: " + temp);

		return getWorld().getRecipeManager().getFirstMatch(ShreddingRecipe.Type.INSTANCE, inv, getWorld());
	}

	private boolean canInsertItemIntoOutputSlot(Item item) {
		var outputStack = this.getStack(OUTPUT_SLOT);
		return outputStack.getItem() == item || outputStack.isEmpty();
	}

	private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
		var outputStack = this.getStack(OUTPUT_SLOT);
		return outputStack.getCount() + result.getCount() <= outputStack.getMaxCount();
	}

	private boolean isOutputSlotEmptyOrRecievable() {
		var outputStack = this.getStack(OUTPUT_SLOT);
		return outputStack.isEmpty() || outputStack.getCount() < outputStack.getMaxCount();
	}
}
