package com.java.product.service.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateProductResponse(
        UUID productId,
        String productName,
        String productDescription,
        Double productPrice
) {
}
