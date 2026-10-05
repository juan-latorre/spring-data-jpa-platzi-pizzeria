package com.platzi.pizza.service.exception;

public class EmailApiException extends RuntimeException {
    public EmailApiException() {
        super("Pizza Price was Modified, But Error sending email...");
    }
}
