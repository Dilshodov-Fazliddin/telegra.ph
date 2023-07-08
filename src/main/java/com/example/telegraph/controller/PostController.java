package com.example.telegraph.controller;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import com.example.telegraph.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/addPost")
    private ResponseEntity<PostEntity> addPost(
           @Valid @RequestBody PostDto postDto,
           @Valid @RequestParam UUID userId,
           BindingResult bindingResult

    ){
        return  ResponseEntity.ok(postService.add(postDto,userId,bindingResult));
    }


    @GetMapping("/searchPostByNameAndTitleOrAsc")
    private ResponseEntity<Object>searchPostByNameOrTitleOrAsc(
       @Valid @RequestParam(required = false,defaultValue = "",name ="name") String name,
       @Valid @RequestParam(required = false,defaultValue = "",name = "title") String title

    ){

        return ResponseEntity.ok(postService.searchUserPostsById(name,title));
    }


    @GetMapping("/searchUserPostsByIdSortAsc")
    private ResponseEntity<Object>getUserPostById(
            @Valid @RequestParam(defaultValue = "") UUID post_id
    ){
        return ResponseEntity.ok(postService.getUserPost(post_id));
    }


    @GetMapping("/getByUrlAndEditByUrl/{url}")
    private ResponseEntity<Object> searchByUrl(
            @Valid @PathVariable String url
    ) {
        return new ResponseEntity<> (postService.updateAndShow(url), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePost(
          @Valid @PathVariable UUID id
    ){
        postService.deletePostById(id);
        return ResponseEntity.ok().build();
    }

}
