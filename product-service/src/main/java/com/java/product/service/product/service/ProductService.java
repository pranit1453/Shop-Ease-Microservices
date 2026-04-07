package com.java.product.service.product.service;


import com.java.product.service.product.dto.*;
import com.java.product.service.wrapper.PageResponse;
import jakarta.validation.Valid;

public interface ProductService {
    CreateProductResponse createNewProduct(@Valid CreateProductRequest request);

    PageResponse<ProductPageResponse> getAllProducts(int pageNo, int pageSize, String search, String sortBy, String sortDir);

    void addProductToCart(@Valid AddToCartRequest request);

    void addProductToWishlist(@Valid AddProductToWishListRequest request);
}
