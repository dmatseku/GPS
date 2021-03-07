package net.dmatseku.gps.command_manager;

import java.util.ArrayList;

public abstract class Command implements ICommand {

    private String                  commandName;
    private final ArrayList<String> commandAliases = new ArrayList<>();
    private final ArgumentsManager  argumentsManager;
    private ArrayList<String>       arguments;

    public Command() {
        commandName = setCommandName();
        setCommandAliases(commandAliases);
        argumentsManager = setArguments();
        if (argumentsManager == null || commandName == null) {
            throw new NullPointerException("ArgumentsManager and CommandName can't be null");
        }
    }

    protected abstract String setCommandName();

    protected abstract ArgumentsManager setArguments();

    protected void setCommandAliases(ArrayList<String> aliases) { }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public ArrayList<String>   getCommandAliases() {
        return commandAliases;
    }

    @Override
    public ArrayList<String> getArguments() {
        return arguments;
    }

    @Override
    public String getArgument(int index) {
        if (index < arguments.size()) {
            return arguments.get(index);
        }
        return null;
    }

    @Override
    public boolean commandValidate(String name) {
        if (name.equals(commandName)) {
            return true;
        } else {
            for (String alias : commandAliases) {
                if (alias.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean argumentsValidate(ArrayList<String> arguments) {
        try {
            this.arguments = argumentsManager.validateArguments(arguments);
        } catch (ArgumentsManager.ArgumentException e) {
            CommandChat.commandError(e.getMessage(), commandName);
            return false;
        }
        return true;
    }
}
