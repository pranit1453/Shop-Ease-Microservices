package com.java.wishlist.service.controller;

import com.java.wishlist.service.dto.AddProductToWishListRequest;
import com.java.wishlist.service.dto.CartToWishlist;
import com.java.wishlist.service.entity.WishList;
import com.java.wishlist.service.service.WishListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/items")
    public ResponseEntity<String> addToWishList(@Valid @RequestBody AddProductToWishListRequest request){
        wishListService.addToWishlist(request);
        return ResponseEntity.ok("Product added to Wishlist successfully");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFromCartToWishlist(@Valid
                                                        @RequestBody CartToWishlist request){
        wishListService.itemFromCartToWishlist(request);
        return ResponseEntity.ok("Product added to Wishlist successfully(Removed From Cart)");
    }
}
