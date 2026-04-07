package com.java.product.service.product.service;


import com.java.product.service.exception.custom.DuplicateResourceFoundException;
import com.java.product.service.exception.custom.ResourceNotFoundException;
import com.java.product.service.channel.client.CartClient;
import com.java.product.service.product.dto.*;
import com.java.product.service.product.entity.Product;
import com.java.product.service.product.mapper.ProductMapper;
import com.java.product.service.product.repository.ProductRepository;
import com.java.product.service.product.specification.ProductSpecification;
import com.java.product.service.subcategory.entity.SubCategory;
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

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService {

    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductMapper productMapper;
    private final CartClient cartClient;
    private final WishListClient wishListClient;
    @Override
    @Transactional
    public CreateProductResponse createNewProduct(final CreateProductRequest request) {
        String productName = request.productName().trim();

        if (productRepository.existsByProductName(productName)) {
            throw new DuplicateResourceFoundException(
                    "Product with name " + productName + " already exists");
        }
        SubCategory subCategory = subCategoryRepository.findById(request.subCategoryId()).orElseThrow(
                ()-> new ResourceNotFoundException("Sub category with id " + request.subCategoryId() + " not found")
        );
        Product product = productMapper.toProductEntity(request);
        subCategory.addProduct(product);
        Product savedProduct = productRepository.save(product);
        return productMapper.toCreateProductResponse(savedProduct);
    }

    @Override
    public PageResponse<ProductPageResponse> getAllProducts(int pageNo, int pageSize, String search, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Specification<Product> spec = ProductSpecification.searchByName(search);

        Page<Product> page = productRepository.findAll(spec,pageable);

        List<ProductPageResponse> content = page.getContent()
                .stream()
                .map(productMapper::toProductPageResponse)
                .toList();

        return PageResponse.<ProductPageResponse>builder()
                .content(content)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public void addProductToCart(final AddToCartRequest request) {

        Product product = productRepository.findById(request.productId()).orElseThrow(
                ()-> new ResourceNotFoundException("Product with id " + request.productId() + " not found")
        );

        if(product.getProductPrice()<=0){
            throw new RuntimeException("Invalid product price");
        }
        cartClient.addToCart(request);


    }

    @Override
    public void addProductToWishlist(final AddProductToWishListRequest request) {
        if(productRepository.findById(request.productId()).isEmpty()){
            throw new ResourceNotFoundException("Product with id " + request.productId() + " not found");
        }

        wishListClient.addToWishList(request);
        //wishListClient.addToWishList(request);
    }
}
