package com.foodapp.entity;

import com.foodapp.entity.enums.PaymentMode;
import com.foodapp.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "payments",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_payment_order", columnNames = "order_id"),
                @UniqueConstraint(name = "uk_payment_razorpay_order", columnNames = "razorpay_order_id"),
                @UniqueConstraint(name = "uk_payment_razorpay_payment", columnNames = "razorpay_payment_id")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @PositiveOrZero
    private int amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Size(max = 64)
    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;

    @Size(max = 64)
    @Column(name = "razorpay_payment_id")
    private String razorpayPaymentId;

    @Size(max = 512)
    private String razorpaySignature;

    private LocalDateTime paidAt;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
