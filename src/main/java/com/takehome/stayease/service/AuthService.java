package com.takehome.stayease.service;

import com.takehome.stayease.dto.*;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}