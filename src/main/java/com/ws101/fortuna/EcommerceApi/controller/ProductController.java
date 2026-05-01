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
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins =  "*")
public class ProductController {

    private final ProductService productService;

    //  Using constructor injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //  CREATE
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(201)
                .body(productService.addProduct(product));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {

        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    //  PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return ResponseEntity.ok(productService.patchProduct(id, product));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // FILTER CATEGORY
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(@RequestParam String category) {
        return ResponseEntity.ok(productService.filterByCategory(category));
    }


    @GetMapping("/price")
    public ResponseEntity<List<Product>> filterByPrice(
            @RequestParam Double min,
            @RequestParam Double max) {

        return ResponseEntity.ok(productService.filterByPrice(min, max));
    }
}