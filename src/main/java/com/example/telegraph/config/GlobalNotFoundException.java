package com.example.telegraph.config;

import com.example.telegraph.exception.AuthFailedException;
import com.example.telegraph.exception.DataNotFoundException;
import com.example.telegraph.exception.NotEnoughCreditsException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalNotFoundException {
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<String>dataNotFoundException(DataNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(value = {NotEnoughCreditsException.class})
    public ResponseEntity<String> notEnoughCredits(NotEnoughCreditsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {PSQLException.class})
    public ResponseEntity<String> PSQLExceptionHandler(
            PSQLException exception
    ){
        return ResponseEntity.status(400).body(exception.getMessage());
    }

    @ExceptionHandler(value = {AuthFailedException.class})
    public ResponseEntity<String> failedAuthorize(AuthFailedException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
