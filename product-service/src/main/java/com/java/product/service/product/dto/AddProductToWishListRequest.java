package com.java.product.service.product.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AddProductToWishListRequest(
        UUID productId,
        String productName,
        String productDescription,
        Double productPrice
) {
}
