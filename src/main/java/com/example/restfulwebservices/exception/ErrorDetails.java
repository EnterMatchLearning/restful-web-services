package com.example.restfulwebservices.exception;

import java.time.LocalDate;

public class ErrorDetails {

    private final LocalDate timestamp;
    private final String message;
    private final String details;
    private final int status;

    public ErrorDetails(LocalDate timestamp, String message, String details, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getStatus() {
        return status;
    }
}
