package com.example.telegraph.exception;

import java.io.Serial;

public class MyCustomException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public MyCustomException(String message) {
        super(message);
    }
}
