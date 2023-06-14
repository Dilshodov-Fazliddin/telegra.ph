package com.example.telegraph.config;

import com.example.telegraph.exception.DataNotFoundException;
import com.example.telegraph.exception.NotEnoughCreditsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalNotFoundException {
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<String>dataNotFoundException(DataNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(value = {NotEnoughCreditsException.class})
    public ResponseEntity<String> notEnoughCredits(NotEnoughCreditsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
