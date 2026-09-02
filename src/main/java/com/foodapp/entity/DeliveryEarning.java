package com.foodapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "delivery_earnings",
        uniqueConstraints = @UniqueConstraint(name = "uk_delivery_earning_order", columnNames = "order_id"),
        indexes = @Index(name = "idx_delivery_earning_partner", columnList = "delivery_boy_id")
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEarning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "delivery_boy_id")
    private User deliveryBoy;

    @NotNull
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @PositiveOrZero
    private int amount;

    @NotNull
    private LocalDateTime deliveryTime;

}
