package com.moptra.hrms.exception.resources;

import java.io.Serial;

public class AppNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID =1L;

    public AppNotFoundException(String message) {
        super(message);
    }

    public AppNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppNotFoundException(Throwable cause) {
        super(cause);
    }
}
