package com.java.product.service.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(
        name = "subCategories",
        indexes = {
                @Index(name = "idx_subCategory_name",columnList = "subCategory_name")
        }
)
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID subCategoryId;
    @Column(name = "subCategory_name", nullable = false)
    private String subCategoryName;
    @Column(name = "subCategory_description")
    private String subCategoryDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    @ToString.Exclude
    private Category category;
    @OneToMany(mappedBy = "products",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();
}
