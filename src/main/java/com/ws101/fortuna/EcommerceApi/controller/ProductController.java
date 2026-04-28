package com.ws101.fortuna.EcommerceApi.controller;

import com.ws101.fortuna.EcommerceApi.model.Product;
import com.ws101.fortuna.EcommerceApi.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author Khiara Espelimbergo
 */
// Marks this class as a REST Controller
@RestController

// Base path
@RequestMapping("/api/v1/products")

// Allow frontend access
@CrossOrigin

/**
 * REST Controller for handling product-related API requests.
 *
 * Provides endpoints for creating, retrieving, updating,
 * partially updating, and deleting products.
 *
 * Base URL: /api/v1/products
 *
 * Uses ProductService for business logic and returns
 * appropriate HTTP responses.
 *
 *gig
 */
public class ProductController {

    @Autowired
    private ProductService productService;


    // GET ALL PRODUCTS 200
    // Endpoint: GET /api/v1/products
    /**
     * Retrieves all products.
     *
     * @return ResponseEntity containing a list of products (200 OK)
     *
     * Example:
     * GET /api/v1/products
     */

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET PRODUCT BY ID
    // Endpoint: GET /api/v1/products/{id}
    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return ResponseEntity containing the product (200 OK)
     *         or 404 Not Found if the product does not exist
     *
     * Example:
     * GET /api/v1/products/{id}
     */

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product); //200
        } else {
            return ResponseEntity.notFound().build(); //404
        }
    }

    // CREATE PRODUCT - 201 created
    // Endpoint: POST /api/v1/products
    /**
     * Creates a new product.
     *
     * @param product the product to be created (validated)
     * @return ResponseEntity with status 201 (Created)
     * @throws jakarta.validation.ConstraintViolationException if validation fails
     *
     * Example request:
     * POST /api/v1/products
     */

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.status(201).body(newProduct); // 201 Created
    }

    // UPDATE Create PRODUCT  //404 not found
    // Endpoint: PUT /api/v1/products/{id}
    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update
     * @param product the updated product data (validated)
     * @return ResponseEntity containing the updated product (200 OK)
     *         or 404 Not Found if the product does not exist
     * @throws jakarta.validation.ConstraintViolationException if validation fails
     *
     * Example:
     * PUT /api/v1/products/{id}
     */

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid@RequestBody Product product) { //valid is added

        Product updated = productService.updateProduct(id, product);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH (PARTIAL UPDATE)
    // Endpoint: PATCH /api/v1/products/{id}

    /**
     * Partially updates a product.
     *
     * Only non-null fields will be updated.
     *
     * @param id the ID of the product to update
     * @param product the product fields to update
     * @return ResponseEntity containing the updated product (200 OK)
     *         or 404 Not Found if the product does not exist
     *
     * Example:
     * PATCH /api/v1/products/{id}
     */

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        Product existing = productService.getProductById(id);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        // Update only fields that are NOT null
        if (product.getName() != null) existing.setName(product.getName());
        if (product.getDescription() != null) existing.setDescription(product.getDescription());
        if (product.getCategory() != null) existing.setCategory(product.getCategory());
        if (product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());

        if (product.getPrice() != null ) existing.setPrice(product.getPrice());
        if (product.getStockQuantity() != null) existing.setStockQuantity(product.getStockQuantity());

        return ResponseEntity.ok(existing);
    }

    // DELETE PRODUCT
    // Endpoint: DELETE /api/v1/products/{id}
    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return ResponseEntity with status 200 OK if deleted
     *         or 404 Not Found if the product does not exist
     *
     * Example:
     * DELETE /api/v1/products/{id}
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id); //new added

        boolean deleted = productService.deleteProduct(id);

        if (deleted) {
            return ResponseEntity.ok().build(); // 204 No Content  //200k updated //.no content() to .ok()
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //  FILTER PRODUCTS BY CATEGORY
    // Endpoint: GET /api/v1/products/filter?category=Sports Watch

    /**
     * Filters products by category
     *
     * @param category the category to filter products by
     * @return ResponseEntity containing list of filtered products
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String category) {

        List<Product> products = productService.filterByCategory(category);
        return ResponseEntity.ok(products);
    }
}
