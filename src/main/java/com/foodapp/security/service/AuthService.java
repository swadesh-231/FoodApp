package com.foodapp.security.service;

import com.foodapp.dto.request.LoginRequest;
import com.foodapp.dto.request.RegisterRequest;
import com.foodapp.dto.response.UserResponse;

public interface AuthService {
    UserResponse registerUser(RegisterRequest registerRequest);
    AuthTokens login(LoginRequest loginRequest);
    String refreshToken(String refreshToken);
}
