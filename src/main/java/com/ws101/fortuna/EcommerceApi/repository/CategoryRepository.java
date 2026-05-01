package com.ws101.fortuna.EcommerceApi.repository;

import com.ws101.fortuna.EcommerceApi.model.Category;
//import com.ws101.fortuna.EcommerceApi.model.Product;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}