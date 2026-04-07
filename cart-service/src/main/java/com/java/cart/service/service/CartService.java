package com.java.cart.service.service;

import com.java.cart.service.dto.AddToCartRequest;

public interface CartService {
    void addToCart(AddToCartRequest request);
}
