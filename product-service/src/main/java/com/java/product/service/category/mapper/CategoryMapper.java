package com.java.product.service.category.mapper;

import com.java.product.service.category.dto.*;
import com.java.product.service.category.entity.Category;
import com.java.product.service.subcategory.dto.SubCategoryResponse;
import com.java.product.service.subcategory.entity.SubCategory;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    Category toCategoryEntity(CreateCategoryRequest request);

    CreateCategoryResponse toCreateCategoryResponse(Category category);

    CategoryResponse toCategoryResponse(Category category);

    CategoryWithSubCategoryResponse  toCategoryWithSubCategoryResponse(Category category);

    SubCategoryResponse toSubCategoryResponse(SubCategory subCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    void updateCategoryFromRequest(UpdateCategoryRequest request, @MappingTarget Category category);

    UpdateCategoryResponse toUpdateCategoryResponse(Category category);
}
