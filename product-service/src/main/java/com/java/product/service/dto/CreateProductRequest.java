package com.java.product.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Builder
public record CreateProductRequest(
        @NotNull(message = "Product name cannot be null")
        @NotBlank(message = "Product name is mandatory")
        @Length(min = 3,
                max = 50,
                message = "Product name should be in between 3 and 50")
        String productName,
        @Length(min = 3,
                max = 50,
                message = "Category name should be in between 3 and 50")
        String productDescription,
        @NotNull(message = "Product price cannot be null")
        @NotBlank(message = "Product price is mandatory")
        Double productPrice,
        @NotNull(message = "subCategoryId cannot be null")
        @NotBlank(message = "subCategoryId is mandatory")
        UUID subCategoryId
) {
}
