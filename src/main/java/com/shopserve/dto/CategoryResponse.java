package com.shopserve.dto;

import java.util.List;

public class CategoryResponse {

    private Long id;
    private String name;
    private String description;
    private List<ProductResponse> products;

    // Constructors
    public CategoryResponse() {}

    public CategoryResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryResponse(Long id, String name, String description, List<ProductResponse> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
} 