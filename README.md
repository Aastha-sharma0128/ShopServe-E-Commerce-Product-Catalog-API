# 🛒 ShopServe: E-Commerce Product Catalog API

ShopServe is a RESTful backend API for managing an e-commerce product catalog, built with **Spring Boot** and **Spring Data JPA**.

It supports complete **CRUD operations** for products and categories, powerful **filtering** capabilities, **image uploads**, and **role-based access control** to differentiate between admin and user functionalities. The API is documented using **Swagger/OpenAPI** and features robust **exception handling** for a clean developer experience.

---

## 🚀 Features

- ✅ **CRUD operations** for products and categories  
- 🔍 **Product filtering** by price range, brand, and category  
- 🖼️ **Image upload** support using multipart/form-data  
- 🔐 **Role-based access control**  
  - `ADMIN`: full access (create, update, delete)
  - `USER`: read-only access (view/filter products)  
- 📘 **API documentation** using Swagger UI  
- ❗ **Global exception handling** with meaningful error messages  

---

## 🧰 Technologies Used

- Java 17  
- Spring Boot  
- Spring Web  
- Spring Security  
- Spring Data JPA  
- H2 In-Memory Database  
- Spring Validation  
- Swagger/OpenAPI (springdoc-openapi)  

---

## 📁 Project Structure

shopserve/
├── controller/ # REST controllers for Product & Category
├── dto/ # Request/Response DTOs
├── model/ # JPA entities (Product, Category)
├── repository/ # JPA repositories
├── service/ # Business logic (optional but recommended)
├── exception/ # Custom exceptions & handler
├── config/ # Spring Security configuration
└── resources/
├── application.yml


