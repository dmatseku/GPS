package net.dmatseku.gps.command_manager;

import net.dmatseku.gps.command_manager.commands.SayHello;

public class CommandRegister {
    public static void register() {
        CommandManager.getInstance().registerListener(new SayHello());
    }
}
