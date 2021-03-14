package net.dmatseku.gps.command_manager.exception;

public abstract class AbstractCommandException extends RuntimeException {
    public AbstractCommandException(String message) {
        super(message);
    }
}
