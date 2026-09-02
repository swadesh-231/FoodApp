package com.foodapp.dto.response;

import java.util.List;

public record CartResponse(
        Long cartId,
        List<CartItemResponse> items,
        double totalAmount
) {
}
