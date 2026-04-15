package com.java.cart.service.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record WishListRequest(
        UUID productId
) {
}
