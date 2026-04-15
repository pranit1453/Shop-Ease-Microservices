package com.java.product.service.product.mapper;

import com.java.product.service.product.dto.*;
import com.java.product.service.product.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "subCategory", ignore = true)
    Product toProductEntity(CreateProductRequest request);

    CreateProductResponse toCreateProductResponse(Product product);

    @Mapping(source = "productPrice", target = "price")
    @Mapping(source = "subCategory.subCategoryId", target = "subCategoryId")
    @Mapping(source = "subCategory.subCategoryName", target = "subCategoryName")
    ProductPageResponse toProductPageResponse(Product product);

    @Mapping(source = "productPrice", target = "price")
    ProductResponse toProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "subCategory", ignore = true)
    void updateProductFromRequest(UpdateProductRequest request, @MappingTarget Product product);

    UpdateProductResponse toUpdateProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "subCategory", ignore = true)
    void patchProductFromRequest(PatchProductRequest request, @MappingTarget Product product);

    @Mapping(source = "productPrice", target = "price")
    @Mapping(source = "subCategory.subCategoryId", target = "subCategoryId")
    @Mapping(source = "subCategory.subCategoryName", target = "subCategoryName")
    PatchProductResponse toPatchProductResponse(Product product);
}
