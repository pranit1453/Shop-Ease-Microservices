package com.java.product.service.product.controller;

import com.java.product.service.category.dto.CreateCategoryRequest;
import com.java.product.service.category.dto.CreateCategoryResponse;
import com.java.product.service.product.dto.*;
import com.java.product.service.product.entity.Product;
import com.java.product.service.product.service.ProductService;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateProductResponse>> createNewProduct(
            @Valid
            @RequestBody CreateProductRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.<CreateProductResponse>builder()
                        .status(true)
                        .message("Successfully created new product")
                        .data(productService.createNewProduct(request))
                        .build());
    }

    @GetMapping("/all")
    public ResponseEntity<PageResponse<ProductPageResponse>> getProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "productName") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts(pageNo,pageSize,search,sortBy,sortDir));
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(
            @Valid
            @RequestBody final AddToCartRequest request){
        productService.addProductToCart(request);
        return ResponseEntity.ok("Product added to cart successfully");
    }
    @PostMapping("/add-to-wishlist")
    public ResponseEntity<String> addToWishList(
            @Valid
            @RequestBody final AddProductToWishListRequest request
    ){
        productService.addProductToWishlist(request);
        return ResponseEntity.ok("Product added to wishlist successfully");
    }
}
