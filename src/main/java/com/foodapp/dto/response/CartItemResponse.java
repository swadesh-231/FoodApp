package com.foodapp.dto.response;

public record CartItemResponse(
        Long cartItemId,
        Long foodItemId,
        String foodItemName,
        String imageUrl,
        double unitPrice,
        int quantity,
        double lineTotal
) {
}
