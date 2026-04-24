package com.ws101.fortuna.EcommerceApi.service;

import com.ws101.fortuna.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Khiara Espelimbergo
 */

// This tells Spring this is a service class
@Service

/**
 * Service class for managing product operations.
 * This class handles business logic for creating, retrieving, updating, deleting, and filtering products.
 *
 * Data is stored in-memory using a List, meaning all data will be lost when the application stops.
 *
 * Acts as an intermediary between the controller and data storage.
 */
public class ProductService {

    // This service uses a List<Product> as a temporary data storage.
    // All data is stored in memory and will be lost
    // when the application stops.
    //nextId simulates auto-increment IDs.
    private List<Product> products = new ArrayList<>();

    // Counter for unique IDs
    private Long nextId = 1L;

    // Constructor
    public ProductService() {
        products.add(new Product(nextId++, "Minimal Watch", "Simple watch", 985.0, "Minimal Watch", 10, "img1.jpg"));
        products.add(new Product(nextId++, "Retro Watch", "Vintage style", 1020.0, "Minimal Watch", 8, "img2.jpg"));
        products.add(new Product(nextId++, "Classic Watch", "Classic leather", 1800.0, "Minimal Watch", 5, "img3.jpg"));
        products.add(new Product(nextId++, "Elegant Watch", "Formal style", 2700.0, "Minimal Watch", 6, "img10.jpg"));
        products.add(new Product(nextId++, "Matte Smart Watch", "Modern smartwatch", 3000.0, "Smart Watch", 15, "img4.jpg"));
        products.add(new Product(nextId++, "Women's Smart Watch", "Everyday wear", 2500.0, "Smart Watch", 25, "img9.jpg"));
        products.add(new Product(nextId++, "Men's Sports Watch", "Durable sports watch", 1200.0, "Sports Watch", 18, "img7.jpg"));
        products.add(new Product(nextId++, "Elegant Sports Watch", "Premium design", 5000.0, "Sports Watch", 3, "img8.jpg"));
        products.add(new Product(nextId++, "Digital Watch", "LED display", 1500.0, "Digital Watch", 20, "img6.jpg"));
        products.add(new Product(nextId++, "Fitness Watch", "Health tracking", 2200.0, "Fitness Watch", 12, "img5.jpg"));

    }

    // GET all products

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     *
     * Example:
     * GET /api/v1/products
     */

    public List<Product> getAllProducts() {
        return products;
    }


    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the product if found; otherwise null (mapped to 404 in controller)
     * @throws NullPointerException if id is null
     *
     * Example:
     * GET /api/v1/products/1
     */

    // GET product by ID
    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    /**
     * Adds a new product.
     *
     * @param product the product to be added
     * @return the created product with assigned ID
     * @throws NullPointerException if product is null
     *
     * Example:
     * POST /api/v1/products
     */

    // CREATE new product
    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }


    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update
     * @param updatedProduct the updated product details
     * @return the updated product if found; otherwise null
     * @throws NullPointerException if id or updatedProduct is null
     *
     * Example:
     * PUT /api/v1/products/1
     */

    // UPDATE product
    public Product updateProduct(Long id, Product updatedProduct) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setName(updatedProduct.getName());
                p.setDescription(updatedProduct.getDescription());
                p.setPrice(updatedProduct.getPrice());
                p.setCategory(updatedProduct.getCategory());
                p.setStockQuantity(updatedProduct.getStockQuantity());
                p.setImageUrl(updatedProduct.getImageUrl());
                return p;
            }
        }
        return null;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return true if the product was deleted; false if not found
     * @throws NullPointerException if id is null
     *
     * Example:
     * DELETE /api/v1/products/1
     */
    // DELETE product
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }


    /**
     * Filters products by category.
     *
     * @param category the category to filter by
     * @return a list of products matching the category
     * @throws NullPointerException if category is null
     *
     * Example:
     * GET /api/v1/products/category?category=Digital
     */
    // FILTER by category
    public List<Product> filterByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}