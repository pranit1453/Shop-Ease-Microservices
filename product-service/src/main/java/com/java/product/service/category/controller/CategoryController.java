package com.java.product.service.category.controller;

import com.java.product.service.category.dto.CategoryResponse;
import com.java.product.service.category.dto.CreateCategoryRequest;
import com.java.product.service.category.dto.CreateCategoryResponse;
import com.java.product.service.category.service.CategoryService;
import com.java.product.service.wrapper.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateCategoryResponse>> createCategory(
            @Valid @RequestBody final CreateCategoryRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.<CreateCategoryResponse>builder()
                        .status(true)
                        .message("Category Created Successfully")
                        .data(categoryService.createNewCategory(request))
                        .timestamp(Instant.now())
                        .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<CategoryResponse>>> getAllCategories(
            @Valid @RequestParam(required = false, defaultValue = "0") int pageNo,
            @Valid @RequestParam(required = false, defaultValue = "2") int pageSize,
            @Valid @RequestParam(required = false) String search,
            @Valid @RequestParam(required = false, defaultValue = "categoryId") String sortBy,
            @Valid @RequestParam(required = false, defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<Page<CategoryResponse>>builder()
                        .status(true)
                        .message("Successfully fetched Categories")
                        .data(categoryService.getAllCategories(pageNo, pageSize, search, sortBy, sortDir))
                        .build());
    }
}
