package com.foodapp.security.service;

import com.foodapp.dto.request.LoginRequest;
import com.foodapp.dto.request.RegisterRequest;
import com.foodapp.dto.response.LoginResponse;
import com.foodapp.dto.response.UserResponse;

public interface AuthService {
    UserResponse registerUser(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
