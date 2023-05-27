package com.example.telegraph.controller;

import com.example.telegraph.dto.UserDto;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/sign-up")
    public String SignUp(){
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String SignUpPage(
            @Valid @RequestBody UserDto userDto

    ){
        int add = userService.add(userDto);
        System.out.println(add);
        return "sign-in";
    }

    @GetMapping("/sign-in")
    public String signIn(){
        return "sign-in";
    }


    @PostMapping("/sign-in")
    public ResponseEntity<UserEntity> signInPage(
            @RequestParam String username,
            @RequestParam String password
    ){
        UserEntity user = userService.signIn(username, password);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
