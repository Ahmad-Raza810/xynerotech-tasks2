
# Project Title

A brief description of what this project does and who it's for

# 🛠 Household Services Booking Platform

This is a robust and secure Spring Boot-based backend application that allows users to register, log in, view household services, and book them. The application is secured using JWT-based authentication and supports role-based access for **USER** and **ADMIN**.

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

| Endpoint | Method | Access | Description |
|------------------------------------|--------|-----------------|------------------------------------------------|
| `/api/health` | GET | Public | Check server health |
| `/api/register` | POST | Public | Register a new user |
| `/api/login` | POST | Public | Authenticate user and return JWT |
| `/api/service/get` | GET | Public | View all available services |
| `/api/service/get/{id}` | GET | Public | View a service by ID |
| `/api/user/{userid}/book` | POST | User Only | Book a service |
| `/api/user/{userid}/my-bookings` | GET | User & Admin | Get all bookings for a specific user |
| `/api/bookings` | GET | Admin Only | Get all bookings in the system |
| `/api/service/add` | POST | Admin Only | Create a new service |
| `/api/service/update/{id}` | PUT | Admin Only | Update service by ID |
| `/api/service/delete/{id}` | DELETE | Admin Only | Delete service by ID |
| `/api/user/add` | POST | Admin Only | Create a new user |
| `/api/user/get` | GET | Admin Only | Get all users |
| `/api/user/get/{id}` | GET | Admin Only | Get user by ID |
| `/api/user/update/{id}` | PUT | Admin Only | Update user by ID |
| `/api/user/delete/{id}` | DELETE | Admin Only | Delete user by ID |

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
Response

JSON

{
  "message": "User registered successfully",
  "timestamp": "...",
  "data": {
    "userId": 1,
    "userName": "Ahmad Raza",
    "email": "ahmad123@gmail.com",
    "role": "USER"
  },
  "success": true,
  "statusCode": 201
}
🔐 Login /api/login
Request

JSON

{
  "email": "ahmad123@gmail.com",
  "password": "ahmad1234"
}
Response

JSON

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
🔧 Service API Endpoints (Admin Only)
Add Service /api/service/add
Request

JSON

{
  "name": "Water Purifier Repair",
  "description": "RO/UV purifier repair and installation service for all major brands."
}
Response

JSON

{
  "message": "Service added successfully",
  "timestamp": "...",
  "data": {
    "id": 1,
    "name": "Water Purifier Repair",
    "description": "RO/UV purifier repair and installation service for all major brands."
   },
  "success": true,
  "statusCode": 201
}
Update Service /api/service/update/{id}
Request

JSON

{
  "description": "Updated description for the water purifier service."
}
Response

JSON

{
  "message": "Service updated successfully",
  "timestamp": "...",
  "data": { ... },
  "success": true,
  "statusCode": 200
}
Delete Service /api/service/delete/{id}
Response

JSON

{
  "message": "Service deleted successfully",
  "timestamp": "...",
  "data": null,
  "success": true,
  "statusCode": 200
}
📅 Booking API Endpoints
Book a Service (User Role) /api/user/{userid}/book
Request

JSON

{
    "serviceId": 1,
    "date": "28-08-2025",
    "time": "14:30",
    "address": "123, ABC Colony, New Delhi, India"
}
Response

JSON

{
    "message": "Booking  confirmed..",
    "timestamp": "2025-08-11T12:30:00.123456",
    "data": {
        "bookingId": 1,
        "serviceName": "Water Purifier Repair",
        "bookingDate": "28-08-2025",
        "time": "14:30",
        "address": "123, ABC Colony, New Delhi, India"
    },
    "success": true,
    "statusCode": 201
}
Get My Bookings (User/Admin Role) /api/user/{userid}/my-bookings
Response

JSON

{
    "message": "Booking fetched successfully.",
    "timestamp": "2025-08-11T12:35:00.123456",
    "data": [
        {
            "bookingId": 1,
            "serviceName": "Water Purifier Repair",
            "bookingDate": "28-08-2025",
            "time": "14:30",
            "address": "123, ABC Colony, New Delhi, India"
        }
    ],
    "success": true,
    "statusCode": 200
}
Get All Bookings (Admin Role) /api/bookings
Response

JSON

{
    "message": "All bookings fetched successfully",
    "timestamp": "2025-08-11T12:40:00.123456",
    "data": [ ... ],
    "success": true,
    "statusCode": 200
}
👥 User API Endpoints (Admin Only)
Add User /api/user/add
Request

JSON

{
  "userName":"Ahmad Raza",
  "email":"ahmad123@gmail.com",
  "password":"ahmad1234",
  "role": "USER"
}
Response

JSON

{
  "message": "User added successfully.",
  "timestamp": "...",
  "data": { ... },
  "success": true,
  "statusCode": 201
}
(For other User endpoints like Get, Update, Delete, the structure is similar to the old README)

📁 Folder Structure
src/main/java/com/xynerotech/task/household_services_booking_platform
├── config
├── controller
├── dto
├── entities
├── exception
├── repository
├── response
├── security
└── service
✅ Validation Rules
User Registration:

Valid email format required.

Password must be at least 6 characters long.

Service:

Service name must be between 3 and 50 characters.

Description must be between 10 and 255 characters.

Booking:

Address must be between 5 and 255 characters.

Time is a required field.

⚠️ Error Handling
Generic structure handled globally:

JSON

{
  "message": "Something went wrong. Please try again.",
  "timestamp": "...",
  "statusCode": 500
}
▶️ How to Run Locally
Clone the repository.

Configure DB in application.properties.

Run the Spring Boot app.

Use Postman to test the endpoints.

👤 Developed By
Ahmad Raza Backend Intern @ Xynerotech ✔️

📧 mustafaraza03898@gmail.com

📞 +91-8107951977

🔗 GitHub