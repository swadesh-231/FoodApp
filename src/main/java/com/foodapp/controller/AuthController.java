package com.foodapp.controller;

import com.foodapp.dto.request.LoginRequest;
import com.foodapp.dto.request.RegisterRequest;
import com.foodapp.dto.response.LoginResponse;
import com.foodapp.dto.response.UserResponse;
import com.foodapp.exception.dto.ApiResponse;
import com.foodapp.security.service.AuthService;
import com.foodapp.security.dto.AuthTokens;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Value("${app.jwt.refresh-token.expiration}")
    private Long refreshTokenExpiry;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        AuthTokens loginResult = authService.login(loginRequest);
        ResponseCookie cookie = ResponseCookie.from(
                        "refreshToken",
                        loginResult.refreshToken()
                )
                .httpOnly(true)
                .secure(false) // true in production with HTTPS
                .path("/api/v1/auth")
                .maxAge(refreshTokenExpiry / 1000)
                .sameSite("Strict")
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );

        return ResponseEntity.ok(
                new LoginResponse(loginResult.accessToken())
        );
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false) // local HTTP; true in production HTTPS
                .path("/api/v1/auth")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Logged out successfully")
                        .status(true)
                        .build()
        );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(
            HttpServletRequest request
    ) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new AuthenticationServiceException("Refresh token missing");
        }
        String refreshToken = Arrays.stream(cookies)
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() ->
                        new AuthenticationServiceException("Refresh token missing")
                );
        String accessToken = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(
                new LoginResponse(accessToken)
        );
    }

}
