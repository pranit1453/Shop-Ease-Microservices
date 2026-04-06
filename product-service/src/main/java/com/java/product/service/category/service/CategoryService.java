package com.java.product.service.category.service;

import com.java.product.service.category.dto.CategoryResponse;
import com.java.product.service.category.dto.CreateCategoryRequest;
import com.java.product.service.category.dto.CreateCategoryResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface CategoryService {
    CreateCategoryResponse createNewCategory(@Valid CreateCategoryRequest request);

    Page<CategoryResponse> getAllCategories(@Valid int pageNo, @Valid int pageSize, @Valid String search, @Valid String sortBy, @Valid String sortDir);
}
