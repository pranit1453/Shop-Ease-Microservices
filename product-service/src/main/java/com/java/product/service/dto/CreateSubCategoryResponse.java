package com.java.product.service.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateSubCategoryResponse(
        UUID subCategoryId,
        String subCategoryName,
        String subCategoryDescription,
        UUID productId
) {
}
