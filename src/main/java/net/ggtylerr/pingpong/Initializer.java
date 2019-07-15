// Ping Pong v1.0.0 by ggtylerr

package net.ggtylerr.pingpong;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.ggtylerr.pingpong.commands.Ping;

public class Initializer implements ModInitializer {
	@Override
	public void onInitialize() {
	    CommandRegistry.INSTANCE.register(false, Ping::register);
	}
}
