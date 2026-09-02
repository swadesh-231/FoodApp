package com.foodapp.dto.response;

import com.foodapp.entity.enums.PaymentMode;
import com.foodapp.entity.enums.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long orderId,
        int amount,
        PaymentMode paymentMode,
        PaymentStatus paymentStatus,
        String razorpayOrderId,
        String razorpayPaymentId,
        LocalDateTime paidAt,
        LocalDateTime createdAt
) {
}
