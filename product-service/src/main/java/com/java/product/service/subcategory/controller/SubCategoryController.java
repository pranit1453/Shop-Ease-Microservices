package com.java.product.service.subcategory.controller;

import com.java.product.service.subcategory.dto.CreateSubCategoryRequest;
import com.java.product.service.subcategory.dto.CreateSubCategoryResponse;
import com.java.product.service.subcategory.dto.SubCategoryResponse;
import com.java.product.service.subcategory.dto.SubCategoryResponseDto;
import com.java.product.service.subcategory.entity.SubCategory;
import com.java.product.service.subcategory.repository.SubCategoryRepository;
import com.java.product.service.subcategory.service.SubCategoryService;
import com.java.product.service.wrapper.ApiResponse;
import com.java.product.service.wrapper.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subCategory")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService  subCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateSubCategoryResponse>> createSubCategory(@Valid
                                                                                        @RequestBody final CreateSubCategoryRequest request) {

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
            @RequestParam(required = false, defaultValue = "ASC") String sortDir,
            Sort sort) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subCategoryService.fetchAllSubCategories(pageNo,pageSize,search,sortBy,sortDir));

    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<ApiResponse<Void>> deleteSubCategoryById(
            @Valid @PathVariable final UUID id
            ){
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
