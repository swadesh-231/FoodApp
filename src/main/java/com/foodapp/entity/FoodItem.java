package com.foodapp.entity;

import com.foodapp.entity.enums.FoodType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "food_items",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_food_item_restaurant_name",
                columnNames = {"restaurant_id", "name"}
        ),
        indexes = @Index(name = "idx_food_item_restaurant", columnList = "restaurant_id")
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 1000)
    private String description;

    @Positive
    private double price;

    @Builder.Default
    private boolean available = true;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FoodType foodType = FoodType.VEG;

    @Size(max = 500)
    private String imageUrl;

    private LocalDateTime createdDate;

    @PositiveOrZero
    private int discountAmount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    public double actualPrice() {
        return Math.max(price - discountAmount, 0);
    }

    public double getDiscountPercentage() {
        if (price <= 0) {
            return 0;
        }
        return (discountAmount / price) * 100;
    }
}
