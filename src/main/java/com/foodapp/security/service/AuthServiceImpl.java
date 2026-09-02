package com.foodapp.security.service;

import com.foodapp.dto.LoginRequest;
import com.foodapp.dto.RegisterRequest;
import com.foodapp.dto.UserResponse;
import com.foodapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        return null;
    }
}
