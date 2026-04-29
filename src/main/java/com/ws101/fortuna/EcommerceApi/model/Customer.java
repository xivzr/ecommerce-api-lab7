package com.ws101.fortuna.EcommerceApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the system.
 */
@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;
    private String address;

    /**
     * One customer can have many orders.
     *
     * This means a single customer can place multiple orders.
     * The "customer" field in Order is the main connection.
     *
     * CascadeType.ALL means changes to the customer will also
     * affect their orders.
     *
     * orphanRemoval = true means if an order is removed,
     * it will also be deleted from the database.
     *
     * FetchType.LAZY means orders are only loaded when needed.
     */
    @JsonIgnore
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();
}
