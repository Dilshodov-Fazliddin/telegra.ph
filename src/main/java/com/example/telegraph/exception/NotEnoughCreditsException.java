package com.example.telegraph.exception;

public class NotEnoughCreditsException extends RuntimeException {
    public NotEnoughCreditsException(String message) {
        super(message);
    }
}
