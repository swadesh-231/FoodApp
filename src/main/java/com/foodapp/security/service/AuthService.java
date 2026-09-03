package com.foodapp.security.service;

import com.foodapp.dto.request.LoginRequest;
import com.foodapp.dto.request.RegisterRequest;
import com.foodapp.dto.response.RegisterResponse;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.security.dto.AuthTokens;

public interface AuthService {
    RegisterResponse registerUser(RegisterRequest registerRequest);
    AuthTokens login(LoginRequest loginRequest);
    String refreshToken(String refreshToken);
}
