package com.java.product.service.subcategory.controller;

import com.java.product.service.subcategory.dto.*;
import com.java.product.service.subcategory.service.SubCategoryService;
import com.java.product.service.wrapper.ApiResponse;
import com.java.product.service.wrapper.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subCategory")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateSubCategoryResponse>> createSubCategory(
            @Valid @RequestBody CreateSubCategoryRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.<CreateSubCategoryResponse>builder()
                        .status(true)
                        .message("Successfully created subcategory")
                        .data(subCategoryService.createSubCategory(request))
                        .timestamp(Instant.now())
                        .build());
    }

    @GetMapping("/all")
    public ResponseEntity<PageResponse<SubCategoryResponseDto>> getAllSubCategories(
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "subCategoryName") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subCategoryService.fetchAllSubCategories(pageNo, pageSize, search, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubCategoryWithProductsResponse>> getSubCategoryWithProducts(
            @PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<SubCategoryWithProductsResponse>builder()
                        .status(true)
                        .message("Successfully fetched subcategory with products")
                        .data(subCategoryService.getSubCategoryWithProducts(id))
                        .timestamp(Instant.now())
                        .build());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse<UpdateSubCategoryResponse>> updateSubCategory(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateSubCategoryRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<UpdateSubCategoryResponse>builder()
                        .status(true)
                        .message("Successfully updated subcategory")
                        .data(subCategoryService.updateSubCategory(id, request))
                        .timestamp(Instant.now())
                        .build());
    }

    @PatchMapping("/{id}/patch")
    public ResponseEntity<ApiResponse<PatchSubCategoryResponse>> patchSubCategory(
            @PathVariable UUID id,
            @Valid @RequestBody PatchSubCategoryRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<PatchSubCategoryResponse>builder()
                        .status(true)
                        .message("Successfully patched subcategory")
                        .data(subCategoryService.patchSubCategory(id, request))
                        .timestamp(Instant.now())
                        .build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<Void>> deleteSubCategoryById(
            @PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<Void>builder()
                        .status(true)
                        .message("Successfully deleted subcategory")
                        .data(subCategoryService.deleteSubCategory(id))
                        .timestamp(Instant.now())
                        .build());
    }
}
