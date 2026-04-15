package com.java.product.service.wishlist.repository;

import com.java.product.service.wishlist.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WishListRepository extends JpaRepository<WishList, UUID> {
    Optional<WishList> findByUserId(UUID userId);
}
