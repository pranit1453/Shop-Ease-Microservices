package com.java.product.service.cart.service;

import com.java.product.service.cart.entity.Cart;
import com.java.product.service.cart.entity.CartItem;
import com.java.product.service.cart.repository.CartItemRepository;
import com.java.product.service.cart.repository.CartRepository;
import com.java.product.service.product.entity.Product;
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


    @Override
    @Transactional
    public void addToCart(final UUID userId,final Product product, final Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(()->{
                    Cart newCart = Cart.builder()
                            .userId(userId)
                            .build();
                    return cartRepository.save(newCart);
                });

        Optional<CartItem> existingCartItem = cartItemRepository.findByCartCartIdAndProductProductId(
                cart.getCartId(),
                product.getProductId()
        );

        if(existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else{
            CartItem cartItem = CartItem.builder()
                    .product(product)
                    .quantity(quantity)
                    .cart(cart)
                    .build();

            cart.getCartItems().add(cartItem);
        }

        cartRepository.save(cart);

    }
}
