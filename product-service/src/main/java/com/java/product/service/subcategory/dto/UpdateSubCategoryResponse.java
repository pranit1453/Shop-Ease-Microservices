package com.java.product.service.subcategory.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UpdateSubCategoryResponse(
        UUID subCategoryId,
        String subCategoryName,
        String subCategoryDescription,
        UUID categoryId
) {
}
