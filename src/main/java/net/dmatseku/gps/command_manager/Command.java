package net.dmatseku.gps.command_manager;

import java.util.ArrayList;

public abstract class Command implements ICommand, ICommandHelp {

    protected String            commandName = "";
    protected ArgumentsBuilder  argumentsBuilder = new ArgumentsBuilder();
    protected ArrayList<String> arguments;


    @Override
    public boolean commandValidate(String name) {
        return name.equals(commandName);
    }

    @Override
    public boolean argumentsValidate(ArrayList<String> arguments) {
        try {
            this.arguments = argumentsBuilder.validateArguments(arguments);
        } catch (ArgumentsBuilder.ArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void argumentsError(StringCommand command) {
        //TODO call error
    }

    @Override
    public String getUsage() {
        return commandName;
    }

    @Override
    public String getArgumentsExplanation() {
        return null;
    }
}
