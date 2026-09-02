package com.foodapp.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(
        name = "addresses",
        indexes = @Index(name = "idx_address_user", columnList = "user_id")
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Address extends BaseEntity {

    @Embedded
    private AddressDetails details;

    @Column(length = 30)
    private String label;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
