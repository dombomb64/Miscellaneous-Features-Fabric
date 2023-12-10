package net.db64.miscfeatures.screen;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.entity.QuartzShredderBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;

public class QuartzShredderScreenHandler extends ScreenHandler {
	/*public static final int RESULT_ID = 0;
	private static final int INPUT_START = 1;
	private static final int INPUT_END = 10;
	private static final int INVENTORY_START = 10;
	private static final int INVENTORY_END = 37;
	private static final int HOTBAR_START = 37;
	private static final int HOTBAR_END = 46;
	private final RecipeInputInventory input = new CraftingInventory(this, 3, 3);
	private final CraftingResultInventory result = new CraftingResultInventory();
	private final ScreenHandlerContext context;
	private final PlayerEntity player;*/

	private final Inventory inventory;
	private final PropertyDelegate propertyDelegate;
	public final QuartzShredderBlockEntity blockEntity;

	public QuartzShredderScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
		this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
			new ArrayPropertyDelegate(10));
	}

	public QuartzShredderScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
		super(ModScreenHandlers.QUARTZ_SHREDDER, syncId);
		checkSize(((Inventory)blockEntity), 10);
		this.inventory = ((Inventory) blockEntity);
		inventory.onOpen(playerInventory.player);
		this.propertyDelegate = propertyDelegate;
		this.blockEntity = ((QuartzShredderBlockEntity)blockEntity);

		int slotSpacing = 18;
		int gridX = 30;
		int gridY = 17;
		int index = 0;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				this.addSlot(new Slot(inventory, index, gridX + x * slotSpacing, gridY + y * slotSpacing));
				index++;
			}
		}
		this.addSlot(new Slot(inventory, index, 124, 35));

		addPlayerInventory(playerInventory);

		addProperties(propertyDelegate);
	}

	private void addPlayerInventory (PlayerInventory playerInventory) {
		int slotSpacing = 18;
		int gridX = 8;
		int gridY = 84;
		int index = 9;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlot(new Slot(playerInventory, index, gridX + x * slotSpacing, gridY + y * slotSpacing));
				index++;
			}
		}
		int hotbarX = 8;
		int hotbarY = 142;
		for (int i = 0; i < 9; i++) {
			this.addSlot(new Slot(playerInventory, i, hotbarX + i * slotSpacing, hotbarY));
		}
	}

	/*public QuartzShredderScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(ScreenHandlerType.CRAFTING, syncId);
		int j;
		int i;
		this.context = context;
		this.player = playerInventory.player;
		this.addSlot(new CraftingResultSlot(playerInventory.player, this.input, this.result, 0, 124, 35));
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 3; ++j) {
				this.addSlot(new Slot(this.input, j + i * 3, 30 + j * 18, 17 + i * 18));
			}
		}
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
		}
	}*/

	/*protected static void updateResult(ScreenHandler handler, World world, PlayerEntity player, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory) {
		if (world.isClient) {
			return;
		}
		ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
		ItemStack itemStack = ItemStack.EMPTY;
		Optional<RecipeEntry<ShreddingRecipe>> optional = world.getServer().getRecipeManager().getFirstMatch(ModRecipeTypes.SHREDDING, craftingInventory, world);
		if (optional.isPresent()) {
			ItemStack itemStack2;
			RecipeEntry<ShreddingRecipe> recipeEntry = optional.get();
			ShreddingRecipe craftingRecipe = recipeEntry.value();
			if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, recipeEntry) && (itemStack2 = craftingRecipe.craft(craftingInventory, world.getRegistryManager())).isItemEnabled(world.getEnabledFeatures())) {
				itemStack = itemStack2;
			}
		}
		resultInventory.setStack(0, itemStack);
		handler.setPreviousTrackedSlot(0, itemStack);
		serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 0, itemStack));
	}*/

	/*@Override
	public void onContentChanged(Inventory inventory) {
		this.context.run((world, pos) -> QuartzShredderScreenHandler.updateResult(this, world, this.player, this.input, this.result));
	}

	@Override
	public void populateRecipeFinder(RecipeMatcher finder) {
		this.input.provideRecipeInputs(finder);
	}

	@Override
	public void clearCraftingSlots() {
		this.input.clear();
		this.result.clear();
	}

	@Override
	public boolean matches(RecipeEntry<? extends Recipe<RecipeInputInventory>> recipe) {
		return recipe.value().matches(this.input, this.player.getWorld());
	}

	@Override
	public void onClosed(PlayerEntity player) {
		super.onClosed(player);
		this.context.run((world, pos) -> this.dropInventory(player, this.input));
	}*/

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}

	public boolean isCrafting() {
		return propertyDelegate.get(0) > 0;
	}

	public int getScaledProgress() {
		int progress = this.propertyDelegate.get(0);
		int maxProgress = this.propertyDelegate.get(1);
		int progressArrowSize = 24;

		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}

	@Override
	public ItemStack quickMove(PlayerEntity player, int invSlot) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(invSlot);
		if (slot != null && slot.hasStack()) {
			ItemStack originalStack = slot.getStack();
			newStack = originalStack.copy();
			if (invSlot < this.inventory.size()) {
				if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
				return ItemStack.EMPTY;
			}

			if (originalStack.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			}
			else {
				slot.markDirty();
			}
		}

		return newStack;
	}

	/*@Override
	public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.result && super.canInsertIntoSlot(stack, slot);
	}

	@Override
	public int getCraftingResultSlotIndex() {
		return 0;
	}

	@Override
	public int getCraftingWidth() {
		return this.input.getWidth();
	}

	@Override
	public int getCraftingHeight() {
		return this.input.getHeight();
	}

	@Override
	public int getCraftingSlotCount() {
		return 10;
	}

	@Override
	public RecipeBookCategory getCategory() {
		return RecipeBookCategory.CRAFTING;
	}

	@Override
	public boolean canInsertIntoSlot(int index) {
		return index != this.getCraftingResultSlotIndex();
	}*/
}
