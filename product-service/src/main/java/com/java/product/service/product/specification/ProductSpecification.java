package com.java.product.service.product.specification;

import com.java.product.service.category.entity.Category;
import com.java.product.service.product.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> searchByName(final String search) {
        return (root, query, cb) -> {

            if (search == null || search.isBlank()) return cb.conjunction();

            String pattern = "%" + search.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("productName")), pattern),
                    cb.like(cb.lower(root.get("productDescription")), pattern)
            );
        };

    }
}
