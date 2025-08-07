package com.shopserve.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Home Controller", description = "Basic endpoints for testing the application")
public class HomeController {

    @GetMapping("/health")
    @Operation(summary = "Health check endpoint", description = "Returns the health status of the application")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "ShopServe application is running successfully!");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @GetMapping("/info")
    @Operation(summary = "Application info", description = "Returns basic information about the application")
    public Map<String, String> info() {
        Map<String, String> info = new HashMap<>();
        info.put("name", "ShopServe");
        info.put("version", "1.0.0");
        info.put("description", "Spring Boot application with JPA, Security, and Swagger");
        info.put("javaVersion", "17");
        return info;
    }
} 