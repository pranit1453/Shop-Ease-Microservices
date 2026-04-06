package com.java.product.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table(
        name = "products",
        indexes = {
                @Index(name = "idx_product_name",columnList = "product_name"),
                @Index(name = "idx_subCategory",columnList = "subcategory"),
                @Index(name = "idx_product_price",columnList = "product_price")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    @Column(name = "product_name",nullable = false)
    private String productName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_price",nullable = false)
    private Double productPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subCategory_id",nullable = false)
    private SubCategory subCategory;
}
