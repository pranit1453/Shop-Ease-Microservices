package com.java.product.service.subcategory.service;

import com.java.product.service.category.entity.Category;
import com.java.product.service.category.repository.CategoryRepository;
import com.java.product.service.exception.custom.DuplicateResourceFoundException;
import com.java.product.service.exception.custom.ResourceConflictException;
import com.java.product.service.exception.custom.ResourceNotFoundException;
import com.java.product.service.product.repository.ProductRepository;
import com.java.product.service.subcategory.dto.CreateSubCategoryRequest;
import com.java.product.service.subcategory.dto.CreateSubCategoryResponse;
import com.java.product.service.subcategory.dto.SubCategoryResponseDto;
import com.java.product.service.subcategory.entity.SubCategory;
import com.java.product.service.subcategory.mapper.SubCategoryMapper;
import com.java.product.service.subcategory.repository.SubCategoryRepository;
import com.java.product.service.subcategory.specification.SubCategorySpecification;
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
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository  categoryRepository;
    private final ProductRepository productRepository;
    private final SubCategoryMapper  subCategoryMapper;
    @Override
    @Transactional
    public CreateSubCategoryResponse createSubCategory(CreateSubCategoryRequest request) {
        String subCategoryName = request.subCategoryName().trim();
        if(subCategoryRepository.existsBySubCategoryName(subCategoryName)){
            throw new DuplicateResourceFoundException("Subcategory with name " + subCategoryName + " already exists");
        }

        Category category = categoryRepository.findByCategoryId(request.categoryId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Category with id " + request.categoryId() + " not found"));

        final SubCategory subCategory = subCategoryMapper.toSubCategoryEntity(request);
        subCategory.setCategory(category);
        SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
        return subCategoryMapper.toSubCategoryResponse(savedSubCategory);
    }

    @Override
    public PageResponse<SubCategoryResponseDto> fetchAllSubCategories(int pageNo, int pageSize, String search, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        final Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        final Specification<SubCategory> spec = SubCategorySpecification.searchByName(search);
        final Page<SubCategory> page = subCategoryRepository.findAll(spec,pageable);

        List<SubCategoryResponseDto> content = page.getContent()
                .stream()
                .map(subCategoryMapper::toSubCategoryDto)
                .toList();

        return PageResponse.<SubCategoryResponseDto>builder()
                .content(content)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();

    }

    @Override
    @Transactional
    public Void deleteSubCategory(UUID id) {
        if(!subCategoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Subcategory with id " + id + " not found");
        }

        if(productRepository.existsBySubCategory_SubCategoryId(id)){
            throw new ResourceConflictException("Subcategory cannot be deleted because it has associated products");
        }
        subCategoryRepository.deleteById(id);
        return null;
    }
}
