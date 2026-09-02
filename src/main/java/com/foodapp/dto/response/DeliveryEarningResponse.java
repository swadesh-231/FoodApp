package com.foodapp.dto.response;

import java.time.LocalDateTime;

public record DeliveryEarningResponse(
        Long id,
        Long orderId,
        Long deliveryBoyId,
        int amount,
        LocalDateTime deliveryTime
) {
}
