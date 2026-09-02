package com.foodapp.service.impl;

import com.foodapp.dto.request.CreateUserRequest;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.entity.User;
import com.foodapp.exception.APIException;
import com.foodapp.mapper.UserMapper;
import com.foodapp.repository.UserRepository;
import com.foodapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        String email = createUserRequest.email().trim().toLowerCase(Locale.ROOT);

        if (userRepository.existsByEmail(email)) {
            throw new APIException("Email is already registered");
        }

        User user = User.builder()
                .name(createUserRequest.username().trim())
                .email(email)
                .password(passwordEncoder.encode(createUserRequest.password()))
                .role(createUserRequest.role())
                .build();

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
}
