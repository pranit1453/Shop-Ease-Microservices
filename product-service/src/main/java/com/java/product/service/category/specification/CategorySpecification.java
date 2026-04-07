package com.java.product.service.category.specification;

import com.java.product.service.category.entity.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

    public static Specification<Category> searchByName(final String search) {
        return (root, query, cb) -> {

            if (search == null || search.isBlank()) return cb.conjunction();

            String pattern = "%" + search.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("categoryName")), pattern),
                    cb.like(cb.lower(root.get("categoryDescription")), pattern)
            );
        };

    }
}
