package net.dmatseku.gps.command_manager.exception;

public class CommandException extends AbstractCommandException {
    public CommandException(String message, String commandName) {
        super("GPS " + commandName + ": " + message + "\nFor help please write \"@gps help " + commandName + "\"");
    }
}
