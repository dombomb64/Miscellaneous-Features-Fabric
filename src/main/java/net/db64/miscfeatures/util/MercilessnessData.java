package net.db64.miscfeatures.util;

import net.db64.miscfeatures.effect.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class MercilessnessData {
	public static int addComboBreakTicks(IEntityDataSaver player, int amount) {
		NbtCompound nbt = player.getPersistentData();
		int comboBreakTicks = nbt.getInt("mercilessnessComboBreakTicks");

		int maxTicks = getMaxComboBreakTicks(player);
		if (comboBreakTicks + amount > maxTicks) {
			comboBreakTicks = maxTicks;
		}
		else {
			comboBreakTicks += amount;
		}

		nbt.putInt("mercilessnessComboBreakTicks", comboBreakTicks);
		//syncComboBreakTicks(comboBreakTicks, (ServerPlayerEntity)player);
		return comboBreakTicks;
	}

	public static int removeComboBreakTicks(IEntityDataSaver player, int amount) {
		NbtCompound nbt = player.getPersistentData();
		int comboBreakTicks = nbt.getInt("mercilessnessComboBreakTicks");

		if (comboBreakTicks - amount < 0) {
			comboBreakTicks = 0;
		}
		else {
			comboBreakTicks -= amount;
		}

		nbt.putInt("mercilessnessComboBreakTicks", comboBreakTicks);
		//syncComboBreakTicks(comboBreakTicks, (ServerPlayerEntity)player);
		return comboBreakTicks;
	}

	public static int setComboBreakTicksToMax(IEntityDataSaver player) {
		NbtCompound nbt = player.getPersistentData();
		var comboBreakTicks = getMaxComboBreakTicks(player);

		nbt.putInt("mercilessnessComboBreakTicks", comboBreakTicks);
		//syncComboBreakTicks(comboBreakTicks, (ServerPlayerEntity)player);
		return comboBreakTicks;
	}

	public static int addCombo(IEntityDataSaver player, int amount) {
		NbtCompound nbt = player.getPersistentData();
		int combo = nbt.getInt("mercilessnessCombo");

		if (combo + amount > 100) {
			combo = 100;
		}
		else {
			combo += amount;
		}

		nbt.putInt("mercilessnessCombo", combo);
		//syncComboBreakTicks(combo, (ServerPlayerEntity)player);
		return combo;
	}

	public static int removeCombo(IEntityDataSaver player, int amount) {
		NbtCompound nbt = player.getPersistentData();
		int combo = nbt.getInt("mercilessnessCombo");

		if (combo - amount < 0) {
			combo = 0;
		}
		else {
			combo -= amount;
		}

		nbt.putInt("mercilessnessCombo", combo);
		//syncComboBreakTicks(combo, (ServerPlayerEntity)player);
		return combo;
	}

	public static void breakCombo(IEntityDataSaver player) {
		var nbt = player.getPersistentData();
		nbt.putInt("mercilessnessCombo", 0);
	}

	public static int getMaxComboBreakTicks(IEntityDataSaver player) {
		if (!(player instanceof PlayerEntity))
			return 0;
		var effect = ((PlayerEntity)player).getStatusEffect(ModEffects.MERCILESSNESS);
		if (effect == null)
			return 0;
		return 4 * effect.getAmplifier() + 8; // Starts at 8 and increases by 4 every level
	}
}
