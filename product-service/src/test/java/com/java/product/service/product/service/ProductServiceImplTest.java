package com.java.product.service.product.service;

import com.java.product.service.channel.client.CartClient;
import com.java.product.service.exception.custom.DuplicateResourceFoundException;
import com.java.product.service.exception.custom.ResourceNotFoundException;
import com.java.product.service.product.dto.CreateProductRequest;
import com.java.product.service.product.dto.CreateProductResponse;
import com.java.product.service.product.dto.UpdateProductRequest;
import com.java.product.service.product.dto.UpdateProductResponse;
import com.java.product.service.product.entity.Product;
import com.java.product.service.product.mapper.ProductMapper;
import com.java.product.service.product.repository.ProductRepository;
import com.java.product.service.subcategory.entity.SubCategory;
import com.java.product.service.subcategory.repository.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private  ProductRepository testProductRepository;
    @Mock
    private  SubCategoryRepository testSubCategoryRepository;
    @Mock
    private  ProductMapper testProductMapper;
    @Mock
    private  CartClient testCartClient;
    @Mock
    private  WishListClient testWishListClient;
    @InjectMocks
    private ProductServiceImpl testProductService;

    private CreateProductRequest testCreateProductRequest;
    private final UUID subCategoryId = UUID.randomUUID();

    private Product  product;

    @BeforeEach
    public void setUp() {
         this.testCreateProductRequest = CreateProductRequest.builder()
                 .productName("Test Product")
                 .productDescription("Test Product Description")
                 .productPrice(100.0)
                 .subCategoryId(subCategoryId)
                .build();
    }

    @Nested
    @DisplayName("Test Cases related to creating a new product")
    class createNewProductTest{

        private String productName;
        private SubCategory subCategory;
        private Product savedProduct;
        private CreateProductResponse response;
        @BeforeEach
        void setUp() {
            productName = testCreateProductRequest.productName().trim();
            UUID productId = UUID.randomUUID();
            subCategory = new SubCategory();
            product = Product.builder()
                    .productId(productId)
                    .productName(productName)
                    .productDescription("Test Product Description")
                    .productPrice(100.0)
                    .build();
             savedProduct = Product.builder()
                    .productId(productId)
                    .productName(productName)
                    .productDescription("Test Product Description")
                    .productPrice(100.0)
                    .build();

             response = CreateProductResponse.builder()
                    .productId(productId)
                    .productName(productName)
                    .build();
        }
        @Test
        @DisplayName("Test case for creating new product when data is valid")
        void shouldCreateANewProductWhenDataIsValid(){
            // given

            // when
            when(testProductRepository.existsByProductName(productName))
                    .thenReturn(false);
            when(testSubCategoryRepository.findById(subCategoryId))
                    .thenReturn(Optional.of(subCategory));
            when(testProductMapper.toProductEntity(testCreateProductRequest))
                    .thenReturn(product);
            when(testProductRepository.save(product))
                    .thenReturn(savedProduct);
            when(testProductMapper.toCreateProductResponse(savedProduct))
                    .thenReturn(response);

            //actual call
            CreateProductResponse result = testProductService.createNewProduct(testCreateProductRequest);
            // then
            assertNotNull(result);
            assertEquals(productName,result.productName());
            // verify interactions
            verify(testProductRepository,times(1)).existsByProductName(productName);
            verify(testSubCategoryRepository,times(1)).findById(subCategoryId);
            verify(testProductRepository,times(1)).save(product);
        }

        @Test
        @DisplayName("Test case for duplicate resource found for product name already exists")
        void shouldThrowDuplicateResourceFoundExceptionWhenProductNameAlreadyExists() {
            when(testProductRepository.existsByProductName(productName)).thenReturn(true);

            DuplicateResourceFoundException exception = assertThrows(DuplicateResourceFoundException.class,
                    () -> testProductService.createNewProduct(testCreateProductRequest));

            assertEquals("Product with name '" + productName + "' already exists",exception.getMessage());
            verify(testProductRepository,times(1)).existsByProductName(productName);
        }

        @Test
        @DisplayName("Test case for resource not found for subcategoryId not found")
        void shouldThrowResourceNotFoundExceptionWhenSubCategoryIdNotFound() {
            when(testProductRepository.existsByProductName(productName)).thenReturn(false);
            when(testSubCategoryRepository.findById(subCategoryId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                    () -> testProductService.createNewProduct(testCreateProductRequest));

            assertEquals("SubCategory with id " + subCategoryId + " not found",exception.getMessage());
            verify(testProductRepository,times(1)).existsByProductName(productName);
            verify(testSubCategoryRepository,times(1)).findById(subCategoryId);
        }

    }

    @Nested
    @DisplayName("Tests for updating a product")
    class updateProductTest{

        private UpdateProductRequest testUpdateProductRequest;
        private final UUID productId = UUID.randomUUID();

        @BeforeEach
        void setUp() {
            product = Product.builder()
                    .productId(productId)
                    .productName("Test Product")
                    .productDescription("Test Product Description")
                    .productPrice(100.0)
                    .build();
        }
        @Test
        @DisplayName("Should update product when data is valid")
        void shouldUpdateProductWhenValidDataProvided() {
            UpdateProductRequest request = UpdateProductRequest.builder()
                    .productName("Updated Product")
                    .productPrice(200.0)
                    .build();

            Product existingProduct = Product.builder()
                    .productId(productId)
                    .productName("Old Product")
                    .productPrice(100.0)
                    .build();

            Product savedProduct = Product.builder()
                    .productId(productId)
                    .productName("Updated Product")
                    .productPrice(200.0)
                    .build();

            UpdateProductResponse response = UpdateProductResponse.builder()
                    .productId(productId)
                    .productName("Updated Product")
                    .build();
            when(testProductRepository.findByProductId(productId)).thenReturn(Optional.of(existingProduct));
            when(testProductRepository.existsByProductNameAndProductIdNot("Updated Product", productId)).thenReturn(false);
            when(testProductRepository.save(existingProduct))
                    .thenReturn(savedProduct);

            when(testProductMapper.toUpdateProductResponse(savedProduct))
                    .thenReturn(response);
            UpdateProductResponse result =
                    testProductService.updateProduct(productId, request);
            assertNotNull(result);
            assertEquals("Updated Product", result.productName());

            // verify
            verify(testProductRepository).findByProductId(productId);
            verify(testProductRepository)
                    .existsByProductNameAndProductIdNot("Updated Product", productId);
            verify(testProductMapper).updateProductFromRequest(request, existingProduct);
            verify(testProductRepository).save(existingProduct);
        }
        @Test
        @DisplayName("Should throw ResourceNotFoundException when product ID does not exist")
        void shouldThrowExceptionWhenProductNotFound() {
        }
    }

}