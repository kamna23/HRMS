package com.moptra.hrms.exception.resources;

public class AppClientErrorException extends RuntimeException{
    public AppClientErrorException(String message) {
        super(message);
    }

    public AppClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppClientErrorException(Throwable cause) {
        super(cause);
    }
}
