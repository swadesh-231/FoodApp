package com.foodapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    @NotNull
    @Min(1)
    @Builder.Default
    private Integer quantity = 1;
}
