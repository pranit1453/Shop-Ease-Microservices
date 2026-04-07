package com.java.product.service.subcategory.entity;

import com.java.product.service.category.entity.Category;
import com.java.product.service.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "sub_categories",
        indexes = {
                @Index(name = "idx_subcategory_name", columnList = "sub_category_name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID subCategoryId;
    @Column(name = "sub_category_name", nullable = false)
    private String subCategoryName;
    @Column(name = "sub_category_description")
    private String subCategoryDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private Category category;
    @OneToMany(mappedBy = "subCategory",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    public void addProduct(final Product product) {
        this.products.add(product);
        product.setSubCategory(this);
    }
}
