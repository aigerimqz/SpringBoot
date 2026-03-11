package kz.kbtu.sis6.post.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.kbtu.sis6.post.dto.CreatePostRequest;
import kz.kbtu.sis6.post.dto.PostResponse;
import kz.kbtu.sis6.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Post API", description = "Endpoints for creating and retrieving posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "Publish a new post")
    @ApiResponse(responseCode = "201", description = "Post published successfully")
    @ApiResponse(responseCode = "400", description = "Validation error - userId or content invalid")
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestBody @Valid CreatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(request));
    }


    @Operation(summary = "Get post by ID")
    @ApiResponse(responseCode = "200", description = "Post found")
    @ApiResponse(responseCode = "404", description = "Post not found")
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(
            @Parameter(description = "UUID of the post", example = "abc-123")
            @PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }
}
