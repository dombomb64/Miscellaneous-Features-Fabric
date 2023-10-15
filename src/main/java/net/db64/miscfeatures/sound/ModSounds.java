package net.db64.miscfeatures.sound;

import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModSounds {
	public static SoundEvent SPIKE_BLOCK_EXTEND = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.spike_block.extend"));
	public static SoundEvent SPIKE_BLOCK_RETRACT = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.spike_block.retract"));

	public static SoundEvent BLOCK_NOTE_BLOCK_ELECTRIC_GUITAR = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.note_block.electric_guitar"));
	public static SoundEvent BLOCK_NOTE_BLOCK_POWER_CHORD = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.note_block.power_chord"));

	public static SoundEvent CHARRABLE_BLOCK_CHAR = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.charrable.char"));

	public static void registerModSounds() {
		Registry.register(Registries.SOUND_EVENT, SPIKE_BLOCK_EXTEND.getId(), SPIKE_BLOCK_EXTEND);
		Registry.register(Registries.SOUND_EVENT, SPIKE_BLOCK_RETRACT.getId(), SPIKE_BLOCK_RETRACT);

		Registry.register(Registries.SOUND_EVENT, BLOCK_NOTE_BLOCK_ELECTRIC_GUITAR.getId(), BLOCK_NOTE_BLOCK_ELECTRIC_GUITAR);
		Registry.register(Registries.SOUND_EVENT, BLOCK_NOTE_BLOCK_POWER_CHORD.getId(), BLOCK_NOTE_BLOCK_POWER_CHORD);

		Registry.register(Registries.SOUND_EVENT, CHARRABLE_BLOCK_CHAR.getId(), CHARRABLE_BLOCK_CHAR);

		AdvancedNoteBlock.registerModSounds();
	}

	public static class AdvancedNoteBlock {
		public static SoundEvent ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.banjo.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.banjo.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.banjo.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.basedrum.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.basedrum.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.basedrum.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_BASS_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bass.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BASS_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bass.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BASS_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bass.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_BELL_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bell.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BELL_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bell.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BELL_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bell.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_BIT_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bit.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BIT_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bit.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_BIT_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.bit.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.chime.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.chime.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.chime.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.cow_bell.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.cow_bell.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.cow_bell.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.didgeridoo.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.didgeridoo.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.didgeridoo.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.flute.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.flute.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.flute.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.guitar.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.guitar.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.guitar.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_HARP_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.harp.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_HARP_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.harp.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_HARP_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.harp.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_HAT_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.hat.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_HAT_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.hat.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_HAT_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.hat.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.iron_xylophone.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.iron_xylophone.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.iron_xylophone.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_PLING_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.pling.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_PLING_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.pling.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_PLING_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.pling.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.snare.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.snare.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.snare.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.xylophone.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.xylophone.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.xylophone.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.electric_guitar.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.electric_guitar.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.electric_guitar.octave_5"));

		public static SoundEvent ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_1 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.power_chord.octave_1"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_3 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.power_chord.octave_3"));
		public static SoundEvent ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_5 = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.advanced_note_block.power_chord.octave_5"));

		public static void registerModSounds() {
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BASS_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_BASS_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BASS_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_BASS_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BASS_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_BASS_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BELL_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_BELL_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BELL_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_BELL_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BELL_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_BELL_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BIT_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_BIT_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BIT_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_BIT_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_BIT_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_BIT_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_HARP_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_HARP_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_HARP_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_HARP_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_HARP_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_HARP_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_HAT_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_HAT_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_HAT_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_HAT_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_HAT_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_HAT_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_PLING_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_PLING_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_PLING_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_PLING_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_PLING_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_PLING_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_5);

			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_1.getId(), ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_1);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_3.getId(), ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_3);
			Registry.register(Registries.SOUND_EVENT, ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_5.getId(), ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_5);
		}
	}
}
