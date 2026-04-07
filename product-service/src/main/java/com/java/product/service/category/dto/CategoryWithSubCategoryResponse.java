package com.java.product.service.category.dto;

import com.java.product.service.subcategory.dto.SubCategoryResponse;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CategoryWithSubCategoryResponse(
        UUID categoryId,
        String categoryName,
        String categoryDescription,
        List<SubCategoryResponse> subCategories
) {
}
