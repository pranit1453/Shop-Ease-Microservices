package com.java.cart.service.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AddToCartRequest(
        UUID productId,
        Integer quantity
) {
}
