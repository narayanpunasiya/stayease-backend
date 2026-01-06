// package com.takehome.stayease;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.takehome.stayease.config.JwtUtil;
// import com.takehome.stayease.entity.Role;
// import com.takehome.stayease.entity.User;
// import com.takehome.stayease.repository.UserRepository;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.test.web.servlet.MockMvc;

// import java.util.Map;
// import java.util.Optional;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(AuthController.class)
// class AuthControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private UserRepository userRepository;

//     @MockBean
//     private JwtUtil jwtUtil;

//     @MockBean
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     private ObjectMapper objectMapper;

//     // ---------------- REGISTER TESTS ----------------

//     @Test
//     void register_success() throws Exception {
//         User user = new User();
//         user.setEmail("test@example.com");
//         user.setPassword("password");
//         user.setRole(Role.USER);

//         when(userRepository.findByEmail("test@example.com"))
//                 .thenReturn(Optional.empty());
//         when(passwordEncoder.encode("password"))
//                 .thenReturn("encodedPassword");
//         when(jwtUtil.generateToken("test@example.com", Role.USER))
//                 .thenReturn("jwt-token");

//         mockMvc.perform(post("/api/users/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(user)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.token").value("jwt-token"));
//     }

//     @Test
//     void register_userAlreadyExists() throws Exception {
//         User user = new User();
//         user.setEmail("test@example.com");
//         user.setPassword("password");

//         when(userRepository.findByEmail("test@example.com"))
//                 .thenReturn(Optional.of(new User()));

//         mockMvc.perform(post("/api/users/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(user)))
//                 .andExpect(status().isNotFound());
//     }

//     // ---------------- LOGIN TESTS ----------------

//     @Test
//     void login_success() throws Exception {
//         User user = new User();
//         user.setEmail("test@example.com");
//         user.setPassword("encodedPassword");
//         user.setRole(Role.USER);

//         Map<String, String> request = Map.of(
//                 "email", "test@example.com",
//                 "password", "password"
//         );

//         when(userRepository.findByEmail("test@example.com"))
//                 .thenReturn(Optional.of(user));
//         when(passwordEncoder.matches("password", "encodedPassword"))
//                 .thenReturn(true);
//         when(jwtUtil.generateToken("test@example.com", Role.USER))
//                 .thenReturn("jwt-token");

//         mockMvc.perform(post("/api/users/login")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.token").value("jwt-token"));
//     }

//     @Test
//     void login_userNotFound() throws Exception {
//         Map<String, String> request = Map.of(
//                 "email", "missing@example.com",
//                 "password", "password"
//         );

//         when(userRepository.findByEmail("missing@example.com"))
//                 .thenReturn(Optional.empty());

//         mockMvc.perform(post("/api/users/login")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isNotFound());
//     }

//     @Test
//     void login_invalidPassword() throws Exception {
//         User user = new User();
//         user.setEmail("test@example.com");
//         user.setPassword("encodedPassword");

//         Map<String, String> request = Map.of(
//                 "email", "test@example.com",
//                 "password", "wrongPassword"
//         );

//         when(userRepository.findByEmail("test@example.com"))
//                 .thenReturn(Optional.of(user));
//         when(passwordEncoder.matches("wrongPassword", "encodedPassword"))
//                 .thenReturn(false);

//         mockMvc.perform(post("/api/users/login")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isNotFound());
//     }
// }

