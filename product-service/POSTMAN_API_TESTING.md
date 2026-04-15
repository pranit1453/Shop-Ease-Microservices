# Product Service API Collection - Postman Testing Guide

## Base URL
```
http://localhost:8080/api/v1
```

## Table of Contents
- [Category APIs](#category-apis)
- [SubCategory APIs](#subcategory-apis)
- [Product APIs](#product-apis)

---

## Category APIs

### 1. Create Category
**POST** `/category/create`

**Description:** Create a new category

**Request Body:**
```json
{
  "categoryName": "Electronics",
  "categoryDescription": "Electronic devices and accessories"
}
```

**Response:**
```json
{
  "status": true,
  "message": "Category Created Successfully",
  "data": {
    "categoryId": "uuid-here",
    "categoryName": "Electronics",
    "categoryDescription": "Electronic devices and accessories"
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 2. Get All Categories
**GET** `/category/all`

**Description:** Get all categories with pagination and search

**Query Parameters:**
- `pageNo` (optional, default: 0) - Page number
- `pageSize` (optional, default: 10) - Page size
- `search` (optional) - Search term for category name
- `sortBy` (optional, default: "categoryId") - Sort field
- `sortDir` (optional, default: "ASC") - Sort direction (ASC/DESC)

**Example URL:**
```
http://localhost:8080/api/v1/category/all?pageNo=0&pageSize=10&search=Electronics&sortBy=categoryName&sortDir=ASC
```

**Response:**
```json
{
  "content": [
    {
      "categoryId": "uuid-here",
      "categoryName": "Electronics",
      "categoryDescription": "Electronic devices and accessories"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1,
  "last": true
}
```

---

### 3. Get Category by ID with SubCategories
**GET** `/category/category/{id}`

**Description:** Get a specific category along with its subcategories

**Path Parameters:**
- `id` (required) - Category UUID

**Example URL:**
```
http://localhost:8080/api/v1/category/category/uuid-here
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully fetched Category with Sub Categories",
  "data": {
    "categoryId": "uuid-here",
    "categoryName": "Electronics",
    "categoryDescription": "Electronic devices and accessories",
    "subCategories": [
      {
        "subCategoryId": "uuid-here",
        "subCategoryName": "Laptops",
        "subCategoryDescription": "Laptop computers"
      }
    ]
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 4. Update Category (Full Update)
**PUT** `/category/update/{id}`

**Description:** Fully update a category (all fields required)

**Path Parameters:**
- `id` (required) - Category UUID

**Request Body:**
```json
{
  "categoryName": "Electronics Updated",
  "categoryDescription": "Updated description for electronic devices"
}
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully updated Category Details",
  "data": {
    "categoryId": "uuid-here",
    "categoryName": "Electronics Updated",
    "categoryDescription": "Updated description for electronic devices"
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 5. Patch Category (Partial Update)
**PATCH** `/category/patch/{id}`

**Description:** Partially update a category (only provided fields will be updated)

**Path Parameters:**
- `id` (required) - Category UUID

**Request Body (Example 1 - Update name only):**
```json
{
  "categoryName": "Electronics New Name"
}
```

**Request Body (Example 2 - Update description only):**
```json
{
  "categoryDescription": "New description"
}
```

**Request Body (Example 3 - Update both):**
```json
{
  "categoryName": "Electronics",
  "categoryDescription": "Updated description"
}
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully patched Category Details",
  "data": {
    "categoryId": "uuid-here",
    "categoryName": "Electronics",
    "categoryDescription": "Updated description"
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 6. Delete Category
**DELETE** `/category/{id}/delete`

**Description:** Delete a category (will fail if it has associated subcategories)

**Path Parameters:**
- `id` (required) - Category UUID

**Example URL:**
```
http://localhost:8080/api/v1/category/uuid-here/delete
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully deleted Category Details",
  "data": null,
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

## SubCategory APIs

### 1. Create SubCategory
**POST** `/subCategory/create`

**Description:** Create a new subcategory

**Request Body:**
```json
{
  "subCategoryName": "Laptops",
  "subCategoryDescription": "Laptop computers and accessories",
  "categoryId": "category-uuid-here"
}
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully created subcategory",
  "data": {
    "subCategoryId": "uuid-here",
    "subCategoryName": "Laptops",
    "subCategoryDescription": "Laptop computers and accessories"
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 2. Get All SubCategories
**GET** `/subCategory/all`

**Description:** Get all subcategories with pagination and search

**Query Parameters:**
- `pageNo` (optional, default: 0) - Page number
- `pageSize` (optional, default: 10) - Page size
- `search` (optional) - Search term for subcategory name
- `sortBy` (optional, default: "subCategoryName") - Sort field
- `sortDir` (optional, default: "ASC") - Sort direction (ASC/DESC)

**Example URL:**
```
http://localhost:8080/api/v1/subCategory/all?pageNo=0&pageSize=10&search=Laptops&sortBy=subCategoryName&sortDir=ASC
```

**Response:**
```json
{
  "content": [
    {
      "subCategoryId": "uuid-here",
      "subCategoryName": "Laptops",
      "subCategoryDescription": "Laptop computers and accessories",
      "categoryId": "category-uuid-here",
      "categoryName": "Electronics"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1,
  "last": true
}
```

---

### 3. Get SubCategory by ID with Products
**GET** `/subCategory/{id}`

**Description:** Get a specific subcategory along with its products

**Path Parameters:**
- `id` (required) - SubCategory UUID

**Example URL:**
```
http://localhost:8080/api/v1/subCategory/uuid-here
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully fetched subcategory with products",
  "data": {
    "subCategoryId": "uuid-here",
    "subCategoryName": "Laptops",
    "subCategoryDescription": "Laptop computers and accessories",
    "categoryId": "category-uuid-here",
    "categoryName": "Electronics",
    "products": [
      {
        "productId": "uuid-here",
        "productName": "MacBook Pro",
        "productDescription": "Apple laptop",
        "price": 1299.99
      }
    ]
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 4. Update SubCategory (Full Update)
**PUT** `/subCategory/{id}/update`

**Description:** Fully update a subcategory (all fields required)

**Path Parameters:**
- `id` (required) - SubCategory UUID

**Request Body:**
```json
{
  "subCategoryName": "Laptops Updated",
  "subCategoryDescription": "Updated description for laptops"
}
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully updated subcategory",
  "data": {
    "subCategoryId": "uuid-here",
    "subCategoryName": "Laptops Updated",
    "subCategoryDescription": "Updated description for laptops",
    "categoryId": "category-uuid-here"
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 5. Patch SubCategory (Partial Update)
**PATCH** `/subCategory/{id}/patch`

**Description:** Partially update a subcategory (only provided fields will be updated)

**Path Parameters:**
- `id` (required) - SubCategory UUID

**Request Body (Example 1 - Update name only):**
```json
{
  "subCategoryName": "Laptops New Name"
}
```

**Request Body (Example 2 - Update description only):**
```json
{
  "subCategoryDescription": "New description"
}
```

**Request Body (Example 3 - Update both):**
```json
{
  "subCategoryName": "Laptops",
  "subCategoryDescription": "Updated description"
}
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully patched subcategory",
  "data": {
    "subCategoryId": "uuid-here",
    "subCategoryName": "Laptops",
    "subCategoryDescription": "Updated description",
    "categoryId": "category-uuid-here"
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 6. Delete SubCategory
**DELETE** `/subCategory/{id}/delete`

**Description:** Delete a subcategory (will fail if it has associated products)

**Path Parameters:**
- `id` (required) - SubCategory UUID

**Example URL:**
```
http://localhost:8080/api/v1/subCategory/uuid-here/delete
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully deleted subcategory",
  "data": null,
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

## Product APIs

### 1. Create Product
**POST** `/products/create`

**Description:** Create a new product

**Request Body:**
```json
{
  "productName": "MacBook Pro",
  "productDescription": "Apple MacBook Pro with M3 chip",
  "productPrice": 1299.99,
  "subCategoryId": "subcategory-uuid-here"
}
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully created new product",
  "data": {
    "productId": "uuid-here",
    "productName": "MacBook Pro",
    "productDescription": "Apple MacBook Pro with M3 chip",
    "price": 1299.99
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 2. Get All Products
**GET** `/products/all`

**Description:** Get all products with pagination and search

**Query Parameters:**
- `pageNo` (optional, default: 0) - Page number
- `pageSize` (optional, default: 10) - Page size
- `search` (optional) - Search term for product name
- `sortBy` (optional, default: "productName") - Sort field
- `sortDir` (optional, default: "ASC") - Sort direction (ASC/DESC)

**Example URL:**
```
http://localhost:8080/api/v1/products/all?pageNo=0&pageSize=10&search=MacBook&sortBy=productName&sortDir=ASC
```

**Response:**
```json
{
  "content": [
    {
      "productId": "uuid-here",
      "productName": "MacBook Pro",
      "productDescription": "Apple MacBook Pro with M3 chip",
      "price": 1299.99,
      "subCategoryId": "subcategory-uuid-here",
      "subCategoryName": "Laptops"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1,
  "last": true
}
```

---

### 3. Get Product by ID
**GET** `/products/{id}`

**Description:** Get a specific product by ID

**Path Parameters:**
- `id` (required) - Product UUID

**Example URL:**
```
http://localhost:8080/api/v1/products/uuid-here
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully fetched product",
  "data": {
    "productId": "uuid-here",
    "productName": "MacBook Pro",
    "productDescription": "Apple MacBook Pro with M3 chip",
    "price": 1299.99,
    "subCategoryId": "subcategory-uuid-here",
    "subCategoryName": "Laptops"
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 4. Update Product (Full Update)
**PUT** `/products/{id}/update`

**Description:** Fully update a product (all fields required)

**Path Parameters:**
- `id` (required) - Product UUID

**Request Body:**
```json
{
  "productName": "MacBook Pro Updated",
  "productDescription": "Updated description for MacBook Pro",
  "productPrice": 1399.99
}
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully updated product",
  "data": {
    "productId": "uuid-here",
    "productName": "MacBook Pro Updated",
    "productDescription": "Updated description for MacBook Pro",
    "price": 1399.99
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 5. Patch Product (Partial Update)
**PATCH** `/products/{id}/patch`

**Description:** Partially update a product (only provided fields will be updated)

**Path Parameters:**
- `id` (required) - Product UUID

**Request Body (Example 1 - Update name only):**
```json
{
  "productName": "MacBook Pro New Name"
}
```

**Request Body (Example 2 - Update description only):**
```json
{
  "productDescription": "New description"
}
```

**Request Body (Example 3 - Update price only):**
```json
{
  "productPrice": 1499.99
}
```

**Request Body (Example 4 - Update multiple fields):**
```json
{
  "productName": "MacBook Pro",
  "productDescription": "Updated description",
  "productPrice": 1399.99
}
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully patched product",
  "data": {
    "productId": "uuid-here",
    "productName": "MacBook Pro",
    "productDescription": "Updated description",
    "price": 1399.99,
    "subCategoryId": "subcategory-uuid-here",
    "subCategoryName": "Laptops"
  },
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 6. Delete Product
**DELETE** `/products/{id}/delete`

**Description:** Delete a product

**Path Parameters:**
- `id` (required) - Product UUID

**Example URL:**
```
http://localhost:8080/api/v1/products/uuid-here/delete
```

**Response:**
```json
{
  "status": true,
  "message": "Successfully deleted product",
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 7. Add Product to Cart
**POST** `/products/add-to-cart`

**Description:** Add a product to the shopping cart

**Request Body:**
```json
{
  "productId": "product-uuid-here",
  "userId": "user-uuid-here",
  "quantity": 2
}
```

**Response:**
```json
{
  "status": true,
  "message": "Product added to cart successfully",
  "data": null,
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

### 8. Add Product to Wishlist
**POST** `/products/add-to-wishlist`

**Description:** Add a product to the wishlist

**Request Body:**
```json
{
  "productId": "product-uuid-here",
  "userId": "user-uuid-here"
}
```

**Response:**
```json
{
  "status": true,
  "message": "Product added to wishlist successfully",
  "data": null,
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

## Common Headers

For all POST, PUT, and PATCH requests, include the following header:

```
Content-Type: application/json
```

## Error Responses

### Resource Not Found (404)
```json
{
  "status": false,
  "message": "Category with id uuid-here not found",
  "data": null,
  "timestamp": "2024-04-15T09:00:00Z"
}
```

### Duplicate Resource (409)
```json
{
  "status": false,
  "message": "Category already exists",
  "data": null,
  "timestamp": "2024-04-15T09:00:00Z"
}
```

### Resource Conflict (409)
```json
{
  "status": false,
  "message": "Category cannot be deleted because it has associated subcategories",
  "data": null,
  "timestamp": "2024-04-15T09:00:00Z"
}
```

### Validation Error (400)
```json
{
  "status": false,
  "message": "Category name is mandatory",
  "data": null,
  "timestamp": "2024-04-15T09:00:00Z"
}
```

---

## Testing Workflow

### Recommended Testing Order:

1. **Create a Category**
   - Use `POST /category/create`
   - Save the returned `categoryId`

2. **Create a SubCategory**
   - Use `POST /subCategory/create` with the `categoryId` from step 1
   - Save the returned `subCategoryId`

3. **Create a Product**
   - Use `POST /products/create` with the `subCategoryId` from step 2
   - Save the returned `productId`

4. **Test GET Operations**
   - `GET /category/all`
   - `GET /category/category/{categoryId}`
   - `GET /subCategory/all`
   - `GET /subCategory/{subCategoryId}`
   - `GET /products/all`
   - `GET /products/{productId}`

5. **Test Update Operations**
   - `PUT /category/update/{categoryId}`
   - `PUT /subCategory/{subCategoryId}/update`
   - `PUT /products/{productId}/update`

6. **Test Patch Operations**
   - `PATCH /category/patch/{categoryId}`
   - `PATCH /subCategory/{subCategoryId}/patch`
   - `PATCH /products/{productId}/patch`

7. **Test Delete Operations**
   - `DELETE /products/{productId}/delete`
   - `DELETE /subCategory/{subCategoryId}/delete`
   - `DELETE /category/{categoryId}/delete`

8. **Test Business Logic**
   - `POST /products/add-to-cart`
   - `POST /products/add-to-wishlist`

---

## Notes

- All IDs are UUIDs and should be replaced with actual UUIDs from your database
- Price values must be greater than 0
- Names cannot be empty and must meet length requirements (3-50 characters)
- Descriptions are optional but if provided must meet length requirements (5-500 characters)
- Delete operations will fail if the resource has dependent child records
- Use PATCH for partial updates (only provided fields will be updated)
- Use PUT for full updates (all fields should be provided)
