package com.example.telegraph.config;

import com.example.telegraph.exception.MyCustomException;
import com.example.telegraph.exception.PostNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
    public class PostUserExceptionHandler {

    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<Object>exception(MyCustomException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
