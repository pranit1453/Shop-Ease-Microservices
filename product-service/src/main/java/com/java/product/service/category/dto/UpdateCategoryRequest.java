package com.java.product.service.category.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record UpdateCategoryRequest(
        @Length(min = 3,
                max = 50,
                message = "Category name should be in between 3 and 50")
        String categoryName,
        @Length(min = 5,
                max = 500,
                message = "Category name should be in between 5 and 500")
        String categoryDescription

) {
}
