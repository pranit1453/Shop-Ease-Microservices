package com.java.product.service.category.mapper;

import com.java.product.service.category.dto.CategoryResponse;
import com.java.product.service.category.dto.CreateCategoryRequest;
import com.java.product.service.category.dto.CreateCategoryResponse;
import com.java.product.service.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "subCategory", ignore = true)
    Category toCategoryEntity(CreateCategoryRequest request);

    CreateCategoryResponse toCreateCategoryResponse(Category category);

    CategoryResponse toCategoryResponse(Category category);
}
