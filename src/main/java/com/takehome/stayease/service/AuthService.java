package com.takehome.stayease.service;

import lombok.RequiredArgsConstructor;
import com.takehome.stayease.dto.AuthResponse;
import com.takehome.stayease.dto.LoginRequest;
import com.takehome.stayease.dto.RegisterRequest;
import com.takehome.stayease.entity.Role;
import com.takehome.stayease.entity.User;
import com.takehome.stayease.repository.UserRepository;
import com.takehome.stayease.security.CustomUserDetailsService;
import com.takehome.stayease.security.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.takehome.stayease.exception.InvalidCredentialsException;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole() == null ? Role.USER : request.getRole())
                .build();

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        return new AuthResponse(jwtService.generateToken(userDetails));
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid credentials"));
    
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(user.getEmail());
    
        return new AuthResponse(jwtService.generateToken(userDetails));
    }
    
}

