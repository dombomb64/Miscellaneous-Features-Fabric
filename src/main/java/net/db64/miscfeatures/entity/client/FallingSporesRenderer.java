package net.db64.miscfeatures.entity.client;

import net.db64.miscfeatures.entity.custom.FallingSporesEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FallingSporesRenderer extends EntityRenderer<FallingSporesEntity> {
	private final BlockRenderManager blockRenderManager;

	public FallingSporesRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.shadowRadius = 0.5F;
		this.blockRenderManager = context.getBlockRenderManager();
	}

	public void render(FallingSporesEntity fallingSporesEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		BlockState blockState = fallingSporesEntity.getBlockState();
		if (blockState.getRenderType() == BlockRenderType.MODEL) {
			World world = fallingSporesEntity.getWorld();
			if (blockState != world.getBlockState(fallingSporesEntity.getBlockPos()) && blockState.getRenderType() != BlockRenderType.INVISIBLE) {
				matrixStack.push();
				BlockPos blockPos = BlockPos.ofFloored(fallingSporesEntity.getX(), fallingSporesEntity.getBoundingBox().maxY, fallingSporesEntity.getZ());
				matrixStack.translate(-0.5, 0.0, -0.5);
				this.blockRenderManager.getModelRenderer().render(world, this.blockRenderManager.getModel(blockState), blockState, blockPos, matrixStack, vertexConsumerProvider.getBuffer(RenderLayers.getMovingBlockLayer(blockState)), false, Random.create(), blockState.getRenderingSeed(fallingSporesEntity.getFallingBlockPos()), OverlayTexture.DEFAULT_UV);
				matrixStack.pop();
				super.render(fallingSporesEntity, f, g, matrixStack, vertexConsumerProvider, i);
			}
		}
	}

	public Identifier getTexture(FallingSporesEntity fallingSporesEntity) {
		return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
	}
}
