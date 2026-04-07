package com.java.product.service.category.entity;

import com.java.product.service.subcategory.entity.SubCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "categories",
        indexes = {
                @Index(name = "idx_category_name", columnList = "category_name"),
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    @Column(name = "category_description")
    private String categoryDescription;
    @OneToMany(mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private Set<SubCategory> subCategories = new HashSet<>();

    public void addSubCategory(final SubCategory subCategory) {
        this.subCategories.add(subCategory);
        subCategory.setCategory(this);
    }
}
