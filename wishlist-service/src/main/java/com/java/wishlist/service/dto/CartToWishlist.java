package com.java.wishlist.service.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record CartToWishlist(
        UUID productId
) {
}
