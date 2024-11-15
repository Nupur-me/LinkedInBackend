package com.linkedin.posts_service.controller;

import com.linkedin.posts_service.dto.PostCreateRequestDTO;
import com.linkedin.posts_service.dto.PostDTO;
import com.linkedin.posts_service.entity.Post;
import com.linkedin.posts_service.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody @Valid PostCreateRequestDTO postCreateRequestDTO){
        PostDTO createdPost = postsService.createPost(postCreateRequestDTO);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId){
        PostDTO postDTO = postsService.getPostById(postId);
        return ResponseEntity.ok(postDTO);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDTO>> getAllPostsOfUser(@PathVariable Long userId){
        List<PostDTO> posts = postsService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }
}
