package net.db64.miscfeatures.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class QuartzShredderScreen extends HandledScreen<QuartzShredderScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(MiscFeatures.MOD_ID, "textures/gui/container/quartz_shredder.png");
	private static final Identifier PROGRESS_TEXTURE = new Identifier(MiscFeatures.MOD_ID, "textures/gui/sprites/container/quartz_shredder/shred_progress.png");

	public QuartzShredderScreen(QuartzShredderScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Override
	protected void init() {
		super.init();
		//titleY = 1000;
		//playerInventoryTitleY = 1000;
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexProgram);
		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;

		context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

		renderProgressArrow(context, x, y);
	}

	private void renderProgressArrow(DrawContext context, int x, int y) {
		if (handler.isCrafting()) {
			context.drawTexture(PROGRESS_TEXTURE, x + 89, y + 34, 0, 0, handler.getScaledProgress(), 16, 24, 16);
		}
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		renderBackground(context, mouseX, mouseY, delta);
		super.render(context, mouseX, mouseY, delta);
		drawMouseoverTooltip(context, mouseX, mouseY);
	}
}
