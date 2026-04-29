package com.ws101.fortuna.EcommerceApi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a category that groups multiple products.
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    /**
     * One category can have many products.
     *
     * This is the opposite side of the relationship.
     * The "category" field in Product is the one that controls it.
     *
     * CascadeType.ALL means if we save or delete a category,
     * the related products will also be affected.
     *
     * orphanRemoval = true means if a product is removed from this list,
     * it will also be deleted from the database.
     *
     * FetchType.LAZY means products will only load when needed.
     */

    @JsonIgnore
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();
}
