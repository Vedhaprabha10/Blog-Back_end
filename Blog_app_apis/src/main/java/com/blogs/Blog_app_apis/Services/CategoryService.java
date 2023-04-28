package com.blogs.Blog_app_apis.Services;

import com.blogs.Blog_app_apis.Payloads.CategoryDto;
import com.blogs.Blog_app_apis.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //get all
    List<CategoryDto> getAll();

    //get by id
    CategoryDto getById(Integer categoryId);
}
