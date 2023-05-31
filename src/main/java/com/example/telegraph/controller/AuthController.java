package com.example.telegraph.controller;

import com.example.telegraph.dto.UserDto;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.exception.MyCustomException;
import com.example.telegraph.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> SignUp(
            @Valid @RequestBody UserDto userDto
            ){
        UserEntity user =userService.add(userDto);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/sign-in")
    public ResponseEntity<Object> signInPage(
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "") String password
    ){
        UserEntity user = userService.signIn(username, password);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
