package com.java.product.service.subcategory.dto;

import com.java.product.service.product.dto.ProductResponse;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record SubCategoryWithProductsResponse(
        UUID subCategoryId,
        String subCategoryName,
        String subCategoryDescription,
        UUID categoryId,
        String categoryName,
        List<ProductResponse> products
) {
}
