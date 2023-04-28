package com.blogs.Blog_app_apis.Services.Impl;

import com.blogs.Blog_app_apis.Entity.User;
import com.blogs.Blog_app_apis.Exceptions.ResourceNotFoundException;
import com.blogs.Blog_app_apis.Payloads.UserDto;
import com.blogs.Blog_app_apis.Repository.UserRepo;
import com.blogs.Blog_app_apis.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

   @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user1 = this.dtoToUser(userDto);
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        User savedUser = this.userRepo.save(user1);
        return this.userToDto(savedUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user1 = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(userDto.getPassword());
        user1.setAbout(userDto.getAbout());
        user1.setRoles(userDto.getRoles());

        User updatedSave = this.userRepo.save(user1);
        UserDto userDto1 = this.userToDto(updatedSave);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = this.userRepo.findAll();
        List<UserDto> userDto = userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;

    }
}
