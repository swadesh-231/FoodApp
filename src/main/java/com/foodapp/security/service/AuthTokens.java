package com.foodapp.security.service;

public record AuthTokens(
        String accessToken,
        String refreshToken
) {
}
