package com.java.product.service.subcategory.service;

import com.java.product.service.subcategory.dto.*;
import com.java.product.service.wrapper.PageResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface SubCategoryService {

    CreateSubCategoryResponse createSubCategory(@Valid CreateSubCategoryRequest request);

    PageResponse<SubCategoryResponseDto> fetchAllSubCategories(int pageNo, int pageSize, String search, String sortBy, String sortDir);

    SubCategoryWithProductsResponse getSubCategoryWithProducts(UUID subCategoryId);

    UpdateSubCategoryResponse updateSubCategory(UUID subCategoryId, @Valid UpdateSubCategoryRequest request);

    PatchSubCategoryResponse patchSubCategory(UUID subCategoryId, @Valid PatchSubCategoryRequest request);

    Void deleteSubCategory(@Valid UUID id);
}
