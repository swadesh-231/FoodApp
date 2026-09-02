package com.foodapp.entity;

import com.foodapp.entity.enums.FoodType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(
        name = "food_items",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_food_item_restaurant_name",
                columnNames = {"restaurant_id", "name"}
        ),
        indexes = {
                @Index(name = "idx_food_item_restaurant", columnList = "restaurant_id"),
                @Index(name = "idx_food_item_category", columnList = "category_id")
        }
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class FoodItem extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;


    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(nullable = false)
    @Builder.Default
    private boolean available = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private FoodType foodType;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @Transient
    public BigDecimal getEffectivePrice() {
        BigDecimal discount = discountAmount == null ? BigDecimal.ZERO : discountAmount;
        return price.subtract(discount).max(BigDecimal.ZERO);
    }
}
