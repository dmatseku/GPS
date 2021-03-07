package net.dmatseku.gps.command_manager;

public class CommandException extends RuntimeException {
    public CommandException(String error) {
        super(error);
    }
}
