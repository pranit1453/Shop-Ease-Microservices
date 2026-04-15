package com.java.product.service.subcategory.repository;

import com.java.product.service.subcategory.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID>, JpaSpecificationExecutor<SubCategory> {

    boolean existsByCategory_CategoryId(UUID id);

    boolean existsBySubCategoryName(String subCategoryName);

    boolean existsBySubCategoryNameAndSubCategoryIdNot(String subCategoryName, UUID subCategoryId);

    // N+1 fix: eagerly fetch category for paginated list
    @EntityGraph(attributePaths = "category")
    Page<SubCategory> findAll(Specification<SubCategory> spec, Pageable pageable);

    // N+1 fix: eagerly fetch category + products in one query for detail view
    @EntityGraph(attributePaths = {"category", "products"})
    Optional<SubCategory> findBySubCategoryId(UUID subCategoryId);
}
