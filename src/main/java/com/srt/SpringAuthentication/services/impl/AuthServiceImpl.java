package com.srt.SpringAuthentication.services.impl;

import com.srt.SpringAuthentication.exceptions.UserAlreadyExistException;
import com.srt.SpringAuthentication.models.DTO.RegisterRequest;
import com.srt.SpringAuthentication.models.Role;
import com.srt.SpringAuthentication.models.User;
import com.srt.SpringAuthentication.repositories.UserRepository;
import com.srt.SpringAuthentication.services.AuthService;
import com.srt.SpringAuthentication.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistException("this username is already taken");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
    
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(encryptedPassword)
                .roles(roles)
                .build();

        userRepository.save(user);
        return "user successfully registered";
    }
}
