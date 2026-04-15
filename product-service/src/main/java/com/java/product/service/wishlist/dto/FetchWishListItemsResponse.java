package com.java.product.service.wishlist.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record FetchWishListItemsResponse(
        UUID wishListId,
        UUID wishListItemId,
        UUID productId,
        String productName,
        String productDescription,
        Double productPrice
) {
}
