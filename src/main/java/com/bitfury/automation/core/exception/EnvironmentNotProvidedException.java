package com.bitfury.automation.core.exception;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Exception class for cases when critically important environment parameters were not provided
 */

@SuppressWarnings("unused")
public class EnvironmentNotProvidedException extends RuntimeException {

    public EnvironmentNotProvidedException() {
        super();
    }

    public EnvironmentNotProvidedException(String message) {
        super(message);
    }

    public EnvironmentNotProvidedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnvironmentNotProvidedException(Throwable cause) {
        super(cause);
    }

    public EnvironmentNotProvidedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
