package net.db64.miscfeatures.sound;

import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
	public static SoundEvent SPIKE_BLOCK_EXTEND = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.spike_block.extend"));
	public static SoundEvent SPIKE_BLOCK_RETRACT = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.spike_block.retract"));

	public static void registerModSounds() {
		Registry.register(Registries.SOUND_EVENT, SPIKE_BLOCK_EXTEND.getId(), SPIKE_BLOCK_EXTEND);
	}
}
