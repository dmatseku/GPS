package net.dmatseku.gps.command_manager;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

public class CommandChat {
    protected static MinecraftClient client = MinecraftClient.getInstance();

    public static void sendSystemMessage(String message) {
        if (client.player != null) {
            client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("\n" + message + "\n"), client.player.getUuid());
        }
    }

    public static void sendMessage(String message) {
        if (client.player != null) {
            client.inGameHud.addChatMessage(MessageType.CHAT, new LiteralText(message), client.player.getUuid());
        }
    }

    public static void globalError(String message) {
        message = "GPS error: " + message + "\nTo see all commands please write \"@gps help\"";
        sendSystemMessage(message);
    }

    public static void commandError(String message, String commandName) {
        message = "GPS " + commandName + ": " + message + "\nFor help please write \"@gps help " + commandName + "\"";
        sendSystemMessage(message);
    }

    public static void argumentError(String message, String commandName, int argumentNumber) {
        message = "GPS " + commandName + ", argument #" + argumentNumber + ": " + message
                + "\nFor help please write \"@gps help " + commandName + "\"";
        sendSystemMessage(message);
    }
}
