package com.example.telegraph.controller;

import com.example.telegraph.dto.LoginDto;
import com.example.telegraph.dto.UserDto;
import com.example.telegraph.dto.response.JwtResponse;
import com.example.telegraph.entity.Role;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;


    @PostMapping("/sign-up")
    private ResponseEntity<UserEntity>SignUp(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.saveUser(userDto, List.of(Role.USER)));
    }

    
    @GetMapping("/sign-in")
    private ResponseEntity<JwtResponse>login(
            @RequestBody LoginDto loginDto
            ){
        return ResponseEntity.ok(userService.signIn(loginDto));
    }
}
