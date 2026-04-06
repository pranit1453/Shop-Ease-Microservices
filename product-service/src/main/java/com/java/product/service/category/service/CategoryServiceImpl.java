package com.java.product.service.category.service;

import com.java.product.service.category.dto.CategoryResponse;
import com.java.product.service.category.dto.CreateCategoryRequest;
import com.java.product.service.category.dto.CreateCategoryResponse;
import com.java.product.service.category.entity.Category;
import com.java.product.service.category.mapper.CategoryMapper;
import com.java.product.service.category.repository.CategoryRepository;
import com.java.product.service.category.specification.CategorySpecification;
import com.java.product.service.exception.custom.DuplicateResourceFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
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
    public Page<CategoryResponse> getAllCategories(final int pageNo,
                                                   final int pageSize,
                                                   final String search,
                                                   final String sortBy,
                                                   final String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        final Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        final Specification<Category> spec = CategorySpecification.searchByName(search);

        final Page<Category> pages = categoryRepository.findAll(spec, pageable);
        return pages.map(categoryMapper::toCategoryResponse);
    }


}
