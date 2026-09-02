package com.foodapp.exception.dto;

public record ApiResponse(
        String message,
        boolean status
) {
}