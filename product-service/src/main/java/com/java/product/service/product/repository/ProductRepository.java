package com.java.product.service.product.repository;

import com.java.product.service.product.entity.Product;
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
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

    boolean existsBySubCategory_SubCategoryId(UUID subCategorySubCategoryId);

    boolean existsByProductName(String productName);

    boolean existsByProductNameAndProductIdNot(String productName, UUID productId);

    // N+1 fix: eagerly fetch subCategory in a single JOIN query
    @EntityGraph(attributePaths = "subCategory")
    Optional<Product> findByProductId(UUID productId);

    // N+1 fix: eagerly fetch subCategory for paginated list
    @EntityGraph(attributePaths = "subCategory")
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
}
