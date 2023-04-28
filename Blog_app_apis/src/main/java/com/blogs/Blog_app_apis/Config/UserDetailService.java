package com.blogs.Blog_app_apis.Config;

import com.blogs.Blog_app_apis.Entity.User;
import com.blogs.Blog_app_apis.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByName(username);
        return user.map(UserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));

    }
}
