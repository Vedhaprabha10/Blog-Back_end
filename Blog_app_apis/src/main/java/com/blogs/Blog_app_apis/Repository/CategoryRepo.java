package com.blogs.Blog_app_apis.Repository;

import com.blogs.Blog_app_apis.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
