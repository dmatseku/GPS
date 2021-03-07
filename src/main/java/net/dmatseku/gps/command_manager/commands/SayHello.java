package net.dmatseku.gps.command_manager.commands;

import net.dmatseku.gps.command_manager.ArgumentsBuilder;
import net.dmatseku.gps.command_manager.Command;
import net.dmatseku.gps.command_manager.StringCommand;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

public class SayHello extends Command {

    public SayHello() {
        this.commandName = "say";
        this.argumentsBuilder
                .addArgument(ArgumentsBuilder.Arguments.INT, ArgumentsBuilder.Require.TRUE)
                .addArgument(ArgumentsBuilder.Arguments.STRING, ArgumentsBuilder.Require.FALSE);
    }

    @Override
    public void execute(StringCommand command) {
        MinecraftClient mc = MinecraftClient.getInstance();
        String result = arguments.get(0);

        if (arguments.get(1) != null) {
            result += arguments.get(1);
        }
        mc.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText(result), mc.player.getUuid());
    }
}
