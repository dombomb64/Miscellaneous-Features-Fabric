package net.db64.miscfeatures.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.minecraft.text.Text;

import java.util.LinkedList;
import java.util.List;

public class ShreddingCategory implements DisplayCategory<BasicDisplay> {
	//public static final Identifier TEXTURE = new Identifier(MiscFeatures.MOD_ID, "textures/gui/rei/quartz_shredder.png");
	public static final CategoryIdentifier<ShreddingDisplay> QUARTZ_SHREDDER = CategoryIdentifier.of(MiscFeatures.MOD_ID, "quartz_shredder");

	@Override
	public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
		return QUARTZ_SHREDDER;
	}

	@Override
	public Text getTitle() {
		return Text.translatable("container.miscfeatures.quartz_shredder");
	}

	@Override
	public Renderer getIcon() {
		return EntryStacks.of(ModBlocks.QUARTZ_SHREDDER);
	}

	@Override
	public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
		final Point startPoint = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 27);
		List<Widget> widgets = new LinkedList<>();

		//widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 144, 70)));
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createArrow(new Point(startPoint.x + 60, startPoint.y + 18)));
		widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 95, startPoint.y + 19)));

		var inputEntries = display.getInputEntries();
		int index = 0;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				var slot = Widgets.createSlot(new Point(startPoint.x + 1 + x * 18, startPoint.y + 1 + y * 18));
				if (index < inputEntries.size()) {
					slot.entries(inputEntries.get(index));
				}
				widgets.add(slot);

				index++;
			}
		}

		widgets.add(Widgets.createSlot(new Point(startPoint.x + 95, startPoint.y + 19))
			.entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

		return widgets;
	}

	@Override
	public int getDisplayHeight() {
		return 90;
	}
}
