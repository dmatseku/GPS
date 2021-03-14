package net.dmatseku.gps.command_manager;

import java.util.ArrayList;

public interface ICommand {
    boolean             commandValidate(String name);
    void             argumentsValidate(ArrayList<String> arguments);
    void                execute();
    String              getCommandName();
    ArrayList<String>   getCommandAliases();
    ArrayList<String>   getArguments();
    String              getArgument(int index);
    String              getUsage();
    String              getArgumentsClarification();
}
