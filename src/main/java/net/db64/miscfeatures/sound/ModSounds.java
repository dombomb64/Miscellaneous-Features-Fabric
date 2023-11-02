package net.db64.miscfeatures.sound;

import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
	public static SoundEvent SPIKE_BLOCK_EXTEND = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.spike_block.extend"));
	public static SoundEvent SPIKE_BLOCK_RETRACT = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.spike_block.retract"));

	public static SoundEvent BLOCK_NOTE_BLOCK_ELECTRIC_GUITAR = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.note_block.electric_guitar"));
	public static SoundEvent BLOCK_NOTE_BLOCK_POWER_CHORD = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.note_block.power_chord"));

	public static SoundEvent CHARRABLE_BLOCK_CHAR = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.charrable.char"));

	public static SoundEvent RUBBER_WOOD_BREAK = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubber_wood.break"));
	public static SoundEvent RUBBER_WOOD_STEP = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubber_wood.step"));
	public static SoundEvent RUBBER_WOOD_PLACE = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubber_wood.place"));
	public static SoundEvent RUBBER_WOOD_HIT = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubber_wood.hit"));
	public static SoundEvent RUBBER_WOOD_FALL = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubber_wood.fall"));

	public static SoundEvent RUBBERWOOD_DOOR_CLOSE = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_door.close"));
	public static SoundEvent RUBBERWOOD_DOOR_OPEN = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_door.open"));
	public static SoundEvent RUBBERWOOD_TRAPDOOR_CLOSE = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_trapdoor.close"));
	public static SoundEvent RUBBERWOOD_TRAPDOOR_OPEN = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_trapdoor.open"));
	public static SoundEvent RUBBERWOOD_PRESSURE_PLATE_CLICK_OFF = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_pressure_plate.click_off"));
	public static SoundEvent RUBBERWOOD_PRESSURE_PLATE_CLICK_ON = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_pressure_plate.click_on"));
	public static SoundEvent RUBBERWOOD_BUTTON_CLICK_OFF = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_button.click_off"));
	public static SoundEvent RUBBERWOOD_BUTTON_CLICK_ON = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_button.click_on"));
	public static SoundEvent RUBBERWOOD_FENCE_GATE_CLOSE = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_fence_gate.close"));
	public static SoundEvent RUBBERWOOD_FENCE_GATE_OPEN = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rubberwood_fence_gate.open"));

	public static SoundEvent RAINBOW_EUCALYPTUS_WOOD_BREAK = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_wood.break"));
	public static SoundEvent RAINBOW_EUCALYPTUS_WOOD_STEP = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_wood.step"));
	public static SoundEvent RAINBOW_EUCALYPTUS_WOOD_PLACE = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_wood.place"));
	public static SoundEvent RAINBOW_EUCALYPTUS_WOOD_HIT = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_wood.hit"));
	public static SoundEvent RAINBOW_EUCALYPTUS_WOOD_FALL = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_wood.fall"));

	public static SoundEvent RAINBOW_EUCALYPTUS_DOOR_CLOSE = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_door.close"));
	public static SoundEvent RAINBOW_EUCALYPTUS_DOOR_OPEN = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_door.open"));
	public static SoundEvent RAINBOW_EUCALYPTUS_TRAPDOOR_CLOSE = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_trapdoor.close"));
	public static SoundEvent RAINBOW_EUCALYPTUS_TRAPDOOR_OPEN = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_trapdoor.open"));
	public static SoundEvent RAINBOW_EUCALYPTUS_PRESSURE_PLATE_CLICK_OFF = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_pressure_plate.click_off"));
	public static SoundEvent RAINBOW_EUCALYPTUS_PRESSURE_PLATE_CLICK_ON = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_pressure_plate.click_on"));
	public static SoundEvent RAINBOW_EUCALYPTUS_BUTTON_CLICK_OFF = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_button.click_off"));
	public static SoundEvent RAINBOW_EUCALYPTUS_BUTTON_CLICK_ON = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_button.click_on"));
	public static SoundEvent RAINBOW_EUCALYPTUS_FENCE_GATE_CLOSE = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_fence_gate.close"));
	public static SoundEvent RAINBOW_EUCALYPTUS_FENCE_GATE_OPEN = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "block.rainbow_eucalyptus_fence_gate.open"));

	public static SoundEvent SNIFFER_SCREAM = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "entity.sniffer.scream"));
	public static SoundEvent SNIFFER_LAUNCH = SoundEvent.of(new Identifier(MiscFeatures.MOD_ID, "entity.sniffer.launch"));

	public static void registerModSounds() {
		Registry.register(Registries.SOUND_EVENT, SPIKE_BLOCK_EXTEND.getId(), SPIKE_BLOCK_EXTEND);
		Registry.register(Registries.SOUND_EVENT, SPIKE_BLOCK_RETRACT.getId(), SPIKE_BLOCK_RETRACT);

		Registry.register(Registries.SOUND_EVENT, BLOCK_NOTE_BLOCK_ELECTRIC_GUITAR.getId(), BLOCK_NOTE_BLOCK_ELECTRIC_GUITAR);
		Registry.register(Registries.SOUND_EVENT, BLOCK_NOTE_BLOCK_POWER_CHORD.getId(), BLOCK_NOTE_BLOCK_POWER_CHORD);

		Registry.register(Registries.SOUND_EVENT, CHARRABLE_BLOCK_CHAR.getId(), CHARRABLE_BLOCK_CHAR);

		Registry.register(Registries.SOUND_EVENT, RUBBER_WOOD_BREAK.getId(), RUBBER_WOOD_BREAK);
		Registry.register(Registries.SOUND_EVENT, RUBBER_WOOD_STEP.getId(), RUBBER_WOOD_STEP);
		Registry.register(Registries.SOUND_EVENT, RUBBER_WOOD_PLACE.getId(), RUBBER_WOOD_PLACE);
		Registry.register(Registries.SOUND_EVENT, RUBBER_WOOD_HIT.getId(), RUBBER_WOOD_HIT);
		Registry.register(Registries.SOUND_EVENT, RUBBER_WOOD_FALL.getId(), RUBBER_WOOD_FALL);

		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_DOOR_CLOSE.getId(), RUBBERWOOD_DOOR_CLOSE);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_DOOR_OPEN.getId(), RUBBERWOOD_DOOR_OPEN);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_TRAPDOOR_CLOSE.getId(), RUBBERWOOD_TRAPDOOR_CLOSE);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_TRAPDOOR_OPEN.getId(), RUBBERWOOD_TRAPDOOR_OPEN);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_PRESSURE_PLATE_CLICK_OFF.getId(), RUBBERWOOD_PRESSURE_PLATE_CLICK_OFF);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_PRESSURE_PLATE_CLICK_ON.getId(), RUBBERWOOD_PRESSURE_PLATE_CLICK_ON);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_BUTTON_CLICK_OFF.getId(), RUBBERWOOD_BUTTON_CLICK_OFF);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_BUTTON_CLICK_ON.getId(), RUBBERWOOD_BUTTON_CLICK_ON);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_FENCE_GATE_CLOSE.getId(), RUBBERWOOD_FENCE_GATE_CLOSE);
		Registry.register(Registries.SOUND_EVENT, RUBBERWOOD_FENCE_GATE_OPEN.getId(), RUBBERWOOD_FENCE_GATE_OPEN);

		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_WOOD_BREAK.getId(), RAINBOW_EUCALYPTUS_WOOD_BREAK);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_WOOD_STEP.getId(), RAINBOW_EUCALYPTUS_WOOD_STEP);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_WOOD_PLACE.getId(), RAINBOW_EUCALYPTUS_WOOD_PLACE);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_WOOD_HIT.getId(), RAINBOW_EUCALYPTUS_WOOD_HIT);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_WOOD_FALL.getId(), RAINBOW_EUCALYPTUS_WOOD_FALL);

		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_DOOR_CLOSE.getId(), RAINBOW_EUCALYPTUS_DOOR_CLOSE);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_DOOR_OPEN.getId(), RAINBOW_EUCALYPTUS_DOOR_OPEN);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_TRAPDOOR_CLOSE.getId(), RAINBOW_EUCALYPTUS_TRAPDOOR_CLOSE);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_TRAPDOOR_OPEN.getId(), RAINBOW_EUCALYPTUS_TRAPDOOR_OPEN);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_PRESSURE_PLATE_CLICK_OFF.getId(), RAINBOW_EUCALYPTUS_PRESSURE_PLATE_CLICK_OFF);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_PRESSURE_PLATE_CLICK_ON.getId(), RAINBOW_EUCALYPTUS_PRESSURE_PLATE_CLICK_ON);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_BUTTON_CLICK_OFF.getId(), RAINBOW_EUCALYPTUS_BUTTON_CLICK_OFF);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_BUTTON_CLICK_ON.getId(), RAINBOW_EUCALYPTUS_BUTTON_CLICK_ON);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_FENCE_GATE_CLOSE.getId(), RAINBOW_EUCALYPTUS_FENCE_GATE_CLOSE);
		Registry.register(Registries.SOUND_EVENT, RAINBOW_EUCALYPTUS_FENCE_GATE_OPEN.getId(), RAINBOW_EUCALYPTUS_FENCE_GATE_OPEN);

		Registry.register(Registries.SOUND_EVENT, SNIFFER_SCREAM.getId(), SNIFFER_SCREAM);
		Registry.register(Registries.SOUND_EVENT, SNIFFER_LAUNCH.getId(), SNIFFER_LAUNCH);

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

	public static class BlockSoundGroups {
		public static final BlockSoundGroup RUBBER_WOOD = new BlockSoundGroup(1.0f, 1.0f, RUBBER_WOOD_BREAK, RUBBER_WOOD_STEP, RUBBER_WOOD_PLACE, RUBBER_WOOD_HIT, RUBBER_WOOD_FALL);
		public static final BlockSoundGroup RAINBOW_EUCALYPTUS_WOOD = new BlockSoundGroup(1.0f, 1.0f, RAINBOW_EUCALYPTUS_WOOD_BREAK, RAINBOW_EUCALYPTUS_WOOD_STEP, RAINBOW_EUCALYPTUS_WOOD_PLACE, RAINBOW_EUCALYPTUS_WOOD_HIT, RAINBOW_EUCALYPTUS_WOOD_FALL);
	}
}
