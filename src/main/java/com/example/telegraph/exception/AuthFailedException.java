package com.example.telegraph.exception;

public class AuthFailedException extends RuntimeException{
    public AuthFailedException(String message) {
        super(message);
    }
}
