package com.java.product.service.product.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductPageResponse(
        UUID productId,
        String productName,
        String productDescription,
        Double price,
        UUID subCategoryId,
        String subCategoryName
) {
}
