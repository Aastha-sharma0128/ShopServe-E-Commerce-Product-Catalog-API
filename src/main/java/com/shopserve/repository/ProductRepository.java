package com.shopserve.repository;

import com.shopserve.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategoryName(String categoryName);
    
    List<Product> findByBrand(String brand);
    
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    
    List<Product> findByCategoryNameAndBrandAndPriceBetween(String categoryName, String brand, Double minPrice, Double maxPrice);
    
    @Query("SELECT p FROM Product p WHERE " +
           "(:categoryName IS NULL OR p.category.name = :categoryName) AND " +
           "(:brand IS NULL OR p.brand = :brand) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> findProductsByFilters(
            @Param("categoryName") String categoryName,
            @Param("brand") String brand,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
} 