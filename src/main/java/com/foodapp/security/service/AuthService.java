package com.foodapp.security.service;

import com.foodapp.dto.LoginRequest;
import com.foodapp.dto.RegisterRequest;
import com.foodapp.dto.UserResponse;

public interface AuthService {
    UserResponse registerUser(RegisterRequest registerRequest);
    UserResponse login(LoginRequest loginRequest);
}
