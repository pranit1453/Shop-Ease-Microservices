package com.java.cart.service.service;

import com.java.cart.service.dto.AddToCartRequest;

import java.util.UUID;

public interface CartService {
    void addToCart(AddToCartRequest request);

    void removeFromCart(UUID id);

    void removeFromCartAndAddToWishlist(UUID id);
}
