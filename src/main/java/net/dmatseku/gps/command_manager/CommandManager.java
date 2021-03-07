package net.dmatseku.gps.command_manager;

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

    private final ArrayList<ICommand> commands = new ArrayList<ICommand>();

    void registerListener(ICommand command) {
        commands.add(command);
    }

    public boolean handleInput(String message) {
        if (inspectMessage(message)) {
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

            return true;
        }
        return false;
    }

    private ICommand getCommand(String name) {
        for (ICommand command : commands) {
            if (command.commandValidate(name)) {
                return (command);
            }
        }
        return null;
    }

    private void triggerCommand(ICommand command, StringCommand strCommand) {
        if (command.argumentsValidate(strCommand.getArguments())) {
            command.execute(strCommand);
        } else {
            command.argumentsError(strCommand);
        }
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
        //TODO: complete calling error
    }

    private static void noLoadedCommands() {
        //TODO: complete calling error
    }
}
