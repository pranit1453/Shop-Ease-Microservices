package com.java.cart.service.channel;

import com.java.cart.service.dto.AddToCartRequest;
import com.java.cart.service.dto.WishListRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "wishlist-service",url = "http://localhost:8082")
public interface WishListClient {

    @PostMapping("/api/v1/wishlist/add")
    WishListRequest addToWishList(@RequestBody  WishListRequest wishListRequest);
}
