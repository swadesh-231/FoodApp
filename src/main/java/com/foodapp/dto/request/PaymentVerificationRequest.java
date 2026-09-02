package com.foodapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PaymentVerificationRequest(
        @NotBlank(message = "Razorpay order id is required")
        @Size(max = 64, message = "Razorpay order id must not exceed 64 characters")
        String razorpayOrderId,

        @NotBlank(message = "Razorpay payment id is required")
        @Size(max = 64, message = "Razorpay payment id must not exceed 64 characters")
        String razorpayPaymentId,

        @NotBlank(message = "Razorpay signature is required")
        @Size(max = 512, message = "Razorpay signature must not exceed 512 characters")
        String razorpaySignature
) {
}
