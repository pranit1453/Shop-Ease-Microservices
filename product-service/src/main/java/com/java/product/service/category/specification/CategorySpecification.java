package com.java.product.service.category.specification;

import com.java.product.service.category.entity.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

    public static Specification<Category> searchByName(final String search) {
        return (root, query, cb) -> {

            if (search == null || search.isBlank()) return cb.conjunction();

            return cb.like(
                    cb.lower(root.get("categoryName"))
                    , "%" + search.toLowerCase() + "%"
            );
        };

    }
}
