package net.dmatseku.gps.command_manager;

import net.dmatseku.gps.Chat;
import net.dmatseku.gps.command_manager.exception.AbstractCommandException;
import net.dmatseku.gps.command_manager.exception.CommandException;
import net.dmatseku.gps.command_manager.exception.GlobalException;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {

    private static CommandManager instance = null;

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }

        return instance;
    }

    private CommandManager() {}

    private final ArrayList<ICommand> commands = new ArrayList<>();

    public void registerListener(ICommand command) {
        commands.add(command);
    }

    public boolean handleInput(String message) {
        if (inspectMessage(message)) {
            try {
                StringCommand strCommand = getStringCommand(new ArrayList<>(Arrays.asList(message.split("\\s"))));

                ICommand command = this.getCommand(strCommand.getCommand());
                if (command != null) {
                    triggerCommand(command, strCommand);
                } else {
                    if (!commands.isEmpty()) {
                        invalidCommand(strCommand.getCommand());
                    } else {
                        noLoadedCommands();
                    }
                }
            } catch (AbstractCommandException e) {
                Chat.sendSystemMessage(e.getMessage());
            }

            return true;
        }
        return false;
    }

    public ICommand getCommand(String name) {
        for (ICommand command : commands) {
            if (command.commandValidate(name)) {
                return (command);
            }
        }
        return null;
    }
    public ArrayList<String> getAllCommands() {
        ArrayList<String> result = new ArrayList<>();
        for (ICommand command : commands) {
            result.add(command.getCommandName());
        }
        return result;
    }

    private void triggerCommand(ICommand command, StringCommand strCommand) {
        command.argumentsValidate(strCommand.getArguments());
        command.execute();
    }

    private static StringCommand getStringCommand(ArrayList<String> parts) {
        parts.remove(0);
        return new StringCommand(parts.toArray(new String[0]));
    }

    private static boolean inspectMessage(String message) {
        if (message.startsWith("@gps")) {
            return message.matches("@gps( .*?)?");
        }
        return false;
    }

    private static void invalidCommand(String command) {
        throw new GlobalException("Command \"" + command + "\" not found");
    }

    private static void noLoadedCommands() {
        throw new GlobalException("No loaded commands found");
    }
}
