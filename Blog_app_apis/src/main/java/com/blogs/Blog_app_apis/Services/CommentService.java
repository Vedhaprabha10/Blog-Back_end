package com.blogs.Blog_app_apis.Services;

import com.blogs.Blog_app_apis.Payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
