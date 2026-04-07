package com.java.product.service.subcategory.repository;

import com.java.product.service.subcategory.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {

    boolean existsByCategory_CategoryId(UUID id);

    boolean existsBySubCategoryName(String subCategoryName);

    @EntityGraph(attributePaths = "category")
    Page<SubCategory> findAll(Specification<SubCategory> spec, Pageable pageable);
}
