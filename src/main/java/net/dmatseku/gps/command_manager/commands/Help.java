package net.dmatseku.gps.command_manager.commands;

import net.dmatseku.gps.command_manager.*;
import net.dmatseku.gps.command_manager.ArgumentsManager.Arguments;
import net.dmatseku.gps.command_manager.ArgumentsManager.Require;

import java.util.ArrayList;

public class Help extends Command {

    @Override
    protected String setCommandName() {
        return "help";
    }

    @Override
    protected ArgumentsManager setArguments() {
        return new ArgumentsManager().addArgument(Arguments.STRING, Require.FALSE);
    }

    @Override
    protected void setCommandAliases(ArrayList<String> aliases) {
        aliases.add("");
    }

    @Override
    public String getUsage() {
        return UsageBuilder
                .createCommandClarification(this.getCommandName())
                .addAdditionalArgument("command name")
                .build();
    }

    @Override
    public String getArgumentsClarification() {
        return UsageBuilder.createArgumentsClarification()
                .addArgumentClarification("command name", "name of the command")
                .build();
    }

    @Override
    public void execute() {
        if (this.getArguments().get(0) != null) {
            helpCommand(this.getArguments().get(0));
        } else {
            helpList();
        }
    }

    private static void helpCommand(String commandName) {
        ICommand command = CommandManager.getInstance().getCommand(commandName);

        if (command != null) {
            String message = "GPS \"" + command.getCommandName() + "\" usage: " + command.getUsage();

            if (command.getArgumentsClarification() != null) {
                message += "\n\nArguments:" + command.getArgumentsClarification();
            }
            CommandChat.sendSystemMessage(message);
        } else {
            String message = "GPS \"" + commandName + "\": command not found";
            CommandChat.sendSystemMessage(message);
        }
    }

    private static void helpList() {
        ArrayList<String> list = CommandManager.getInstance().getAllCommands();
        StringBuilder message = new StringBuilder("GPS commands:");

        for (String name : list) {
            message.append("\n").append(name);
        }
        CommandChat.sendSystemMessage(message.toString());
    }
}
