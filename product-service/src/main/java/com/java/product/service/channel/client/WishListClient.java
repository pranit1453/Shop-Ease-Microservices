package com.java.product.service.channel.client;

import com.java.product.service.product.dto.AddProductToWishListRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "wishlist-service",url = "http://localhost:8082")
public interface WishListClient {

    @PostMapping("/api/v1/wishlist/items")
    void addToWishList(@RequestBody AddProductToWishListRequest request);
}
