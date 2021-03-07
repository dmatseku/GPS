package net.dmatseku.gps.command_manager;

import java.util.ArrayList;
import java.util.Arrays;

public class StringCommand {
    private String command = "";
    private ArrayList<String> arguments = new ArrayList<>();

    public StringCommand(String[] parts) {
        if (parts.length != 0) {
            command = parts[0];
        }

        if (parts.length > 1) {
            arguments = new ArrayList<String>(Arrays.asList(parts));
            arguments.remove(0);
        }
    }

    public String getCommand() {
        return command;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String getArgument(int index) {
        if (index >= arguments.size()) {
            return null;
        }

        return arguments.get(index);
    }
}
