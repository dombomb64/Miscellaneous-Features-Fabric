package net.db64.miscfeatures.entity.client;

import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
	public static final EntityModelLayer FALLING_SPORES =
		new EntityModelLayer(new Identifier(MiscFeatures.MOD_ID, "falling_spores"), "main");
}
