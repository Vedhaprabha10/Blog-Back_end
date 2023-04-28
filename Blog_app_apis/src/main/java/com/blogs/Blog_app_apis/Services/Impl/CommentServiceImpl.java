package com.blogs.Blog_app_apis.Services.Impl;

import com.blogs.Blog_app_apis.Entity.Comment;
import com.blogs.Blog_app_apis.Entity.Post;
import com.blogs.Blog_app_apis.Exceptions.ResourceNotFoundException;
import com.blogs.Blog_app_apis.Payloads.CommentDto;
import com.blogs.Blog_app_apis.Repository.CommentRepo;
import com.blogs.Blog_app_apis.Repository.PostRepo;
import com.blogs.Blog_app_apis.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment saveComment = this.commentRepo.save(comment);
        return this.modelMapper.map(saveComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
        this.commentRepo.delete(com);
    }
}
