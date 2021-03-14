package net.dmatseku.gps;

import net.dmatseku.gps.command_manager.CommandManager;
import net.dmatseku.gps.command_manager.exception.AbstractCommandException;
import net.dmatseku.gps.commands.File;
import net.dmatseku.gps.commands.Help;

public class CommandRegister {
    public static void register() {
        CommandManager instance = CommandManager.getInstance();

        try {
            instance.registerListener(new Help());
            instance.registerListener(new File());
        } catch (AbstractCommandException e) {
            Chat.sendSystemMessage(e.getMessage());
        }
    }
}
