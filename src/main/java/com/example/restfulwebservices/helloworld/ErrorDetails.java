package com.example.restfulwebservices.helloworld;

import java.time.LocalDate;

public class ErrorDetails {

    private LocalDate timestamp;
    private String message;
    private String details;
    private int status;

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
