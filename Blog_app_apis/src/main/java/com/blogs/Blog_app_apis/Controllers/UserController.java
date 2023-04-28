package com.blogs.Blog_app_apis.Controllers;

import com.blogs.Blog_app_apis.Exceptions.ResourceNotFoundException;
import com.blogs.Blog_app_apis.Payloads.ApiResponse;
import com.blogs.Blog_app_apis.Payloads.AuthRequest;
import com.blogs.Blog_app_apis.Payloads.UserDto;
import com.blogs.Blog_app_apis.Services.Impl.JwtServiceImpl;
import com.blogs.Blog_app_apis.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtServiceImpl jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    //create user
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    //update user
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    //delete user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }
    //get All user
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.userService.getAllUsers());
    }

    //get All user
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserDto> getById(@PathVariable Integer userId){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.userService.getUserById(userId));
    }

    @PostMapping("/authenticate")
    public String authenticationAndToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getEmail());
        }else {
            throw new UsernameNotFoundException("invalid user request");
        }


    }

}
