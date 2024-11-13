package com.datawise.social_media.service;

import com.datawise.social_media.entity.User;
import com.datawise.social_media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class Utils {

    @Autowired
    private UserRepository userRepository;

    public User fetchUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.get();
    }
}
