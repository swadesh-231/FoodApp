package com.foodapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(
        name = "addresses",
        indexes = @Index(name = "idx_address_user", columnList = "user_id")
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String addressLine;

    @NotBlank
    @Size(max = 60)
    private String city;

    @NotBlank
    @Size(max = 60)
    private String state;

    @NotBlank
    @Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
    @Column(length = 6)
    private String pincode;

    @NotBlank
    @Size(max = 60)
    private String country;

    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
