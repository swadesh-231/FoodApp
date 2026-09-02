package com.foodapp.security.service;

import com.foodapp.dto.request.LoginRequest;
import com.foodapp.dto.request.RegisterRequest;
import com.foodapp.dto.response.UserResponse;

public interface AuthService {
    UserResponse registerUser(RegisterRequest registerRequest);
    UserResponse login(LoginRequest loginRequest);
}
