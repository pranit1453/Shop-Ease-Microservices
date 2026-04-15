package com.java.product.service.subcategory.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PatchSubCategoryResponse(
        UUID subCategoryId,
        String subCategoryName,
        String subCategoryDescription,
        UUID categoryId
) {
}
