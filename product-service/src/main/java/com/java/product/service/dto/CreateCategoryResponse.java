package com.java.product.service.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateCategoryResponse(
        UUID categoryId,
        String categoryName,
        String categoryDescription,
        UUID subCategoryId
) {
}
