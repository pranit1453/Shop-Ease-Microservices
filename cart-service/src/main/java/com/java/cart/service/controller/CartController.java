package com.java.cart.service.controller;

import com.java.cart.service.dto.AddToCartRequest;
import com.java.cart.service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {
        cartService.addToCart(request);
        return ResponseEntity.ok("Item added to cart");
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<String> removeFromCart(@PathVariable UUID id){
        cartService.removeFromCart(id);
        return ResponseEntity.ok("Product Remove From Cart");
    }

    @PostMapping("/remove/item-from-cart/{id}/add-to/wishlist")
    public ResponseEntity<String> removeFromCartAndMoveToWishList(@PathVariable UUID id){
        cartService.removeFromCartAndAddToWishlist(id);
        return ResponseEntity.ok("Item removed from cart and added to wishlist");
    }
}
