package com.foodapp.dto.response;

public record OrderItemResponse(
        Long id,
        Long foodItemId,
        String foodItemName,
        double unitPrice,
        int quantity,
        double lineTotal
) {
}
