package com.takehome.stayease.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$",
        message = "Password must have at least 8 characters, one uppercase, one number and one special character"
    )
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String role; // Optional, default to CUSTOMER
}

