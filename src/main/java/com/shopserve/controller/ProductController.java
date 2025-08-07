package com.shopserve.controller;

import com.shopserve.dto.ProductRequest;
import com.shopserve.dto.ProductResponse;
import com.shopserve.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "APIs for managing products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve a list of all products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve a specific product by its ID")
    public ResponseEntity<ProductResponse> getProductById(
            @Parameter(description = "Product ID") @PathVariable Long id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter products", description = "Filter products by category, brand, and price range")
    public ResponseEntity<List<ProductResponse>> filterProducts(
            @Parameter(description = "Category name") @RequestParam(required = false) String category,
            @Parameter(description = "Brand name") @RequestParam(required = false) String brand,
            @Parameter(description = "Minimum price") @RequestParam(required = false) Double minPrice,
            @Parameter(description = "Maximum price") @RequestParam(required = false) Double maxPrice) {
        List<ProductResponse> products = productService.filterProducts(category, brand, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryName}")
    @Operation(summary = "Get products by category", description = "Retrieve products by category name")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(
            @Parameter(description = "Category name") @PathVariable String categoryName) {
        List<ProductResponse> products = productService.getProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand/{brand}")
    @Operation(summary = "Get products by brand", description = "Retrieve products by brand name")
    public ResponseEntity<List<ProductResponse>> getProductsByBrand(
            @Parameter(description = "Brand name") @PathVariable String brand) {
        List<ProductResponse> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create new product", description = "Create a new product with image upload (Admin only)")
    public ResponseEntity<ProductResponse> createProduct(
            @Parameter(description = "Product name") @RequestParam String name,
            @Parameter(description = "Product brand") @RequestParam String brand,
            @Parameter(description = "Product price") @RequestParam Double price,
            @Parameter(description = "Category ID") @RequestParam Long categoryId,
            @Parameter(description = "Product description") @RequestParam(required = false) String description,
            @Parameter(description = "Product image") @RequestParam(required = false) MultipartFile image) {
        
        ProductRequest request = new ProductRequest();
        request.setName(name);
        request.setBrand(brand);
        request.setPrice(price);
        request.setCategoryId(categoryId);
        request.setDescription(description);
        request.setImage(image);

        ProductResponse product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update product", description = "Update an existing product (Admin only)")
    public ResponseEntity<ProductResponse> updateProduct(
            @Parameter(description = "Product ID") @PathVariable Long id,
            @Parameter(description = "Product name") @RequestParam String name,
            @Parameter(description = "Product brand") @RequestParam String brand,
            @Parameter(description = "Product price") @RequestParam Double price,
            @Parameter(description = "Category ID") @RequestParam Long categoryId,
            @Parameter(description = "Product description") @RequestParam(required = false) String description,
            @Parameter(description = "Product image") @RequestParam(required = false) MultipartFile image) {
        
        ProductRequest request = new ProductRequest();
        request.setName(name);
        request.setBrand(brand);
        request.setPrice(price);
        request.setCategoryId(categoryId);
        request.setDescription(description);
        request.setImage(image);

        ProductResponse product = productService.updateProduct(id, request);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete product", description = "Delete a product (Admin only)")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "Product ID") @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
} 