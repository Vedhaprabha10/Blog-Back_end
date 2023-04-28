package com.blogs.Blog_app_apis.Controllers;

import com.blogs.Blog_app_apis.Payloads.ApiResponse;
import com.blogs.Blog_app_apis.Payloads.CommentDto;
import com.blogs.Blog_app_apis.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {
        CommentDto comment1 = this.commentService.createComment(comment,postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment1);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }
}