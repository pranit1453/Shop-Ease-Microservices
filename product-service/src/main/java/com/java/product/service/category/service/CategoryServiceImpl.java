package com.java.product.service.category.service;

import com.java.product.service.category.dto.*;
import com.java.product.service.category.entity.Category;
import com.java.product.service.category.mapper.CategoryMapper;
import com.java.product.service.category.repository.CategoryRepository;
import com.java.product.service.category.specification.CategorySpecification;
import com.java.product.service.exception.custom.DuplicateResourceFoundException;
import com.java.product.service.exception.custom.ResourceConflictException;
import com.java.product.service.exception.custom.ResourceNotFoundException;
import com.java.product.service.subcategory.repository.SubCategoryRepository;
import com.java.product.service.wrapper.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CreateCategoryResponse createNewCategory(final CreateCategoryRequest request) {
        final String categoryName = request.categoryName().trim();

        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryName)) {
            throw new DuplicateResourceFoundException("Category already exists");
        }

        final Category category = categoryMapper.toCategoryEntity(request);
        final Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toCreateCategoryResponse(savedCategory);
    }

    @Override
    public PageResponse<CategoryResponse> getAllCategories(final int pageNo,
                                                           final int pageSize,
                                                           final String search,
                                                           final String sortBy,
                                                           final String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        final Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        final Specification<Category> spec = CategorySpecification.searchByName(search);

        final Page<Category> page = categoryRepository.findAll(spec, pageable);

        List<CategoryResponse> content = page.getContent()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();

        return PageResponse.<CategoryResponse>builder()
                .content(content)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CategoryWithSubCategoryResponse getAllSubCategoriesAssociatedWithCategory(final UUID id) {

        Category category = categoryRepository.findByCategoryId(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Category with id " + id + " not found"));

        return categoryMapper.toCategoryWithSubCategoryResponse(category);
    }

    @Override
    @Transactional
    public UpdateCategoryResponse updateCategoryDetails(final UUID id,final UpdateCategoryRequest request) {
        Category category = categoryRepository.findByCategoryId(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Category with id " + id + " not found"));

        categoryMapper.updateCategoryFromRequest(request,category);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toUpdateCategoryResponse(savedCategory);
    }

    @Override
    @Transactional
    public Void deleteCategoryDetailsById(final UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category with id " + id + " not found");
        }

        if(subCategoryRepository.existsByCategory_CategoryId(id)) {
            throw new ResourceConflictException("Category cannot be deleted because it has associated subcategories");
        }
        categoryRepository.deleteById(id);
        return null;
    }


}
