package com.blogs.Blog_app_apis.Payloads;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 char")
    private String name;
    @Email(message = "Email address is not valid...")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 10,message = "password must be in the range")
    private String password;
    @NotEmpty
    private String about;
    private String roles;
}
