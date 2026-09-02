package com.foodapp.dto.response;

import com.foodapp.entity.enums.FoodType;

import java.time.LocalDateTime;

public record FoodItemResponse(
        Long id,
        String name,
        String description,
        double price,
        int discountAmount,
        double actualPrice,
        boolean available,
        FoodType foodType,
        String imageUrl,
        Long restaurantId,
        LocalDateTime createdDate
) {
}
