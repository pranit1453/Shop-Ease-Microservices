package com.java.wishlist.service.service;

import com.java.wishlist.service.dto.AddProductToWishListRequest;
import jakarta.validation.Valid;

public interface WishListService {
    void addToWishlist(@Valid AddProductToWishListRequest request);
}
