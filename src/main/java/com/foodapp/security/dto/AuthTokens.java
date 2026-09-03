package com.foodapp.security.dto;

public record AuthTokens(
        String accessToken,
        String refreshToken
) {
}
