package com.foodapp.security.service;

import com.foodapp.dto.request.LoginRequest;
import com.foodapp.dto.request.RegisterRequest;
import com.foodapp.dto.response.RegisterResponse;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.entity.User;
import com.foodapp.entity.enums.Role;
import com.foodapp.exception.APIException;
import com.foodapp.mapper.UserMapper;
import com.foodapp.repository.UserRepository;
import com.foodapp.security.dto.AuthTokens;
import com.foodapp.security.jwt.JwtService;
import com.foodapp.security.user.CustomUserDetails;
import com.foodapp.security.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
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
        return userMapper.toRegisterResponse(savedUser);
    }

    @Override
    public AuthTokens login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.user();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthTokens(accessToken, refreshToken);
    }

    @Override
    public String refreshToken(String refreshToken) {
        if (!jwtService.validateRefreshToken(refreshToken)) {
            throw new APIException("Invalid or expired refresh token");
        }

        String email = jwtService.getUsernameFromRefreshToken(refreshToken);

        CustomUserDetails userDetails =
                (CustomUserDetails) customUserDetailsService
                        .loadUserByUsername(email);
        return jwtService.generateAccessToken(userDetails.user());
    }
}
