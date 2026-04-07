package com.java.product.service.category.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UpdateCategoryResponse(
        UUID categoryId,
        String categoryName,
        String categoryDescription
) {
}
