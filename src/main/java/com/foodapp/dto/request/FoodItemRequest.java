package com.foodapp.dto.request;

import com.foodapp.entity.enums.FoodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record FoodItemRequest(
        @NotBlank(message = "Food item name is required")
        @Size(max = 100, message = "Food item name must not exceed 100 characters")
        String name,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than 0")
        Double price,

        @NotNull(message = "Discount amount is required")
        @PositiveOrZero(message = "Discount amount cannot be negative")
        Integer discountAmount,

        @NotNull(message = "Food type is required")
        FoodType foodType,

        @Size(max = 500, message = "Image url must not exceed 500 characters")
        String imageUrl,

        boolean available
) {
}
