package com.java.product.service.category.repository;

import com.java.product.service.category.entity.Category;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {

    boolean existsByCategoryNameIgnoreCase(String categoryName);

    @EntityGraph(attributePaths = "subCategories")
    Optional<Category> findByCategoryId(UUID categoryId);
}

/*
    @EntityGraph is used to control how related entities are fetched
    (especially for LAZY relationships) without changing your entity mapping.
    You want related data only in specific APIs

    Query...
        SELECT c.*, s.*
        FROM categories c
        LEFT JOIN subcategories s ON c.category_id = s.category_id
        WHERE c.category_id = ?;
 */