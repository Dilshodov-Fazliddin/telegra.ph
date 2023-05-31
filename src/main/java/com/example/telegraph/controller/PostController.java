package com.example.telegraph.controller;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import com.example.telegraph.exception.PostNotCreatedException;
import com.example.telegraph.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
    private ResponseEntity<Object>searchPostByNameOrTitleOrAsc(
        @RequestParam(required = false,defaultValue = "",name ="name") String name,
        @RequestParam(required = false,defaultValue = "",name = "title") String title

    ){

        return ResponseEntity.ok(postService.searchUserPostsById(name,title));
    }


    @GetMapping("/searchUserPostsByIdSortAsc")
    private ResponseEntity<Object>getUserPostById(
            @RequestParam(defaultValue = "") UUID post_id
    ){
        return ResponseEntity.ok(postService.getUserPost(post_id));
    }


    @GetMapping("/getByUrlAndEditByUrl/{url}")
    private ResponseEntity<Object> searchByUrl(
            @PathVariable String url
    ) {
        return new ResponseEntity<> (postService.updateAndShow(url), HttpStatus.OK);
    }
}
