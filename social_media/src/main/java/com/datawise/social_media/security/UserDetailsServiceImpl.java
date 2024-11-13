package com.datawise.social_media.security;

import com.datawise.social_media.dto.RegisterRequest;
import com.datawise.social_media.entity.Role;
import com.datawise.social_media.entity.User;
import com.datawise.social_media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.EnumSet;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByEmail(email); // Assuming 'email' is used as username

        return userDetail.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    public String addUser(RegisterRequest request) {
        if (request.getRole() == null || !EnumSet.of(Role.FREE, Role.PREMIUM).contains(request.getRole())) {
            throw new IllegalArgumentException("Invalid role. Role must be either FREE or PREMIUM.");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        repository.save(user);
        return "User Added Successfully";
    }
}
