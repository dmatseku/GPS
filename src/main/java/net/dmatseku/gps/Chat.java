package net.dmatseku.gps;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

public class Chat {
    protected static MinecraftClient client = MinecraftClient.getInstance();

    public static void sendSystemMessage(String message) {
        if (client.player != null) {
            client.inGameHud.addChatMessage(MessageType.SYSTEM, new LiteralText("\n" + message + "\n"), client.player.getUuid());
        }
    }

    public static void sendChatMessage(String message) {
        if (client.player != null) {
            client.player.sendChatMessage(message);
        }
    }
}
