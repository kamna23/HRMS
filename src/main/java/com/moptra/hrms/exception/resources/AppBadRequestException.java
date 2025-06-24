package com.moptra.hrms.exception.resources;

public class AppBadRequestException extends RuntimeException {
    public AppBadRequestException(Throwable cause) {
        super(cause);
    }

    public AppBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppBadRequestException(String message) {
        super(message);
    }
}
