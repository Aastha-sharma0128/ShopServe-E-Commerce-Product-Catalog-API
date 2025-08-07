package com.example.secureapi.config;

import com.example.secureapi.model.User;
import com.example.secureapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Create a default admin user if it doesn't exist
        try {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("admin123");
            adminUser.setRoles(Set.of("ADMIN"));
            
            userService.createUser(adminUser);
            System.out.println("Admin user created successfully!");
        } catch (Exception e) {
            System.out.println("Admin user already exists or creation failed: " + e.getMessage());
        }
    }
} 