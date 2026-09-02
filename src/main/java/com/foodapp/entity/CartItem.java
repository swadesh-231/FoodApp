package com.foodapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(
        name = "cart_items",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_cart_item_cart_food_item",
                columnNames = {"cart_id", "food_item_id"}
        ),
        indexes = @Index(name = "idx_cart_item_cart", columnList = "cart_id")
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CartItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_item_id", nullable = false)
    private FoodItem foodItem;

    @Min(1)
    @Column(nullable = false)
    @Builder.Default
    private Integer quantity = 1;

    /** Live price - the cart reflects the current menu until checkout freezes it onto the order. */
    @Transient
    public BigDecimal getLineTotal() {
        return foodItem.getEffectivePrice().multiply(BigDecimal.valueOf(quantity));
    }
}
