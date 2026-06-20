// package com.takehome.stayease.service;

// import com.takehome.stayease.entity.Role;
// import com.takehome.stayease.entity.User;
// import com.takehome.stayease.repository.UserRepository;
// import com.takehome.stayease.security.JwtService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// @RequiredArgsConstructor
// public class UserService implements UserDetailsService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtService jwtService;

//     // Register a new user
//     public String registerUser(User user) {
//         Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
//         if (existingUser.isPresent()) {
//             throw new RuntimeException("User already exists");
//         }

//         if (user.getRole() == null) {
//             user.setRole(Role.USER); // Default role
//         }

//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         userRepository.save(user);

//         UserDetails userDetails = loadUserByUsername(user.getEmail());
//         return jwtService.generateToken(userDetails);
//     }

//     // Login existing user
//     public String loginUser(String email, String password) {
//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("Invalid credentials"));

//         if (!passwordEncoder.matches(password, user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         UserDetails userDetails = loadUserByUsername(user.getEmail());
//         return jwtService.generateToken(userDetails);
//     }

//     // For Spring Security authentication
//     @Override
//     public UserDetails loadUserByUsername(String email)
//             throws UsernameNotFoundException {

//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//         return org.springframework.security.core.userdetails.User.builder()
//                 .username(user.getEmail())
//                 .password(user.getPassword())
//                 .authorities("ROLE_" + user.getRole().name())
//                 .build();
//     }
// }
