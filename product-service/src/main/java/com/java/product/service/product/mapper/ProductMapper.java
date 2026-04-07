package com.java.product.service.product.mapper;

import com.java.product.service.product.dto.CreateProductRequest;
import com.java.product.service.product.dto.CreateProductResponse;
import com.java.product.service.product.dto.ProductPageResponse;
import com.java.product.service.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    @Mapping(target = "productId", ignore = true)
    Product toProductEntity(CreateProductRequest request);

    CreateProductResponse toCreateProductResponse(Product product);

    ProductPageResponse toProductPageResponse(Product product);
}
