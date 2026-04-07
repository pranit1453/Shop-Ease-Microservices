package com.java.wishlist.service.repository;

import com.java.wishlist.service.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WishListRepository extends JpaRepository<WishList, UUID> {
    WishList findByProductId(UUID productId);

    boolean existsByProductId(UUID productId);
}
