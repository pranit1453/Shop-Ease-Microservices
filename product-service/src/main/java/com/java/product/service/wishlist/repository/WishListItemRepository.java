package com.java.product.service.wishlist.repository;

import com.java.product.service.wishlist.entity.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, UUID> {
    Optional<WishListItem> findByWishListWishlistIdAndProductProductId(UUID wishListWishlistId, UUID productProductId);
}
