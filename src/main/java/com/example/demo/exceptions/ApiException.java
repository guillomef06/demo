package com.example.demo.exceptions;


import java.time.ZonedDateTime;

public class ApiException {
    private final String message;

    private final ZonedDateTime zonedDateTime;

    public ApiException(String message, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.zonedDateTime = zonedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
