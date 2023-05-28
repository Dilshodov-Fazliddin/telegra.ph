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
    private final PostService postService;

    @PostMapping("/addPost")
    private ResponseEntity<PostEntity> addPost(
        @RequestBody PostDto postDto,
        @RequestParam UUID userId
    ){
        return  ResponseEntity.ok(postService.add(postDto,userId));
    }


    @GetMapping("/searchPostByNameAndTitleOrAsc")
    private List<PostEntity>searchPostByNameOrTitleOrAsc(
        @RequestParam String name,
        @RequestParam String title
    ){
        return postService.searchUserPostsById(name,title);
    }


    @GetMapping("/searchUserPostsByIdSortAsc")
    private List<PostEntity>getUserPostById(
            @RequestParam UUID id
    ){
        return postService.getUserPost(id);
    }


}
