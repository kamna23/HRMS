package com.moptra.hrms.exception.resources;

public class AppUnauthorizedAccessException extends RuntimeException{
    public AppUnauthorizedAccessException(String message) {
        super(message);
    }

    public AppUnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppUnauthorizedAccessException(Throwable cause) {
        super(cause);
    }
}
