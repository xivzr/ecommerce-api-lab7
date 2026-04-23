package com.ws101.fortuna.EcommerceApi.service;

import com.ws101.fortuna.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// This tells Spring this is a service class
@Service
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
    public List<Product> getAllProducts() {
        return products;
    }

    // GET product by ID
    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // CREATE new product
    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

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

    // DELETE product
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    // FILTER by category
    public List<Product> filterByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}