package com.blogs.Blog_app_apis.Payloads;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
