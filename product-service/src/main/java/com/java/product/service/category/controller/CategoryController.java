package com.java.product.service.category.controller;

import com.java.product.service.category.dto.*;
import com.java.product.service.category.service.CategoryService;
import com.java.product.service.wrapper.ApiResponse;
import com.java.product.service.wrapper.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

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
    public ResponseEntity<PageResponse<CategoryResponse>> getAllCategories(
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "categoryId") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getAllCategories(pageNo, pageSize, search, sortBy, sortDir));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<CategoryWithSubCategoryResponse>> getCategoryWithSubCategory(
            @Valid @PathVariable final UUID id
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<CategoryWithSubCategoryResponse>builder()
                        .status(true)
                        .message("Successfully fetched Category with Sub Categories")
                        .data(categoryService.getAllSubCategoriesAssociatedWithCategory(id))
                        .timestamp(Instant.now())
                        .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UpdateCategoryResponse>> updateCategoryById(
            @Valid @PathVariable UUID id,
            @Valid @RequestBody final UpdateCategoryRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<UpdateCategoryResponse>builder()
                        .status(true)
                        .message("Successfully updated Category Details")
                        .data(categoryService.updateCategoryDetails(id,request))
                        .timestamp(Instant.now())
                        .build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<Void>>  deleteCategoryById(
            @Valid @PathVariable final UUID id
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<Void>builder()
                        .status(true)
                        .message("Successfully deleted Category Details")
                        .data(categoryService.deleteCategoryDetailsById(id))
                        .build());
    }
}
