package com.java.product.service.subcategory.specification;

import com.java.product.service.category.entity.Category;
import com.java.product.service.subcategory.entity.SubCategory;
import org.springframework.data.jpa.domain.Specification;

public class SubCategorySpecification {

    public static Specification<SubCategory> searchByName(final String search) {
        return (root, query, cb) -> {

            if (search == null || search.isBlank()) return cb.conjunction();

            String pattern = "%" + search.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("subCategoryName")), pattern),
                    cb.like(cb.lower(root.get("subCategoryDescription")), pattern)
            );
        };

    }
}
