package com.java.product.service.wishlist.service;

import com.java.product.service.product.entity.Product;
import com.java.product.service.product.repository.ProductRepository;
import com.java.product.service.wishlist.entity.WishList;
import com.java.product.service.wishlist.entity.WishListItem;
import com.java.product.service.wishlist.repository.WishListItemRepository;
import com.java.product.service.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;
    private final WishListItemRepository wishListItemRepository;

    @Override
    @Transactional
    public void addToWishList(UUID userId, Product product) {
        WishList wishList = wishListRepository.findByUserId(userId)
                .orElseGet(()->{
                    WishList newWishList = WishList.builder()
                            .userId(userId)
                            .build();
                    return wishListRepository.save(newWishList);
                });

        Optional<WishListItem> existingWishListItem = wishListItemRepository
                .findByWishListWishlistIdAndProductProductId(
                        wishList.getWishlistId(),
                        product.getProductId()
                );

        if(existingWishListItem.isPresent()){
            throw new IllegalArgumentException("Product with id " + product.getProductId() + " already exists in Wishlist");
        }

        WishListItem newWishListItem = WishListItem.builder()
                .product(product)
                .build();

        wishList.addItem(newWishListItem);
        wishListRepository.save(wishList);
    }
}
