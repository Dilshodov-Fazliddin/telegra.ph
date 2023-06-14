package com.example.telegraph.controller;

import com.example.telegraph.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/block")
    public ResponseEntity<Boolean>blockUserById(@RequestParam UUID user_id){
        return ResponseEntity.ok(userService.blockUser(user_id));
    }

    @PutMapping("/unblock")
    public ResponseEntity<Boolean>UnblockUserById(@RequestParam UUID user_id){
        return ResponseEntity.ok(userService.unblockUser(user_id));
    }

}
