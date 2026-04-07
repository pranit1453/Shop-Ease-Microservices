package com.java.product.service.channel.client;

import com.java.product.service.product.dto.AddToCartRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "cart-service",url = "http://localhost:8081")
public interface CartClient {
    @PostMapping("/api/v1/cart/items")
    void addToCart(@RequestBody  AddToCartRequest request);
}

/*
@EnableFeignClients in main method then its proxy implementation get created at runtime
1. @FeignClient(...)
public interface xxxClient
2. Spring creates a proxy object at runtime
    spring+feign
3. Convert Methods to Http call
4. send request to target service
 */