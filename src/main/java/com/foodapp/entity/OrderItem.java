package com.foodapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(
        name = "order_items",
        indexes = @Index(name = "idx_order_item_order", columnList = "order_id")
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodItem foodItem;

    @Min(1)
    private int quantity;

    public double getActualPriceOfOrderItem() {
        return quantity * foodItem.actualPrice();
    }

}
