package net.dmatseku.gps.commands;

import net.dmatseku.gps.command_manager.*;
import net.dmatseku.gps.command_manager.ArgumentsManager.Arguments;
import net.dmatseku.gps.command_manager.ArgumentsManager.Require;
import net.dmatseku.gps.Chat;
import net.dmatseku.gps.command_manager.exception.ArgumentException;
import net.dmatseku.gps.command_manager.exception.CommandException;

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
                .addAdditionalArgumentClarification("command name", "name of the command")
                .build();
    }

    @Override
    public void execute() {
        if (this.getArgument(0) != null) {
            helpCommand(this.getArgument(0));
        } else {
            helpList();
        }
    }

    private void helpCommand(String commandName) {
        ICommand command = CommandManager.getInstance().getCommand(commandName);

        if (command != null) {
            String message = "GPS \"" + command.getCommandName() + "\" usage: " + command.getUsage();

            if (command.getArgumentsClarification() != null) {
                message += "\n\nArguments:" + command.getArgumentsClarification();
            }
            Chat.sendSystemMessage(message);
        } else {
            throw new ArgumentException("command \"" + commandName + "\" not found", this.getCommandName(), 1, false);
        }
    }

    private static void helpList() {
        ArrayList<String> list = CommandManager.getInstance().getAllCommands();
        StringBuilder message = new StringBuilder("GPS commands:");

        for (String name : list) {
            message.append("\n").append(name);
        }
        Chat.sendSystemMessage(message.toString());
    }
}
