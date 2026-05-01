package com.ws101.fortuna.EcommerceApi.repository;

import com.ws101.fortuna.EcommerceApi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}