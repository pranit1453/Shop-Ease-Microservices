package com.java.product.service.wishlist.service;

import com.java.product.service.product.entity.Product;

import java.util.UUID;

public interface WishListService {
    void addToWishList(UUID userId, Product product);
}
