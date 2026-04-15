package com.java.product.service.cart.repository;

import com.java.product.service.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    Optional<CartItem> findByCartCartIdAndProductProductId(UUID cartId, UUID productId);
}
