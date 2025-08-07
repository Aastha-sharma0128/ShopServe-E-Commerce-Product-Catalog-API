# ShopServe - Spring Boot Application

A comprehensive e-commerce Spring Boot application built with Java 17 and modern Spring technologies.

## Features

- **Spring Boot 3.2.0** with Java 17
- **Spring Web** for RESTful web services
- **Spring Data JPA** for database operations
- **Spring Security** with role-based authentication (ADMIN/USER)
- **H2 Database** for in-memory data storage
- **Spring Boot DevTools** for development convenience
- **Spring Boot Validation** for input validation
- **SpringDoc OpenAPI** for API documentation (Swagger UI)
- **File Upload** support for product images
- **Global Exception Handling** with structured error responses
- **Sample Data** initialization for testing

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### 1. Clone or Download the Project

Make sure you have the project files in your local directory.

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or alternatively:

```bash
java -jar target/shopserve-0.0.1-SNAPSHOT.jar
```

### 4. Access the Application

Once the application starts, you can access:

- **Main Application**: http://localhost:8080
- **H2 Database Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:shopservedb`
  - Username: `sa`
  - Password: `password`
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Documentation**: http://localhost:8080/api-docs

## Authentication

The application uses in-memory users with role-based access:

### Admin User (Full Access)
- **Username**: `admin@example.com`
- **Password**: `password`
- **Role**: `ROLE_ADMIN`
- **Permissions**: All CRUD operations (GET, POST, PUT, DELETE)

### Regular User (Read-Only Access)
- **Username**: `user@example.com`
- **Password**: `password`
- **Role**: `ROLE_USER`
- **Permissions**: GET operations only

## API Endpoints

### Categories
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `POST /api/categories` - Create new category (Admin only)
- `PUT /api/categories/{id}` - Update category (Admin only)
- `DELETE /api/categories/{id}` - Delete category (Admin only)

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/filter` - Filter products by category, brand, price range
- `GET /api/products/category/{categoryName}` - Get products by category
- `GET /api/products/brand/{brand}` - Get products by brand
- `POST /api/products` - Create new product with image upload (Admin only)
- `PUT /api/products/{id}` - Update product (Admin only)
- `DELETE /api/products/{id}` - Delete product (Admin only)

### Filter Parameters
- `category` - Filter by category name
- `brand` - Filter by brand name
- `minPrice` - Minimum price filter
- `maxPrice` - Maximum price filter

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── shopserve/
│   │           ├── ShopserveApplication.java
│   │           ├── config/
│   │           │   ├── SecurityConfig.java
│   │           │   ├── OpenApiConfig.java
│   │           │   ├── WebConfig.java
│   │           │   └── DataInitializer.java
│   │           ├── controller/
│   │           │   ├── HomeController.java
│   │           │   ├── CategoryController.java
│   │           │   └── ProductController.java
│   │           ├── service/
│   │           │   ├── CategoryService.java
│   │           │   └── ProductService.java
│   │           ├── repository/
│   │           │   ├── CategoryRepository.java
│   │           │   └── ProductRepository.java
│   │           ├── entity/
│   │           │   ├── Category.java
│   │           │   └── Product.java
│   │           ├── dto/
│   │           │   ├── CategoryRequest.java
│   │           │   ├── CategoryResponse.java
│   │           │   ├── ProductRequest.java
│   │           │   └── ProductResponse.java
│   │           └── exception/
│   │               ├── ResourceNotFoundException.java
│   │               ├── ValidationException.java
│   │               └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── shopserve/
                └── ShopserveApplicationTests.java
```

## Configuration

The application is configured via `application.properties`:

- **Server Port**: 8080
- **Database**: H2 in-memory database
- **JPA**: Hibernate with automatic schema generation
- **Security**: Role-based authentication with ADMIN/USER roles
- **Swagger**: Available at `/swagger-ui.html`
- **File Upload**: Max 10MB file size
- **Image Storage**: Local `/uploads/` directory

## Sample Data

The application automatically initializes with sample data:

### Categories
- Electronics
- Clothing
- Books
- Home & Garden

### Products
- MacBook Pro (Electronics)
- iPhone 15 (Electronics)
- Cotton T-Shirt (Clothing)
- Slim Fit Jeans (Clothing)
- The Great Gatsby (Books)
- Garden Shovel (Home & Garden)

## Development

### Hot Reload

The application includes Spring Boot DevTools for automatic restart during development. Simply save your files and the application will restart automatically.

### Database

The H2 console is enabled and accessible at `/h2-console`. This is useful for debugging and viewing the database contents during development.

### Image Uploads

Product images are stored in the `uploads/` directory and served via `/uploads/{filename}`. The system generates unique filenames to prevent conflicts.

## API Documentation

The application uses SpringDoc OpenAPI to automatically generate API documentation. Access the Swagger UI at `/swagger-ui.html` to explore and test the available endpoints.

## Security

Spring Security is configured with:
- Role-based access control (ADMIN/USER)
- Basic authentication
- Method-level security with `@PreAuthorize`
- CSRF protection (disabled for H2 console)
- Frame options configured for H2 console access

## Error Handling

The application includes comprehensive error handling:
- `ResourceNotFoundException` - 404 Not Found
- `ValidationException` - 400 Bad Request
- `AccessDeniedException` - 403 Forbidden
- `MethodArgumentNotValidException` - 400 Bad Request (validation errors)
- Generic exception handling - 500 Internal Server Error

All errors return structured JSON responses with status, message, details, and timestamp.

## Testing

Run the tests with:

```bash
mvn test
```

## Building for Production

To create a production-ready JAR file:

```bash
mvn clean package
```

The JAR file will be created in the `target/` directory.

## Troubleshooting

1. **Port 8080 already in use**: Change the port in `application.properties`
2. **Java version issues**: Ensure you're using Java 17 or higher
3. **Maven issues**: Try `mvn clean` and then `mvn install`
4. **Authentication issues**: Use the provided credentials (admin@example.com/password or user@example.com/password)
5. **File upload issues**: Ensure the `uploads/` directory has write permissions

## Next Steps

This is a comprehensive e-commerce application setup. You can extend it by:

1. Adding more entities (Orders, Users, Reviews)
2. Implementing payment processing
3. Adding search functionality
4. Implementing caching (Redis)
5. Adding email notifications
6. Implementing user registration and authentication
7. Adding unit and integration tests
8. Implementing database migrations
9. Adding monitoring and logging
10. Implementing API rate limiting #   S h o p S e r v e - E - C o m m e r c e - P r o d u c t - C a t a l o g - A P I  
 