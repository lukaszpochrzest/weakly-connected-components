package org.wcc.ui.exception;

public class NoOptionChosenException extends RuntimeException {

    public NoOptionChosenException() {
    }

    public NoOptionChosenException(String message) {
        super(message);
    }

    public NoOptionChosenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoOptionChosenException(Throwable cause) {
        super(cause);
    }

    public NoOptionChosenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
