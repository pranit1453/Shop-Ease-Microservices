package com.java.product.service.product.service;

import com.java.product.service.product.dto.AddProductToWishListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WishListClient {

    private final WebClient wishListWebClient;

    public void addToWishList(AddProductToWishListRequest request){
        wishListWebClient.post()
                .uri("/api/v1/wishlist/items")
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::isError,response->
                        Mono.error(new RuntimeException("Wishlist-service error")))
                .toBodilessEntity()
                .block();
    }
}
