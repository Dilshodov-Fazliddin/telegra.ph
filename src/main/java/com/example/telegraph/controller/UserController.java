package com.example.telegraph.controller;

import com.example.telegraph.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/block")
    public ResponseEntity<Boolean>blockUserById(UUID user_id){
        return ResponseEntity.ok(userService.blockUser(user_id));
    }

    @PutMapping("/unblock")
    public ResponseEntity<Boolean>UnblockUserById(UUID user_id){
        return ResponseEntity.ok(userService.unblockUser(user_id));
    }

}
