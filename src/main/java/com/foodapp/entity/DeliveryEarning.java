package com.foodapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/** Ledger row written once per delivered order; payouts are summed from these, never recomputed. */
@Entity
@Table(
        name = "delivery_earnings",
        uniqueConstraints = @UniqueConstraint(name = "uk_delivery_earning_order", columnNames = "order_id"),
        indexes = @Index(name = "idx_delivery_earning_partner", columnList = "delivery_partner_id")
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DeliveryEarning extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_partner_id", nullable = false)
    private User deliveryPartner;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Instant deliveredAt;
}
