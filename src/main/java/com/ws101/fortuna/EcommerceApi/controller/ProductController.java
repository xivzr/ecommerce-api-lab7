package com.ws101.fortuna.EcommerceApi.controller;

import com.ws101.fortuna.EcommerceApi.model.Product;
import com.ws101.fortuna.EcommerceApi.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST Controller
@RestController

// Base path
@RequestMapping("/api/v1/products")

// Allow frontend access
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET ALL PRODUCTS -200
    // Endpoint: GET /api/v1/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET PRODUCT BY ID
    // Endpoint: GET /api/v1/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product); //200
        } else {
            return ResponseEntity.notFound().build(); //404
        }
    }

    // FILTER PRODUCTS
    // Endpoint: GET /api/v1/products/filter?filterType=category&filterValue=Watch
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        if (filterType.equalsIgnoreCase("category")) {
            return ResponseEntity.ok(productService.getProductsByCategory(filterValue)); //200
        }

        return ResponseEntity.badRequest().build();
    }

    // CREATE PRODUCT - 201 created
    // Endpoint: POST /api/v1/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.status(200).body(newProduct); // 201 Created
    }

    // UPDATE Create PRODUCT  //404 not found
    // Endpoint: PUT /api/v1/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        Product updated = productService.updateProduct(id, product);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH (PARTIAL UPDATE)
    // Endpoint: PATCH /api/v1/products/{id}
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

        if (product.getPrice() != 0) existing.setPrice(product.getPrice());
        if (product.getStockQuantity() != 0) existing.setStockQuantity(product.getStockQuantity());

        return ResponseEntity.ok(existing);
    }

    // DELETE PRODUCT
    // Endpoint: DELETE /api/v1/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        boolean deleted = productService.deleteProduct(id);

        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}