package com.moptra.hrms.exception.handler;

import com.moptra.hrms.exception.ErrorResponse;
import com.moptra.hrms.exception.resources.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            AppBadRequestException.class,
            AppUnauthorizedAccessException.class,
            AppNotFoundException.class,
            AppClientErrorException.class,
            AppInternalServerError.class
    })
    public ResponseEntity<ErrorResponse> handleAppException(HttpServletRequest request, Exception exception) {
        HttpStatus status;
        switch (exception) {
            case AppBadRequestException appBadRequestException -> status = HttpStatus.BAD_GATEWAY;
            case AppClientErrorException appClientErrorException -> status = HttpStatus.UNPROCESSABLE_ENTITY;
            case AppNotFoundException appNotFoundException -> status = HttpStatus.NOT_FOUND;
            case AppUnauthorizedAccessException appUnauthorizedAccessException -> status = HttpStatus.UNAUTHORIZED;

            default -> {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                exception = new AppInternalServerError("Unexpected Internal Server Error");
            }
        }

        var errorResponse = new ErrorResponse(LocalDateTime.now(), status.value(), exception.getMessage());
        return ResponseEntity.status(status).body(errorResponse);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAnyUnhandledException(HttpServletRequest request, Exception exception){
        var errorResponse =new ErrorResponse(LocalDateTime.now(),"Unexpected Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


}
