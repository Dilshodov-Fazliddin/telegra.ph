package com.example.telegraph.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
    public class PostUserExceptionHandler {

    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<Object>exception(MyCustomException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
