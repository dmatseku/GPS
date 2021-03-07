package net.dmatseku.gps;

import net.fabricmc.api.ModInitializer;

public class GPS implements ModInitializer {
	@Override
	public void onInitialize() {
		CommandRegister.register();
	}
}
