package com.example.telegraph.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class RequestValidationException extends RuntimeException{
    private String message;

    public RequestValidationException(List<ObjectError>errors){
        StringBuilder errorMessage=new StringBuilder();

        for (ObjectError error:errors){
            errorMessage.append(error.getDefaultMessage()).append("\n");
        }
        this.message=errorMessage.toString();
    }

    @Override
    public String getMessage(){
        return message;
    }
}
