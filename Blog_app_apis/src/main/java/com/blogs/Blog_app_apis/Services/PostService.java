package com.blogs.Blog_app_apis.Services;

import com.blogs.Blog_app_apis.Entity.Post;
import com.blogs.Blog_app_apis.Payloads.PostDto;
import com.blogs.Blog_app_apis.Payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAll
    PostResponse getAll(Integer pageNumber, Integer pageSize,String sortBy,String sortDri);

    //get post by id
    PostDto getPostById(Integer postId);

    //get all posts by category
    List<PostDto>getPostByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostByUser(Integer userId);

    //search post
    List<PostDto>searchPosts(String keyword);



}
