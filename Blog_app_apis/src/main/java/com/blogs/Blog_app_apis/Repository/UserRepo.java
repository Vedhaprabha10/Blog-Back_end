package com.blogs.Blog_app_apis.Repository;

import com.blogs.Blog_app_apis.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByName(String username);
}
