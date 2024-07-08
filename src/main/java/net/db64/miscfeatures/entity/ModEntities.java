package net.db64.miscfeatures.entity;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.entity.custom.FallingSporesEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
	public static final EntityType<FallingSporesEntity> FALLING_SPORES = Registry.register(Registries.ENTITY_TYPE,
		new Identifier(MiscFeatures.MOD_ID, "falling_spores"),
		FabricEntityTypeBuilder.create(SpawnGroup.MISC, FallingSporesEntity::new)
			.dimensions(EntityDimensions.fixed(1f, 1f)).build());
}
