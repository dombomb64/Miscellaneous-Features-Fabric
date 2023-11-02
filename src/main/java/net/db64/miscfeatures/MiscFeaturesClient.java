package net.db64.miscfeatures;

import net.db64.miscfeatures.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class MiscFeaturesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//ModMessages.registerS2CPackets();

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RAINBOW_EUCALYPTUS_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RAINBOW_EUCALYPTUS_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBBER_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RAINBOW_EUCALYPTUS_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPIKE_BLOCK, RenderLayer.getCutout());
	}
}
