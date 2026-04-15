package com.java.cart.service.service;

import com.java.cart.service.channel.WishListClient;
import com.java.cart.service.dto.AddToCartRequest;
import com.java.cart.service.dto.WishListRequest;
import com.java.cart.service.entity.Cart;
import com.java.cart.service.entity.CartItem;
import com.java.cart.service.exception.custom.ResourceNotFoundException;
import com.java.cart.service.repository.CartItemRepository;
import com.java.cart.service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final WishListClient wishListClient;

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

    @Override
    @Transactional
    public void removeFromCart(final UUID id) {
        if(!cartItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with id " + id + " not found in Cart Item");
        }
        cartItemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void removeFromCartAndAddToWishlist(UUID id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + id + " not found in Cart Item"));
        WishListRequest request = WishListRequest.builder()
                .productId(cartItem.getProductId())
                .build();
        try {
            wishListClient.addToWishList(request);
            cartItemRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Failed to remove from WishList");
        }
    }
}
