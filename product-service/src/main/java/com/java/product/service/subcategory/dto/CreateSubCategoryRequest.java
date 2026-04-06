package com.java.product.service.subcategory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Builder
public record CreateSubCategoryRequest(
        @NotNull(message = "Sub Category name cannot be null")
        @NotBlank(message = "Sub Category name is mandatory")
        @Length(min = 3,
                max = 50,
                message = "Sub Category name should be in between 3 and 50")
        String subCategoryName,
        @Length(min = 3,
                max = 50,
                message = "Sub Category name should be in between 3 and 50")
        String subCategoryDescription,
        @NotNull(message = "CategoryId cannot be null")
        @NotBlank(message = "CategoryId is mandatory")
        UUID categoryId
) {
}
