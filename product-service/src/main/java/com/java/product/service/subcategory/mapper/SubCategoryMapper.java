package com.java.product.service.subcategory.mapper;

import com.java.product.service.subcategory.dto.CreateSubCategoryRequest;
import com.java.product.service.subcategory.dto.CreateSubCategoryResponse;
import com.java.product.service.subcategory.entity.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SubCategoryMapper {

    @Mapping(target = "subCategoryId", ignore = true)
    @Mapping(target = "products", ignore = true)
    SubCategory toSubCategoryEntity(CreateSubCategoryRequest request);

    CreateSubCategoryResponse toSubCategoryResponse(SubCategory subCategory);
}
