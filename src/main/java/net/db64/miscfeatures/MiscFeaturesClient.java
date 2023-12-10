package net.db64.miscfeatures;

import net.db64.miscfeatures.block.ModBlocks;
import net.db64.miscfeatures.effect.ModEffects;
import net.db64.miscfeatures.item.ModItems;
import net.db64.miscfeatures.item.custom.InstantDeathPotionItem;
import net.db64.miscfeatures.screen.ModScreenHandlers;
import net.db64.miscfeatures.screen.QuartzShredderScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;

public class MiscFeaturesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//ModMessages.registerS2CPackets();

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RAINBOW_EUCALYPTUS_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RAINBOW_EUCALYPTUS_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBBER_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPIKE_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WARPED_WART, RenderLayer.getCutout());

		ColorProviderRegistryImpl.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : InstantDeathPotionItem.getColor(stack), ModItems.INSTANT_DEATH_POTION);

		HandledScreens.register(ModScreenHandlers.QUARTZ_SHREDDER, QuartzShredderScreen::new);
	}
}
