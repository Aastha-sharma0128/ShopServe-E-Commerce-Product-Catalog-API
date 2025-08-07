package com.shopserve.config;

import com.shopserve.entity.Category;
import com.shopserve.entity.Product;
import com.shopserve.repository.CategoryRepository;
import com.shopserve.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if no categories exist
        if (categoryRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Create categories
        Category electronics = new Category("Electronics", "Electronic devices and gadgets");
        Category clothing = new Category("Clothing", "Fashion and apparel");
        Category books = new Category("Books", "Books and publications");
        Category home = new Category("Home & Garden", "Home improvement and garden supplies");

        categoryRepository.save(electronics);
        categoryRepository.save(clothing);
        categoryRepository.save(books);
        categoryRepository.save(home);

        // Create products
        Product laptop = new Product("MacBook Pro", "Apple", 1299.99, 
                "High-performance laptop for professionals", electronics);
        
        Product smartphone = new Product("iPhone 15", "Apple", 799.99, 
                "Latest smartphone with advanced features", electronics);
        
        Product tshirt = new Product("Cotton T-Shirt", "Nike", 29.99, 
                "Comfortable cotton t-shirt for everyday wear", clothing);
        
        Product jeans = new Product("Slim Fit Jeans", "Levi's", 59.99, 
                "Classic slim fit jeans in blue denim", clothing);
        
        Product novel = new Product("The Great Gatsby", "F. Scott Fitzgerald", 12.99, 
                "Classic American novel about the Jazz Age", books);
        
        Product gardenTool = new Product("Garden Shovel", "HomeDepot", 24.99, 
                "Durable garden shovel for landscaping", home);

        productRepository.save(laptop);
        productRepository.save(smartphone);
        productRepository.save(tshirt);
        productRepository.save(jeans);
        productRepository.save(novel);
        productRepository.save(gardenTool);

        System.out.println("Sample data initialized successfully!");
    }
} 