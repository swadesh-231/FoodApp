package com.foodapp.security.service;

import com.foodapp.dto.request.LoginRequest;
import com.foodapp.dto.request.RegisterRequest;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.entity.User;
import com.foodapp.entity.enums.Role;
import com.foodapp.exception.APIException;
import com.foodapp.mapper.UserMapper;
import com.foodapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        String email = registerRequest.email()
                .trim()
                .toLowerCase(Locale.ROOT);
        if (userRepository.existsByEmail(email)) {
            throw new APIException("Email is already registered");
        }
        User user = User.builder()
                .name(registerRequest.username().trim())
                .email(email)
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.CUSTOMER)
                .build();
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        return null;
    }
}
