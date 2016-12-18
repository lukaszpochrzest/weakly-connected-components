package org.wcc.ui.exception;

public class NoGraphOptionException extends RuntimeException {
    public NoGraphOptionException() {
    }

    public NoGraphOptionException(String message) {
        super(message);
    }

    public NoGraphOptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoGraphOptionException(Throwable cause) {
        super(cause);
    }

    public NoGraphOptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
