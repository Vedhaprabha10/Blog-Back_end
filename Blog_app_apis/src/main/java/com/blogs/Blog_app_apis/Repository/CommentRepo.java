package com.blogs.Blog_app_apis.Repository;

import com.blogs.Blog_app_apis.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
