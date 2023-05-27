package com.example.telegraph.controller;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.exception.MyCustomException;
import com.example.telegraph.service.PostService;
import com.example.telegraph.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final UserService userService;
    private final PostService postService;

    @PostMapping("/addTag")
    private ResponseEntity<PostEntity> addPost(
        @RequestBody PostDto postDto,
        @RequestParam UUID userId
    ){
        return  ResponseEntity.ok(postService.add(postDto,userId));
    }

    @GetMapping("/getUserPostById")
    private List<PostEntity> getUserPost(
        @RequestParam UUID postId
    ){
        return postService.getUserPostsById(postId);
    }

    @GetMapping("/searchPostByNameOrTitleOrAsc")
    private List<PostEntity>searchPostByNameOrTitleOrAsc(
        @RequestParam String name,
        @RequestParam String title
    ){
        return postService.searchUserPostsById(name,title);
    }
}
