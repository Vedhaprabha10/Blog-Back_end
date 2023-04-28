package com.blogs.Blog_app_apis.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

    private int categoryId;
    @NotBlank
    @Size(min = 4,message = "min size of category must be 4 ")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10)
    private String categoryDescription;
}
