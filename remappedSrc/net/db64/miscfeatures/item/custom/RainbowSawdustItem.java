package net.db64.miscfeatures.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RainbowSawdustItem extends Item {
	public RainbowSawdustItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.translatable("item.miscfeatures.rainbow_sawdust.tooltip").formatted(Formatting.BLUE));
		super.appendTooltip(stack, world, tooltip, context);
	}
}
