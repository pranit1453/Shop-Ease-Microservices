package com.java.product.service.cart.service;

import com.java.product.service.product.dto.ProductResponse;
import com.java.product.service.product.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public interface CartService {

    void addToCart(@NotNull(message = "User ID is required") UUID userId,
                   Product product,
                   @NotNull(message = "Quantity is required") @Min(value = 1, message = "Quantity must be at least 1") Integer quantity);
}
