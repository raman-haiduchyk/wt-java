package by.bundesliga.fan.controller.exception;

import by.bundesliga.fan.dao.impl.connection.ConnectionPoolException;

public class ControllerRuntimeException extends RuntimeException {
    public ControllerRuntimeException() {
        super();
    }

    public ControllerRuntimeException(String message) {
        super(message);
    }

    public ControllerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerRuntimeException(Throwable cause) {
        super(cause);
    }
}
