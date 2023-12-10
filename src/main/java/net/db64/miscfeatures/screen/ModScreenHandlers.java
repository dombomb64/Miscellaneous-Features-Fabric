package net.db64.miscfeatures.screen;

import net.db64.miscfeatures.MiscFeatures;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
	public static final ScreenHandlerType<QuartzShredderScreenHandler> QUARTZ_SHREDDER =
		Registry.register(Registries.SCREEN_HANDLER, new Identifier(MiscFeatures.MOD_ID, "quartz_shredder"),
			new ExtendedScreenHandlerType<>(QuartzShredderScreenHandler::new));

	public static void registerScreenHandlers() {
		MiscFeatures.LOGGER.debug("Registering screen handlers for Miscellaneous Features (" + MiscFeatures.MOD_ID + ")");
	}
}
