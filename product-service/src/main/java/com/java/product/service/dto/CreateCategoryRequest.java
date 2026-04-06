package com.java.product.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record CreateCategoryRequest(
        @NotNull(message = "Category name cannot be null")
        @NotBlank(message = "Category name is mandatory")
        @Length(min = 3,
                max = 50,
                message = "Category name should be in between 3 and 50")
        String categoryName,
        @Length(min = 5,
                max = 500,
                message = "Category name should be in between 5 and 500")
        String categoryDescription
) {}
