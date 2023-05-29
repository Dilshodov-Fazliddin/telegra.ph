package com.example.telegraph.controller;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import com.example.telegraph.service.PostService;
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
        @RequestParam(required = false,defaultValue = "",name ="name") String name,
        @RequestParam(required = false,defaultValue = "",name = "title") String title
    ){
        return postService.searchUserPostsById(name,title);
    }


    @GetMapping("/searchUserPostsByIdSortAsc")
    private List<PostEntity>getUserPostById(
            @RequestParam UUID post_id
    ){
        return postService.getUserPost(post_id);
    }


    @GetMapping("/getByUrlAndEditByUrl/{url}")
    private List<PostEntity> searchByUrl(
            @PathVariable String url

    ){
        return postService.updateAndShow(url);
    }

}
