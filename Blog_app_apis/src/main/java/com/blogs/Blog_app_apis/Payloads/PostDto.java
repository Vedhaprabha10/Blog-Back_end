package com.blogs.Blog_app_apis.Payloads;

import com.blogs.Blog_app_apis.Entity.Comment;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {
    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto>comments=new HashSet<>();
}
