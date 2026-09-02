package com.foodapp.dto.request;

import com.foodapp.entity.enums.PaymentMode;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull(message = "Delivery address id is required")
        Long addressId,

        @NotNull(message = "Payment mode is required")
        PaymentMode paymentMode
) {
}
