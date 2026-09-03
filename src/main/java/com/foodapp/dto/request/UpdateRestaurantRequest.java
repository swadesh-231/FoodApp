package com.foodapp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

/**
 * Partial update: every field is optional and a null means "leave unchanged".
 */
public record UpdateRestaurantRequest(
        @Size(max = 100, message = "Restaurant name must not exceed 100 characters")
        String name,

        @Size(max = 2000, message = "Description must not exceed 2000 characters")
        String description,

        LocalTime openTime,

        LocalTime closeTime,

        @Valid
        AddressRequest address
) {
}
