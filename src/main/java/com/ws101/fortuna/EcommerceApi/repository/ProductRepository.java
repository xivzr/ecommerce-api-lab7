package com.ws101.fortuna.EcommerceApi.repository;

import com.ws101.fortuna.EcommerceApi.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_Name(String name);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findProductsInPriceRange(
            @Param("min") Double min,
            @Param("max") Double max
    );

}