package net.db64.miscfeatures.util;

import net.minecraft.nbt.NbtCompound;

public class SnifferRocketData {
	public static int addFlightTime(IEntityDataSaver entity, int amount) {
		NbtCompound nbt = entity.getPersistentData();
		int flightTime = nbt.getInt("flightTime");
		int flightTimeTotal = nbt.getInt("flightTimeTotal");
		if (flightTime + amount >= flightTimeTotal) {
			flightTime = flightTimeTotal;
		}
		else {
			flightTime += amount;
		}

		nbt.putInt("flightTime", flightTime);
		// Sync the data
		return flightTime;
	}
}
