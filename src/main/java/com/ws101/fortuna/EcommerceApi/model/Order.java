package com.ws101.fortuna.EcommerceApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's order.
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String status;

    private Double totalAmount;

    /**
     * Many orders belong to one customer.
     *
     * Each order is linked to one customer,
     * but a customer can have many orders.
     *
     * The customer_id is stored in the orders table.
     *
     * FetchType.LAZY means customer info is only loaded when needed.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    /**
     * One order can have many order items.
     *
     * Each order contains a list of items (products with quantity).
     *
     * The "order" field in OrderItem is the one that connects them.
     *
     */

    @JsonIgnore
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<OrderItem> orderItems = new ArrayList<>();
}
