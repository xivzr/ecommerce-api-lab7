package com.ws101.fortuna.EcommerceApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a product available in the e-commerce system.
 * Each product belongs to one Category.
 */

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private Double price;


    /**
     * Many products belong to one category.
     *
     * This means each product has only one category,but a category can have many products.
     *
     * The category_id will be stored in the products table
     * as a foreign key.
     *
     * FetchType.LAZY means the category data will only be loaded
     * when we actually use it, not immediately.
     */


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be non-negative")
    @Column(nullable = false)
    private Integer stockQuantity;

    private String imageUrl;
}
