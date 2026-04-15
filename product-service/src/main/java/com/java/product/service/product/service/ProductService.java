package com.java.product.service.product.service;

import com.java.product.service.product.dto.*;
import com.java.product.service.wrapper.PageResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface ProductService {

    CreateProductResponse createNewProduct(@Valid CreateProductRequest request);

    PageResponse<ProductPageResponse> getAllProducts(int pageNo, int pageSize, String search, String sortBy, String sortDir);

    ProductResponse getProductById(UUID productId);

    UpdateProductResponse updateProduct(UUID productId, @Valid UpdateProductRequest request);

    PatchProductResponse patchProduct(UUID productId, @Valid PatchProductRequest request);

    void deleteProduct(UUID productId);

    void addProductToCart(@Valid AddToCartRequest request);

    void addProductToWishlist(@Valid AddProductToWishListRequest request);
}
