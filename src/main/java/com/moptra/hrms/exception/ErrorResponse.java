package com.moptra.hrms.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;

    public ErrorResponse(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public ErrorResponse(LocalDateTime timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
