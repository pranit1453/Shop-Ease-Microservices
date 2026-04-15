package com.java.product.service.subcategory.mapper;

import com.java.product.service.product.dto.ProductResponse;
import com.java.product.service.product.entity.Product;
import com.java.product.service.subcategory.dto.*;
import com.java.product.service.subcategory.entity.SubCategory;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubCategoryMapper {

    @Mapping(target = "subCategoryId", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "category", ignore = true)
    SubCategory toSubCategoryEntity(CreateSubCategoryRequest request);

    CreateSubCategoryResponse toSubCategoryResponse(SubCategory subCategory);

    @Mapping(source = "category.categoryId", target = "categoryId")
    SubCategoryResponseDto toSubCategoryDto(SubCategory subCategory);

    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    SubCategoryWithProductsResponse toSubCategoryWithProductsResponse(SubCategory subCategory);

    @Mapping(source = "productPrice", target = "price")
    ProductResponse toProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subCategoryId", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateSubCategoryFromRequest(UpdateSubCategoryRequest request, @MappingTarget SubCategory subCategory);

    @Mapping(source = "category.categoryId", target = "categoryId")
    UpdateSubCategoryResponse toUpdateSubCategoryResponse(SubCategory subCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subCategoryId", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "category", ignore = true)
    void patchSubCategoryFromRequest(PatchSubCategoryRequest request, @MappingTarget SubCategory subCategory);

    @Mapping(source = "category.categoryId", target = "categoryId")
    PatchSubCategoryResponse toPatchSubCategoryResponse(SubCategory subCategory);
}
