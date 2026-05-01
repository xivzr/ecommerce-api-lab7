package com.ws101.fortuna.EcommerceApi.repository;

import com.ws101.fortuna.EcommerceApi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}