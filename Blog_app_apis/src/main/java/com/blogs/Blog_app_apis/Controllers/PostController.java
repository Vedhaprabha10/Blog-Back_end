package com.blogs.Blog_app_apis.Controllers;

//import com.blogs.Blog_app_apis.Config.AppConstants;
import com.blogs.Blog_app_apis.Config.AppConstants;
import com.blogs.Blog_app_apis.Payloads.ApiResponse;
import com.blogs.Blog_app_apis.Payloads.PostDto;
import com.blogs.Blog_app_apis.Payloads.PostResponse;
import com.blogs.Blog_app_apis.Services.FileService;
import com.blogs.Blog_app_apis.Services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;
    @Value("${project.image}")
    String path;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPost);
    }

    //get by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> postByUser = this.postService.getPostByUser(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(postByUser);
    }

    //get by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.FOUND).body(postByCategory);
    }

    //get all post
    @GetMapping("/getAll")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDri", defaultValue = AppConstants.SORT_DRI, required = false) String sortDri) {
        PostResponse allPost = this.postService.getAll(pageNumber, pageSize, sortBy, sortDri);
        return ResponseEntity.status(HttpStatus.FOUND).body(allPost);
    }

    //get post by id
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postById = this.postService.getPostById(postId);
        return ResponseEntity.status(HttpStatus.FOUND).body(postById);
    }

    //delete post by id
    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post successfully deleted", true);
    }

    //updated post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPost);
    }

    //search
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("keyword") String keyword) {
        List<PostDto> result = this.postService.searchPosts(keyword);
        return ResponseEntity.status(HttpStatus.FOUND).body(result);
    }

    //post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto>uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatePost);
    }

    //get images
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException
    {
        InputStream resource= this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
