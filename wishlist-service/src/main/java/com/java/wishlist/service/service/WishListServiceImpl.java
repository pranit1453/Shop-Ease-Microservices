package com.java.wishlist.service.service;

import com.java.wishlist.service.dto.AddProductToWishListRequest;
import com.java.wishlist.service.dto.CartToWishlist;
import com.java.wishlist.service.entity.WishList;
import com.java.wishlist.service.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements  WishListService {

    private final WishListRepository  wishListRepository;
    @Override
    public void addToWishlist(AddProductToWishListRequest request) {
        UUID productId = request.productId();
        if(wishListRepository.existsByProductId(productId)){
            throw new IllegalArgumentException("Product already exists in WishList");
        }
        WishList wishList = WishList.builder()
                .productId(productId)
                .productName(request.productName())
                .productDescription(request.productDescription())
                .productPrice(request.productPrice())
                .build();
        wishListRepository.save(wishList);
    }

    @Override
    @Transactional
    public void itemFromCartToWishlist(CartToWishlist request) {
        UUID productId = request.productId();
        if(wishListRepository.existsByProductId(productId)){
            throw new IllegalArgumentException("Product already exists in WishList");
        }
        WishList wishList = WishList.builder()
                .productId(productId)
                .build();
        wishListRepository.save(wishList);
    }
}
