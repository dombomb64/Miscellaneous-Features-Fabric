package net.db64.miscfeatures.block.custom;

import net.db64.miscfeatures.MiscFeatures;
import net.db64.miscfeatures.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdvancedNoteBlockBlock extends Block {
	public static final EnumProperty<AdvancedInstrument> INSTRUMENT = EnumProperty.of("instrument", AdvancedInstrument.class);
	public static final BooleanProperty POWERED = Properties.POWERED;
	public static final IntProperty NOTE = IntProperty.of("note", 0, 11);
	public static final IntProperty OCTAVE = IntProperty.of("octave", 0, 5);

	public AdvancedNoteBlockBlock(Settings settings) {
		super(settings);
		this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(INSTRUMENT, AdvancedInstrument.HARP)).with(POWERED, false)).with(OCTAVE, 0)).with(NOTE, 0));
	}

	private BlockState getStateWithInstrument(WorldAccess world, BlockPos pos, BlockState state) {
		AdvancedInstrument instrument = AdvancedInstrument.fromInstrument(world.getBlockState(pos.up()).getInstrument());
		if (instrument.isNotBaseBlock()) {
			return (BlockState)state.with(INSTRUMENT, instrument);
		}
		AdvancedInstrument instrument2 = AdvancedInstrument.fromInstrument(world.getBlockState(pos.down()).getInstrument());
		AdvancedInstrument instrument3 = instrument2.isNotBaseBlock() ? AdvancedInstrument.HARP : instrument2;
		if (instrument3 == AdvancedInstrument.HARP) {
			switch (Registries.BLOCK.getId(world.getBlockState(pos.down()).getBlock()).toString()) {
				case MiscFeatures.MOD_ID + ":steel_wool":
					instrument3 = AdvancedInstrument.ELECTRIC_GUITAR;
					break;
				case MiscFeatures.MOD_ID + ":burnt_steel_wool":
					instrument3 = AdvancedInstrument.POWER_CHORD;
					break;
			}
		}
		return (BlockState)state.with(INSTRUMENT, instrument3);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getStateWithInstrument(ctx.getWorld(), ctx.getBlockPos(), this.getDefaultState());
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		boolean bl;
		boolean bl2 = bl = direction.getAxis() == Direction.Axis.Y;
		if (bl) {
			return this.getStateWithInstrument(world, pos, state);
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		boolean bl = world.isReceivingRedstonePower(pos);
		if (bl != state.get(POWERED)) {
			if (bl) {
				this.playNote(null, state, world, pos);
			}
			world.setBlockState(pos, (BlockState)state.with(POWERED, bl), Block.NOTIFY_ALL);
		}
	}

	private void playNote(@Nullable Entity entity, BlockState state, World world, BlockPos pos) {
		if (state.get(INSTRUMENT).isNotBaseBlock() || world.getBlockState(pos.up()).isAir()) {
			world.addSyncedBlockEvent(pos, this, 0, 0);
			world.emitGameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, pos);
		}
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.isIn(ItemTags.NOTEBLOCK_TOP_INSTRUMENTS) && hit.getSide() == Direction.UP) {
			return ActionResult.PASS;
		}
		if (world.isClient) {
			return ActionResult.SUCCESS;
		}
		if (player.isSneaking()) {
			//MiscFeatures.LOGGER.info("sneaking can be detected!");
			state = (BlockState)state.cycle(OCTAVE);
		}
		else {
			state = (BlockState) state.cycle(NOTE);
		}
		world.setBlockState(pos, state, Block.NOTIFY_ALL);
		this.playNote(player, state, world, pos);
		player.incrementStat(Stats.TUNE_NOTEBLOCK);
		return ActionResult.CONSUME;
	}

	@Override
	public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		if (world.isClient) {
			return;
		}
		this.playNote(player, state, world, pos);
		player.incrementStat(Stats.PLAY_NOTEBLOCK);
	}

	public static float getNotePitch(int note, int octave) {
		if (octave % 2 == 0) note -= 12;
		return (float)Math.pow(2.0, (double)(note) / 12.0);
	}

	public static int getNoteAndOctave(int note, int octave) {
		return (octave * 12) + note;
	}

	@Override
	public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
		RegistryEntry<SoundEvent> registryEntry;
		float pitch;
		int note = state.get(NOTE);
		int octave = state.get(OCTAVE);
		AdvancedInstrument instrument = state.get(INSTRUMENT);
		if (instrument.shouldSpawnNoteParticles()) {
			pitch = AdvancedNoteBlockBlock.getNotePitch(note, octave);
			//MiscFeatures.LOGGER.info("Note Sound Pitch: " + pitch);
			world.addParticle(ParticleTypes.NOTE, (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, getNoteAndOctave(note, octave) / 72.0, 0.0, 0.0);
		} else {
			pitch = 1.0f;
		}
		if (instrument.hasCustomSound()) {
			Identifier identifier = this.getCustomSound(world, pos);
			if (identifier == null) {
				return false;
			}
			registryEntry = RegistryEntry.of(SoundEvent.of(identifier));
		} else {
			registryEntry = Registries.SOUND_EVENT.getEntry(instrument.getSound(octave));
		}
		world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, registryEntry, SoundCategory.RECORDS, 3.0f, pitch, world.random.nextLong());
		return true;
	}

	@Nullable
	private Identifier getCustomSound(World world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos.up());
		if (blockEntity instanceof SkullBlockEntity) {
			SkullBlockEntity skullBlockEntity = (SkullBlockEntity)blockEntity;
			return skullBlockEntity.getNoteBlockSound();
		}
		return null;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(INSTRUMENT, POWERED, NOTE, OCTAVE);
	}

	public enum AdvancedInstrument implements StringIdentifiable {
		BANJO("banjo", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BANJO_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		BASEDRUM("basedrum", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BASEDRUM_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		BASS("bass", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BASS_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BASS_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BASS_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		BELL("bell", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BELL_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BELL_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BELL_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		BIT("bit", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BIT_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BIT_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_BIT_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		CHIME("chime", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_CHIME_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		COW_BELL("cow_bell", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_COW_BELL_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		DIDGERIDOO("didgeridoo", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_DIDGERIDOO_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		FLUTE("flute", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_FLUTE_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		GUITAR("guitar", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_GUITAR_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		HARP("harp", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_HARP_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_HARP_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_HARP_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		HAT("hat", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_HAT_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_HAT_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_HAT_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		IRON_XYLOPHONE("iron_xylophone", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_IRON_XYLOPHONE_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		PLING("pling", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_PLING_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_PLING_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_PLING_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		SNARE("snare", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_SNARE_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		XYLOPHONE("xylophone", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_XYLOPHONE_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		ELECTRIC_GUITAR("electric_guitar", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_1,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_3,
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_ELECTRIC_GUITAR_OCTAVE_5),
			AdvancedInstrument.Type.BASE_BLOCK),
		POWER_CHORD("power_chord", List.of(
			ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_1,
		                ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_3,
		                ModSounds.AdvancedNoteBlock.ADVANCED_NOTE_BLOCK_POWER_CHORD_OCTAVE_5),
		AdvancedInstrument.Type.BASE_BLOCK);

		private final String name;
		private final List<SoundEvent> sounds;
		private final AdvancedInstrument.Type type;

		private AdvancedInstrument(String name, List<SoundEvent> sounds, AdvancedInstrument.Type type) {
			this.name = name;
			this.sounds = sounds;
			this.type = type;
		}

		@Override
		public String asString() {
			return this.name;
		}

		public List<SoundEvent> getSounds() {
			return this.sounds;
		}
		public SoundEvent getSound(int octave) {
			return switch (octave) {
				default -> this.sounds.get(0);
				case 2, 3 -> this.sounds.get(1);
				case 4, 5 -> this.sounds.get(2);
			};
		}

		/**
		 * {@return whether note blocks playing this instrument should produce note particles}
		 */
		public boolean shouldSpawnNoteParticles() {
			return this.type == AdvancedInstrument.Type.BASE_BLOCK;
		}

		/**
		 * {@return whether note blocks playing this instrument should determine the sound from skulls above them}
		 */
		public boolean hasCustomSound() {
			return this.type == AdvancedInstrument.Type.CUSTOM;
		}

		public boolean isNotBaseBlock() {
			return this.type != AdvancedInstrument.Type.BASE_BLOCK;
		}

		public static AdvancedInstrument fromInstrument(Instrument instrument) {
			return switch (instrument) {
				case BANJO -> BANJO;
				case BASEDRUM -> BASEDRUM;
				case BASS -> BASS;
				case BELL -> BELL;
				case BIT -> BIT;
				case CHIME -> CHIME;
				case COW_BELL -> COW_BELL;
				case DIDGERIDOO -> DIDGERIDOO;
				case FLUTE -> FLUTE;
				case GUITAR -> GUITAR;
				default -> HARP;
				case HAT -> HAT;
				case IRON_XYLOPHONE -> IRON_XYLOPHONE;
				case PLING -> PLING;
				case SNARE -> SNARE;
				case XYLOPHONE -> XYLOPHONE;
			};
		}

		static enum Type {
			BASE_BLOCK,
			MOB_HEAD,
			CUSTOM;
		}
	}
}
