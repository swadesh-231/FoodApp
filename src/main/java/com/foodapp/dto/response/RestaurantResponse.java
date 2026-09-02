package com.foodapp.dto.response;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record RestaurantResponse(
        Long id,
        String name,
        String description,
        LocalTime openTime,
        LocalTime closeTime,
        Boolean open,
        boolean isActive,
        AddressResponse address,
        Long ownerId,
        String bannerImageUrl,
        LocalDateTime createdDate,
        List<FoodItemResponse> foodItems
) {
}
