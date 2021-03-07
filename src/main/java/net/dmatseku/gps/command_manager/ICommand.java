package net.dmatseku.gps.command_manager;

import java.util.ArrayList;

public interface ICommand {
    boolean commandValidate(String name);
    boolean argumentsValidate(ArrayList<String> arguments);
    void    argumentsError(StringCommand command);
    void    execute(StringCommand command);
}
