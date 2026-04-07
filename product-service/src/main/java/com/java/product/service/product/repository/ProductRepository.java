package com.java.product.service.product.repository;

import com.java.product.service.product.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    boolean existsBySubCategory_SubCategoryId(UUID subCategorySubCategoryId);

    boolean existsByProductName(String productName);

    boolean findByProductId(UUID productId);
}
