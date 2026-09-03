package com.foodapp.exception.dto;

import lombok.Builder;

@Builder
public record ApiResponse(
        String message,
        boolean status
) {
}