package com.takehome.stayease.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.takehome.stayease.entity.Role;

@Data
public class RegisterRequest {

    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
        message = "Password must contain uppercase, number, special character and be at least 8 chars"
    )
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Role role;
}