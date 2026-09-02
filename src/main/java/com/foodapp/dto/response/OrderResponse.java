package com.foodapp.dto.response;

import com.foodapp.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        Long restaurantId,
        String restaurantName,
        AddressResponse address,
        int totalAmount,
        OrderStatus status,
        LocalDateTime orderedAt,
        LocalDateTime deliveryTime,
        Long deliveryBoyId,
        String deliveryBoyName,
        List<OrderItemResponse> orderItems,
        PaymentResponse payment
) {
}
