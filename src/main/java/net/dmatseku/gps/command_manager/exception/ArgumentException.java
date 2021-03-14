package net.dmatseku.gps.command_manager.exception;

public class ArgumentException extends AbstractCommandException {
    public ArgumentException(String message, String commandName, int argumentNumber, boolean showHelpMessage) {
        super("GPS " + commandName + ", argument #" + argumentNumber + ": " + message
                + (showHelpMessage ? "\nFor help please write \"@gps help " + commandName + "\"" : ""));
    }
}
