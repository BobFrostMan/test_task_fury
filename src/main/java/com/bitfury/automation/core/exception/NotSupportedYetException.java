package com.bitfury.automation.core.exception;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Runtime exception to inform about not supported functionality
 */
@SuppressWarnings("unused")
public class NotSupportedYetException extends RuntimeException {

    public NotSupportedYetException() {
        super();
    }

    public NotSupportedYetException(String message) {
        super(message);
    }

    public NotSupportedYetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportedYetException(Throwable cause) {
        super(cause);
    }

    public NotSupportedYetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
