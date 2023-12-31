package net.db64.miscfeatures.block.entity;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
	public static BlockEntityType<QuartzShredderBlockEntity> QUARTZ_SHREDDER =
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MiscFeatures.MOD_ID, "quartz_shredder"),
			FabricBlockEntityTypeBuilder.create(QuartzShredderBlockEntity::new,
				ModBlocks.QUARTZ_SHREDDER).build());

	public static void registerBlockEntities() {
		//ADVANCED_NOTE_BLOCK = Registry.register(Registries.BLOCK_ENTITY_TYPE,
		//	new Identifier(MiscFeatures.MOD_ID, "advanced_note_block"),
		//	FabricBlockEntityTypeBuilder.create(AdvancedNoteBlockEntity::new,
		//		ModBlocks.ADVANCED_NOTE_BLOCK).build(null));
	}
}
