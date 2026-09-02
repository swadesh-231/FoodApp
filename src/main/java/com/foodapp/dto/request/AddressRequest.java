package com.foodapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequest(
        @NotBlank(message = "Address line is required")
        @Size(max = 255, message = "Address line must not exceed 255 characters")
        String addressLine,

        @NotBlank(message = "City is required")
        @Size(max = 60, message = "City must not exceed 60 characters")
        String city,

        @NotBlank(message = "State is required")
        @Size(max = 60, message = "State must not exceed 60 characters")
        String state,

        @NotBlank(message = "Pincode is required")
        @Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
        String pincode,

        @NotBlank(message = "Country is required")
        @Size(max = 60, message = "Country must not exceed 60 characters")
        String country,

        boolean isDefault
) {
}
