package com.java.cart.service.service;

import com.java.cart.service.dto.AddToCartRequest;
import com.java.cart.service.entity.Cart;
import com.java.cart.service.entity.CartItem;
import com.java.cart.service.repository.CartItemRepository;
import com.java.cart.service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public void addToCart(final AddToCartRequest request) {
        Cart cart = cartRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> cartRepository.save(new Cart()));
        Optional<CartItem> existing = cartItemRepository.findByProductId(request.productId());

        CartItem cartItem;
        if(existing.isPresent()) {
            cartItem = existing.get();
            cartItem.setQuantity(cartItem.getQuantity()+ request.quantity());
        }else {
            cartItem = CartItem.builder()
                    .productId(request.productId())
                    .quantity(request.quantity())
                    .cart(cart)
                    .build();
        }
        cartItemRepository.save(cartItem);
    }
}
