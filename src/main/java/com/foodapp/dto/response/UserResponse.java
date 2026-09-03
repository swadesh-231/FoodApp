package com.foodapp.dto.response;

import com.foodapp.entity.enums.Role;

import java.time.Instant;

public record UserResponse(
        Long id,
        String username,
        String email,
        Role role,
        Instant createdAt
) {
}
