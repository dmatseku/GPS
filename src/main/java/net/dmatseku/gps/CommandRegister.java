package net.dmatseku.gps;

import net.dmatseku.gps.command_manager.CommandManager;
import net.dmatseku.gps.commands.File;
import net.dmatseku.gps.commands.Help;

public class CommandRegister {
    public static void register() {
        CommandManager instance = CommandManager.getInstance();

        instance.registerListener(new Help());
        instance.registerListener(new File());
    }
}
