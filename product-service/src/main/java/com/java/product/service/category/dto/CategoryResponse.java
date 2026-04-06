package com.java.product.service.category.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CategoryResponse(
        UUID categoryId,
        String categoryName,
        String categoryDescription
) {
}
