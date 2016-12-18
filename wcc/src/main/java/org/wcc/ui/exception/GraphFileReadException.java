package org.wcc.ui.exception;

public class GraphFileReadException extends RuntimeException {

    public GraphFileReadException() {
    }

    public GraphFileReadException(String message) {
        super(message);
    }

    public GraphFileReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphFileReadException(Throwable cause) {
        super(cause);
    }

    public GraphFileReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
