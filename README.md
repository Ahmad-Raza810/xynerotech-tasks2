
# 🛠 Household Services Booking Platform

This is a robust and secure Spring Boot-based backend application that allows users to register, log in, view household services, and (in future updates) book services. The application is secured using JWT-based authentication and supports role-based access for **User** and **Admin**.

## 📦 Tech Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **JWT (JSON Web Token)**
- **JPA (Hibernate)**
- **MySQL**
- **Lombok**
- **Postman** (for API testing)

---

## 🔐 Role-Based Access Control (RBAC)

| Endpoint                     | Method | Access        | Description                              |
|-----------------------------|--------|---------------|------------------------------------------|
| `/api/health`               | GET    | Public        | Check server health                      |
| `/api/register`             | POST   | Public        | Register a new user                      |
| `/api/login`                | POST   | Public        | Authenticate user and return JWT         |
| `/api/service/get`          | GET    | Public        | View all available services              |
| `/api/service/get/{id}`     | GET    | Public        | View a service by ID                     |
| `/api/services`             | POST   | Admin Only    | Create a new service                     |
| `/api/services/{id}`        | PUT    | Admin Only    | Update service by ID                     |
| `/api/services/{id}`        | DELETE | Admin Only    | Delete service by ID                     |
| `/api/user/add`             | POST   | Admin Only    | Create a new user                        |
| `/api/user/get`             | GET    | Admin Only    | Get all users                            |
| `/api/user/get/{id}`        | GET    | Admin Only    | Get user by ID                           |
| `/api/user/update/{id}`     | PUT    | Admin Only    | Update user by ID                        |
| `/api/user/delete/{id}`     | DELETE | Admin Only    | Delete user by ID                        |

---

## 📬 API Request/Response Samples

### ✅ Register `/api/register`
**Request**
```json
{
  "userName": "Ahmad Raza",
  "email": "ahmad123@gmail.com",
  "password": "ahmad1234"
}
```
**Response**
```json
{
  "message": "User registered successfully",
  "timestamp": "...",
  "data": {
    "userId": 6,
    "userName": "Ahmad Raza",
    "email": "ahmad123@gmail.com",
    "role": "USER"
  },
  "success": true,
  "statusCode": 201
}
```

### 🔐 Login `/api/login`
**Request**
```json
{
  "email": "ahmad123@gmail.com",
  "password": "ahmad1234"
}
```
**Response**
```json
{
  "message": "Login successful",
  "timestamp": "...",
  "data": {
    "token": "JWT_TOKEN",
    "email": "ahmad123@gmail.com",
    "role": "USER"
  },
  "success": true,
  "statusCode": 200
}
```

---

## 👥 User API Endpoints

### Add User `/api/user/add`
**Request**
```json
{
  "userName":"Ahmad Raza",
  "email":"ahmad123@gmail.com",
  "password":"ahmad1234"
}
```
**Response**
```json
{
  "message": "User added successfully.",
  "timestamp": "...",
  "data": {
    "userId": 6,
    "userName": "Ahmad Raza",
    "email": "ahmad123@gmail.com",
    "role": "USER"
  },
  "success": true,
  "statusCode": 201
}
```

### Get All Users `/api/user/get`
**Response**
```json
[
  {
    "userId": 1,
    "userName": "Admin",
    "email": "admin@xynerotech.com",
    "role": "ADMIN"
  },
  {
    "userId": 6,
    "userName": "Ahmad Raza",
    "email": "ahmad123@gmail.com",
    "role": "USER"
  }
]
```

### Get User by ID `/api/user/get/{id}`
**Response**
```json
{
  "message": "User fetched successfully",
  "timestamp": "...",
  "data": {
    "userId": 6,
    "userName": "Ahmad Raza",
    "email": "ahmad123@gmail.com",
    "role": "USER"
  },
  "success": true,
  "statusCode": 200
}
```

### Update User `/api/user/update/{id}`
**Request**
```json
{
  "email": "ahmad1234@gmail.com"
}
```
**Response**
```json
{
  "message": "user successfully updated.",
  "timestamp": "...",
  "data": {
    "userId": 6,
    "userName": "Ahmad Raza",
    "email": "ahmad1234@gmail.com",
    "role": "USER"
  },
  "success": true,
  "statusCode": 200
}
```

### Delete User `/api/user/delete/{id}`
**Response**
```json
{
  "message": "user deleted successfully.",
  "timestamp": "...",
  "data": null,
  "success": true,
  "statusCode": 200
}
```

---

## 🔧 Service API Endpoints

### Get All Services `/api/service/get`
**Response**
```json
{
  "message": "Services fetched successfully",
  "timestamp": "...",
  "data": [ ... ],
  "success": true,
  "statusCode": 200
}
```

### Get Service by ID `/api/service/get/{id}`
**Response**
```json
{
  "message": "Service found",
  "timestamp": "...",
  "data": { ... },
  "success": true,
  "statusCode": 200
}
```

### Create Service `/api/services`
**Request**
```json
{
  "name": "Water Purifier Repair",
  "description": "RO/UV purifier repair and installation",
  "price": 349.0
}
```
**Response**
```json
{
  "message": "Service added successfully",
  "timestamp": "...",
  "data": { ... },
  "success": true,
  "statusCode": 201
}
```

### Update Service `/api/services/{id}`
**Request**
```json
{
  "price": 499.0
}
```
**Response**
```json
{
  "message": "Service updated successfully",
  "timestamp": "...",
  "data": { ... },
  "success": true,
  "statusCode": 200
}
```

### Delete Service `/api/services/{id}`
**Response**
```json
{
  "message": "Service deleted successfully",
  "timestamp": "...",
  "data": null,
  "success": true,
  "statusCode": 200
}
```

---

## 📁 Folder Structure

```
src/main/java/com/xynerotech/task/household_services_booking_platform
├── controller
├── dto
├── entities
├── exception
├── repository
├── response
├── security
├── service
└── config
```

---

## ✅ Validation Rules

- **User Registration**:
  - Valid email format required.
  - Password minimum 6 characters.
- **Service**:
  - Name length: 3–50 chars.
  - Description: 10–255 chars.
  - Price must be positive.

---

## ⚠️ Error Handling

Generic structure handled globally:
```json
{
  "message": "Something went wrong. Please try again.",
  "timestamp": "...",
  "statusCode": 500
}
```

---

## ▶️ How to Run Locally

1. Clone the repository.
2. Configure DB in `application.properties`.
3. Run the Spring Boot app.
4. Use Postman to test the endpoints.

---

## 👤 Developed By

**Ahmad Raza**  
Backend Intern @ Xynerotech ✔️  
📧 mustafaraza03898@gmail.com  
📞 +91-8107951977  
🔗 [GitHub](https://github.com/Ahmad-Raza810)
