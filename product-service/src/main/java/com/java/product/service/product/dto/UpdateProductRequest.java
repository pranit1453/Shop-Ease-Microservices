package com.java.product.service.product.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record UpdateProductRequest(
        @Length(min = 3, max = 50, message = "Product name should be between 3 and 50 characters")
        String productName,
        @Length(min = 3, max = 500, message = "Product description should be between 3 and 500 characters")
        String productDescription,
        Double productPrice
) {
}
