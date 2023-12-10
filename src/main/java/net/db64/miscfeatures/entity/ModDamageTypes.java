package net.db64.miscfeatures.entity;

import net.db64.miscfeatures.MiscFeatures;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes implements DamageTypes {
	public static final RegistryKey<DamageType> SPIKE_BLOCK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(MiscFeatures.MOD_ID, "spike_block"));
	public static final RegistryKey<DamageType> ACHILLES_HEEL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(MiscFeatures.MOD_ID, "achilles_heel"));

	public static DamageSource of(World world, RegistryKey<DamageType> key) {
		return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
}
