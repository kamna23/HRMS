package com.moptra.hrms.exception.resources;

public class AppInternalServerError extends RuntimeException{

    public AppInternalServerError(String message) {
        super(message);
    }

    public AppInternalServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public AppInternalServerError(Throwable cause) {
        super(cause);
    }
}
