package com.foodapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
        name = "categories",
        uniqueConstraints = @UniqueConstraint(name = "uk_category_name", columnNames = "name")
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Category extends BaseEntity {

    @NotBlank
    @Column(nullable = false, length = 60)
    private String name;
}
