package com.foodapp.dto.response;

public record AddressResponse(
        Long id,
        String addressLine,
        String city,
        String state,
        String pincode,
        String country,
        boolean isDefault
) {
}
