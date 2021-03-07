package net.dmatseku.gps;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.dmatseku.gps.command_manager.CommandRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.*;

public class GPS implements ModInitializer {
	@Override
	public void onInitialize() {
		CommandRegister.register();
	}
}
