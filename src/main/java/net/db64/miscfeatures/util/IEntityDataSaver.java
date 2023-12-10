package net.db64.miscfeatures.util;

import net.minecraft.nbt.NbtCompound;

public interface IEntityDataSaver {
	public static final String dataId = "miscfeatures:data";
	NbtCompound getPersistentData();
}
