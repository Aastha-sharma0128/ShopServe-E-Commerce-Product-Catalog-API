# Secure API - Spring Boot with JWT Authentication

A secure REST API built with Spring Boot 3.2.0, Spring Security, and JWT (JSON Web Tokens) for authentication.

## Features

- **Spring Boot 3.2.0** with Java 17
- **Spring Security** for authentication and authorization
- **Spring Data JPA** for database operations
- **H2 Database** (in-memory) for development
- **JWT (JSON Web Tokens)** for stateless authentication
- **BCrypt** password encoding
- **RESTful API** endpoints

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Project Structure

```
secureapi/
├── src/
│   ├── main/
│   │   ├── java/com/example/secureapi/
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── DataInitializer.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   └── TestController.java
│   │   │   ├── dto/
│   │   │   │   ├── AuthRequest.java
│   │   │   │   └── AuthResponse.java
│   │   │   ├── filter/
│   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   ├── model/
│   │   │   │   └── User.java
│   │   │   ├── repository/
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/
│   │   │   │   └── UserService.java
│   │   │   ├── util/
│   │   │   │   └── JwtUtil.java
│   │   │   └── SecureApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Getting Started

### 1. Build the Project

```bash
mvn clean install
```

### 2. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 3. Access H2 Database Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## API Endpoints

### Authentication Endpoints

#### 1. Register a New User
```http
POST /auth/register
Content-Type: application/json

{
    "username": "newuser",
    "password": "password123"
}
```

#### 2. Login
```http
POST /auth/login
Content-Type: application/json

{
    "username": "admin",
    "password": "admin123"
}
```

Response:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Protected Endpoints

#### 1. User Endpoint (Authentication Required)
```http
GET /user
Authorization: Bearer <your-jwt-token>
```

#### 2. Admin Endpoint (ADMIN Role Required)
```http
GET /admin
Authorization: Bearer <your-jwt-token>
```

## Testing the API

### Using curl

1. **Register a new user:**
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

2. **Login to get JWT token:**
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

3. **Access user endpoint:**
```bash
curl -X GET http://localhost:8080/user \
  -H "Authorization: Bearer <your-jwt-token>"
```

4. **Access admin endpoint:**
```bash
curl -X GET http://localhost:8080/admin \
  -H "Authorization: Bearer <your-jwt-token>"
```

### Using Postman

1. Import the following collection or create requests manually
2. Set the base URL to `http://localhost:8080`
3. For protected endpoints, add the JWT token in the Authorization header as `Bearer <token>`

## Default Admin User

The application automatically creates a default admin user on startup:
- **Username:** `admin`
- **Password:** `admin123`
- **Role:** `ADMIN`

## Configuration

Key configuration properties in `application.properties`:

- **JWT Secret:** `jwt.secret` - Change this in production
- **JWT Expiration:** `jwt.expiration` - Token expiration time in milliseconds
- **Database:** H2 in-memory database for development
- **Server Port:** 8080

## Security Features

- **JWT-based Authentication:** Stateless authentication using JSON Web Tokens
- **Password Encryption:** BCrypt password hashing
- **CORS Support:** Cross-origin resource sharing enabled
- **CSRF Protection:** Disabled for API endpoints (stateless)
- **Session Management:** Stateless sessions

## Production Considerations

1. **Change JWT Secret:** Update the `jwt.secret` property with a strong, unique secret
2. **Database:** Replace H2 with a production database (PostgreSQL, MySQL, etc.)
3. **HTTPS:** Enable HTTPS in production
4. **CORS:** Configure CORS properly for your frontend domain
5. **Logging:** Configure appropriate logging levels
6. **Monitoring:** Add health checks and monitoring

## Dependencies

- **Spring Boot Starter Web:** REST API support
- **Spring Boot Starter Security:** Security framework
- **Spring Boot Starter Data JPA:** Database operations
- **H2 Database:** In-memory database
- **JJWT:** JSON Web Token library
- **Spring Boot Starter Test:** Testing support

## License

This project is for educational purposes. Feel free to use and modify as needed. 