package com.java.product.service.subcategory.service;

import com.java.product.service.subcategory.dto.CreateSubCategoryRequest;
import com.java.product.service.subcategory.dto.CreateSubCategoryResponse;
import com.java.product.service.subcategory.dto.SubCategoryResponse;
import com.java.product.service.subcategory.dto.SubCategoryResponseDto;
import com.java.product.service.wrapper.PageResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface SubCategoryService {
    CreateSubCategoryResponse createSubCategory(@Valid CreateSubCategoryRequest request);

    PageResponse<SubCategoryResponseDto> fetchAllSubCategories(int pageNo, int pageSize, String search, String sortBy, String sortDir);

    Void deleteSubCategory(@Valid UUID id);
}
