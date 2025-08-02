
# 🏠 Household Services Booking Platform

This project is a backend REST API for a **Household Services Booking Platform** built using **Spring Boot**. It allows users to register, log in, and browse household services. Admins can manage services and users. The booking module and relationships will be implemented in the next phase.

---

## 🚀 Features

- User Registration and Login with JWT authentication
- Role-based access control (Admin/User)
- Service Management (Add/Update/Delete by Admin)
- Secure Password Storage (BCrypt)
- DTO-based request/response structure
- Centralized Exception Handling
- Swagger API Documentation (Optional)

---

## 🧑‍💻 Tech Stack

- Java 17
- Spring Boot 3.5.4
- Spring Security with JWT
- Spring Data JPA
- MySQL
- Maven
- Lombok

---

## 👥 Roles and Access

| Endpoint                  | Method | Access        | Description                        |
|--------------------------|--------|---------------|------------------------------------|
| `/api/health`            | GET    | Public        | Server status check                |
| `/api/register`          | POST   | Public        | Register new user                  |
| `/api/login`             | POST   | Public        | Login user and get JWT token       |
| `/api/service/get`       | GET    | Public        | Get all services                   |
| `/api/service/get/{id}`  | GET    | Public        | Get service by ID                  |
| `/api/services`          | POST   | Admin Only    | Create new service                 |
| `/api/services/{id}`     | PUT    | Admin Only    | Update service by ID               |
| `/api/services/{id}`     | DELETE | Admin Only    | Delete service by ID               |
| `/api/users`             | GET    | Admin Only    | Get all users                      |
| `/api/users/{id}`        | DELETE | Admin Only    | Delete user by ID                  |

---

## 🔐 Authentication

- JWT is used for secure authentication.
- Register/Login routes are public.
- Protected routes require `Authorization: Bearer <token>` header.

---

## 🧪 Sample Request/Response

### ✅ Register
**POST** `/api/register`

```json
{
  "userName": "satish",
  "email": "satish@example.com",
  "password": "rrrrrr"
}
```

**Response**
```json
{
  "message": "User registered successfully",
  "success": true,
  "statusCode": 201
}
```

---

### ✅ Login
**POST** `/api/login`

```json
{
  "email": "satish@example.com",
  "password": "rrrrrr"
}
```

**Response**
```json
{
  "token": "JWT_TOKEN",
  "email": "satish@example.com",
  "role": "USER"
}
```

---

### ✅ Get All Services
**GET** `/api/service/get`

```json
[
  {
    "id": 1,
    "name": "Electrician",
    "description": "Fixing wires and appliances",
    "price": 250.0
  }
]
```

---

## ⚙️ Default Admin Credentials

- **Email:** admin@example.com
- **Password:** admin123

> These are automatically inserted on app startup if not present.

---

## 📌 Notes

- Booking module and service-user relationship is planned for Phase 2.
- DTOs are used to decouple internal entities from API requests/responses.
- Add JWT token as Authorization header to test protected routes.

---

## 🧑 Author

Developed by Ahmad Raza
