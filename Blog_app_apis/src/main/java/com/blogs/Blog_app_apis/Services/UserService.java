package com.blogs.Blog_app_apis.Services;

import com.blogs.Blog_app_apis.Entity.User;
import com.blogs.Blog_app_apis.Payloads.UserDto;

import java.util.List;

public interface UserService {
  UserDto createUser(UserDto user);

  UserDto updateUser(UserDto user,Integer userId);

  UserDto getUserById(Integer userId);

  List<UserDto> getAllUsers();

  void deleteUser(Integer userId);


    }
