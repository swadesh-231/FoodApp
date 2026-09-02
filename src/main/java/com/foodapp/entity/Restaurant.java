package com.foodapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "restaurants",
        indexes = @Index(name = "idx_restaurant_owner", columnList = "user_id")
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 2000)
    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalTime openTime;
    private LocalTime closeTime;

    @NotNull
    @Builder.Default
    private Boolean open = true;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Builder.Default
    private boolean isActive = true;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<FoodItem> foodItems = new ArrayList<>();

    @Size(max = 500)
    private String bannerImageUrl;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

}
