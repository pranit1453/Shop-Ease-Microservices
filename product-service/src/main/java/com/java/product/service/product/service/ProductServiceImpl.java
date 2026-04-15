package com.java.product.service.product.service;

import com.java.product.service.cart.service.CartService;
import com.java.product.service.channel.client.CartClient;
import com.java.product.service.exception.custom.DuplicateResourceFoundException;
import com.java.product.service.exception.custom.ResourceConflictException;
import com.java.product.service.exception.custom.ResourceNotFoundException;
import com.java.product.service.product.dto.*;
import com.java.product.service.product.entity.Product;
import com.java.product.service.product.mapper.ProductMapper;
import com.java.product.service.product.repository.ProductRepository;
import com.java.product.service.product.specification.ProductSpecification;
import com.java.product.service.subcategory.entity.SubCategory;
import com.java.product.service.subcategory.repository.SubCategoryRepository;
import com.java.product.service.wishlist.service.WishListService;
import com.java.product.service.wrapper.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductMapper productMapper;
    private final CartService cartService;
    private final WishListService wishListService;

    @Override
    @Transactional
    public CreateProductResponse createNewProduct(final CreateProductRequest request) {
        final String productName = request.productName().trim();

        if (productRepository.existsByProductName(productName)) {
            throw new DuplicateResourceFoundException("Product with name '" + productName + "' already exists");
        }

        SubCategory subCategory = subCategoryRepository.findById(request.subCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SubCategory with id " + request.subCategoryId() + " not found"));

        Product product = productMapper.toProductEntity(request);
        subCategory.addProduct(product);
        Product saved = productRepository.save(product);
        return productMapper.toCreateProductResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ProductPageResponse> getAllProducts(int pageNo, int pageSize, String search, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Specification<Product> spec = ProductSpecification.searchByName(search);

        // N+1 optimized: @EntityGraph on repository method fetches subCategory in single JOIN
        Page<Product> page = productRepository.findAll(spec, pageable);

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
    @Transactional(readOnly = true)
    public ProductResponse getProductById(final UUID productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));
        return productMapper.toProductResponse(product);
    }

    @Override
    @Transactional
    public UpdateProductResponse updateProduct(final UUID productId, final UpdateProductRequest request) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));

        if (request.productName() != null) {
            String newName = request.productName().trim();
            if (productRepository.existsByProductNameAndProductIdNot(newName, productId)) {
                throw new DuplicateResourceFoundException("Product with name '" + newName + "' already exists");
            }
        }

        if (request.productPrice() != null && request.productPrice() <= 0) {
            throw new ResourceConflictException("Product price must be greater than 0");
        }

        productMapper.updateProductFromRequest(request, product);
        Product saved = productRepository.save(product);
        return productMapper.toUpdateProductResponse(saved);
    }

    @Override
    @Transactional
    public PatchProductResponse patchProduct(final UUID productId, final PatchProductRequest request) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));

        if (request.productName() != null) {
            String newName = request.productName().trim();
            if (productRepository.existsByProductNameAndProductIdNot(newName, productId)) {
                throw new DuplicateResourceFoundException("Product with name '" + newName + "' already exists");
            }
        }

        if (request.productPrice() != null && request.productPrice() <= 0) {
            throw new ResourceConflictException("Product price must be greater than 0");
        }

        productMapper.patchProductFromRequest(request, product);
        Product saved = productRepository.save(product);
        return productMapper.toPatchProductResponse(saved);
    }

    @Override
    @Transactional
    public void deleteProduct(final UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        }
        productRepository.deleteById(productId);
    }

    @Override
    public void addProductToCart(final AddToCartRequest request) {
        Product product = productRepository.findByProductId(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + request.productId() + " not found"));

        if(product.getProductPrice()== null || product.getProductPrice()<=0){
            throw new IllegalArgumentException("Invalid product price");
        }

        if(request.quantity()<=0){
            throw new IllegalArgumentException("Invalid quantity : Quantity must be greater than 0");
        }
        cartService.addToCart(
                request.userId(),
                product,
                request.quantity()
        );
    }

    @Override
    @Transactional
    public void addProductToWishlist(final AddProductToWishListRequest request) {
        Product product = productRepository.findByProductId(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + request.productId() + " not found"));
        wishListService.addToWishList(request.userId(),
                product);
    }
}
