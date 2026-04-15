package com.java.product.service.subcategory.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record UpdateSubCategoryRequest(
        @Length(min = 3, max = 50, message = "Sub category name should be between 3 and 50 characters")
        String subCategoryName,
        @Length(min = 3, max = 500, message = "Sub category description should be between 3 and 500 characters")
        String subCategoryDescription
) {
}
