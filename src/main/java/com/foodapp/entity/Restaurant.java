package com.foodapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "restaurants",
        indexes = {
                @Index(name = "idx_restaurant_owner", columnList = "owner_id"),
                @Index(name = "idx_restaurant_city", columnList = "city")
        }
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Restaurant extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private LocalTime openTime;

    private LocalTime closeTime;

    @Column(nullable = false)
    @Builder.Default
    private boolean open = true;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Embedded
    private AddressDetails address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Builder.Default
    private List<FoodItem> foodItems = new ArrayList<>();

    private String bannerImageUrl;

    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
        foodItem.setRestaurant(this);
    }

    public void removeFoodItem(FoodItem foodItem) {
        foodItems.remove(foodItem);
        foodItem.setRestaurant(null);
    }
}
