package com.java.product.service.product.controller;

import com.java.product.service.product.dto.*;
import com.java.product.service.product.service.ProductService;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateProductResponse>> createNewProduct(
            @Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.<CreateProductResponse>builder()
                        .status(true)
                        .message("Successfully created new product")
                        .data(productService.createNewProduct(request))
                        .timestamp(Instant.now())
                        .build());
    }

    @GetMapping("/all")
    public ResponseEntity<PageResponse<ProductPageResponse>> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "productName") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts(pageNo, pageSize, search, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<ProductResponse>builder()
                        .status(true)
                        .message("Successfully fetched product")
                        .data(productService.getProductById(id))
                        .timestamp(Instant.now())
                        .build());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse<UpdateProductResponse>> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<UpdateProductResponse>builder()
                        .status(true)
                        .message("Successfully updated product")
                        .data(productService.updateProduct(id, request))
                        .timestamp(Instant.now())
                        .build());
    }

    @PatchMapping("/{id}/patch")
    public ResponseEntity<ApiResponse<PatchProductResponse>> patchProduct(
            @PathVariable UUID id,
            @Valid @RequestBody PatchProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<PatchProductResponse>builder()
                        .status(true)
                        .message("Successfully patched product")
                        .data(productService.patchProduct(id, request))
                        .timestamp(Instant.now())
                        .build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(
            @PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<Void>builder()
                        .status(true)
                        .message("Successfully deleted product")
                        .timestamp(Instant.now())
                        .build());
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<ApiResponse<Void>> addToCart(
            @Valid @RequestBody AddToCartRequest request) {
        productService.addProductToCart(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<Void>builder()
                        .status(true)
                        .message("Product added to cart successfully")
                        .timestamp(Instant.now())
                        .build());
    }

    @PostMapping("/add-to-wishlist")
    public ResponseEntity<ApiResponse<Void>> addToWishList(
            @Valid @RequestBody AddProductToWishListRequest request) {
        productService.addProductToWishlist(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.<Void>builder()
                        .status(true)
                        .message("Product added to wishlist successfully")
                        .timestamp(Instant.now())
                        .build());
    }
}
