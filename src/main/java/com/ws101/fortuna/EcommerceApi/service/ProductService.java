package com.ws101.fortuna.EcommerceApi.service;

import com.ws101.fortuna.EcommerceApi.model.Product;
import com.ws101.fortuna.EcommerceApi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor Injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    // ADD product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // UPDATE product
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setImageUrl(updatedProduct.getImageUrl());

        return productRepository.save(existing);
    }

    //patch
    public Product patchProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (product.getName() != null) existing.setName(product.getName());
        if (product.getDescription() != null) existing.setDescription(product.getDescription());
        if (product.getPrice() != null) existing.setPrice(product.getPrice());
        if (product.getCategory() != null) existing.setCategory(product.getCategory());
        if (product.getStockQuantity() != null) existing.setStockQuantity(product.getStockQuantity());
        if (product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());

        return productRepository.save(existing);
    }

    // DELETE product
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    // FILTER by category
    public List<Product> filterByCategory(String categoryName) {
        return productRepository.findByCategory_Name(categoryName);
    }

    // FILTER by price range
    public List<Product> filterByPrice(Double min, Double max) {
        return productRepository.findProductsInPriceRange(min, max);
    }
}