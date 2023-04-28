package com.blogs.Blog_app_apis.Controllers;

import com.blogs.Blog_app_apis.Payloads.ApiResponse;
import com.blogs.Blog_app_apis.Payloads.CategoryDto;
import com.blogs.Blog_app_apis.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    //create
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    //update
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updatedCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDto1);
    }

    //delete
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity(new ApiResponse("Category is deleted successfully", true), HttpStatus.OK);
    }
    //getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>>getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.categoryService.getAll());
    }
    //getById
    @GetMapping("/get/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto categoryDto = this.categoryService.getById(catId);
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryDto);
    }
}
