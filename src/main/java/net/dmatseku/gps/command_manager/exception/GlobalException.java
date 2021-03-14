package net.dmatseku.gps.command_manager.exception;

public class GlobalException extends AbstractCommandException {
    public GlobalException(String message) {
        super("GPS error: " + message + "\nTo see all commands please write \"@gps help\"");
    }
}
