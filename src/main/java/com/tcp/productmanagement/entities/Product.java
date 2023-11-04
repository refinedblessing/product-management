package com.tcp.productmanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents a product in the inventory.
 */
@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    /**
     * Unique id for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    /**
     * The name of the product. It should be between 2 and 255 characters in length.
     */
    @NotBlank(message = "Product name cannot be blank")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Expiry date of product, 3 months from the day of entry
     */
    @Column(name = "expiry_date")
    private LocalDate expiryDate = LocalDate.now().plusMonths(3);

    /**
     * Markdown date of product
     */
    @Column(name = "markdown_date")
    private LocalDate markdownDate = expiryDate.minusDays(6);

    /**
     * The min threshold of the product.
     */
    @Column(name = "min_threshold")
    private Integer minThreshold = 0;

    @Column(name= "quantity")
    private Integer quantity = 0;

    /**
     * The max threshold of the product.
     */
    @Column(name = "max_threshold")
    private Integer maxThreshold = 0;
}
