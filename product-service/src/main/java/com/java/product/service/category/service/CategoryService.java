package com.java.product.service.category.service;

import com.java.product.service.category.dto.*;
import com.java.product.service.wrapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CategoryService {
    CreateCategoryResponse createNewCategory(@Valid CreateCategoryRequest request);

    PageResponse<CategoryResponse> getAllCategories(@Valid int pageNo, @Valid int pageSize, @Valid String search, @Valid String sortBy, @Valid String sortDir);

    CategoryWithSubCategoryResponse getAllSubCategoriesAssociatedWithCategory(@Valid UUID id);

    UpdateCategoryResponse updateCategoryDetails(@Valid UUID id, @Valid UpdateCategoryRequest request);

    Void deleteCategoryDetailsById(@Valid UUID id);
}
