package com.foodapp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record CreateRestaurantRequest(
        @NotBlank(message = "Restaurant name is required")
        @Size(max = 100, message = "Restaurant name must not exceed 100 characters")
        String name,

        @Size(max = 2000, message = "Description must not exceed 2000 characters")
        String description,

        @NotNull(message = "Opening time is required")
        LocalTime openTime,

        @NotNull(message = "Closing time is required")
        LocalTime closeTime,

        @NotNull(message = "Address is required")
        @Valid
        AddressRequest address,

        @NotNull(message = "Owner id is required")
        Long ownerId
) {
}
