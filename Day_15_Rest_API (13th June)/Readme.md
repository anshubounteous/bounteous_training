
# UrbanConnect - Service Provider & Customer Management System

UrbanConnect is a RESTful web application built using **Java Spring Boot**, providing a platform to connect **service providers** (like electricians, plumbers) with **customers** for on-demand doorstep services — similar to Urban Company.

---

## System Architecture

full architecture diagram:
![diagram-export-6-22-2025-10_55_41-PM](https://github.com/user-attachments/assets/b69a0191-491e-46c2-bf16-89b1dbc061ee)

---
---
high level diagram:
![ChatGPT Image Jun 22, 2025, 11_50_07 PM](https://github.com/user-attachments/assets/fa4e2847-9cba-4a82-ba9f-b48b129c3b1a)



## Application Flow

### \[1] Registration/Login

* POST `/api/auth/register`
* POST `/api/auth/login` → JWT Token

### \[2] Post Service Request (Customer)

* POST `/api/request`
* Providers notified
* One accepts → Others blocked

### \[3] Wallet Flow

* POST `/api/wallet/add`
* POST `/api/wallet/transfer` (after service complete)

### \[4] Review

* POST `/api/review`
* GET `/api/review/user/{id}`

### \[5] Notifications

* GET `/api/notification/user/{id}`

---

## Role-Based API Access

| Endpoint                      | CUSTOMER | PROVIDER | ADMIN |
| ----------------------------- | :------: | :------: | :---: |
| `/api/auth/register`          |     yes    |     yes    |   no   |
| `/api/auth/login`             |     yes    |     yes    |   yes   |
| `/api/request`                |     yes    |    auth    |   yes   |
| `/api/availability`           |    auth    |     yes    |   yes   |
| `/api/wallet/**`              |     yes    |     yes    |   yes   |
| `/api/review`                 |     yes    |     yes    |   yes   |
| `/api/notification/user/{id}` |     yes    |     yes    |   yes   |
| `/api/admin/**`               |     no    |     no    |   yes   |

---

## Postman Collection

API endpoints are included in a Postman collection.

![Screenshot 2025-06-23 002358](https://github.com/user-attachments/assets/2aedc871-0930-404e-b707-46afe861de37)

---

![Screenshot 2025-06-23 002415](https://github.com/user-attachments/assets/cd7de48c-d33e-4177-ae24-9a2e26a2c7e2)

**Authorization:**

```
Key: Authorization
Value: Bearer <your-jwt-token>
```

---

## Setup Instructions

### 1. Prerequisites

* Java 21+
* PostgreSQL installed and running
* Gradle installed (or use the Gradle wrapper)

### 2. Configure Database

In `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/urbanDB
    username: your_db_username
    password: your_db_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### 3. Run the App

```bash
./gradlew bootRun
```

---

## Features Overview

### Authentication & Roles

* Separate secure registration for:

  * `CUSTOMER`
  * `PROVIDER`
* JWT-based login & token-based authorization
* Role-based route protection using Spring Security

### Service Requests

* Customers post service requests specifying category, location, date
* System auto-notifies relevant providers
* Once a provider accepts, others are disabled from responding

### Availability Management

* Providers set availability calendar
* Time slots auto-block when services are accepted
* Toggle service availability by category or date

### Wallet & Payments

* Wallet system for both customers and providers
* Customers can add balance & pay for services
* Service fee deduction: 10–15% based on category
* Providers can withdraw funds to external account

### Notifications

* Providers get real-time alerts for new matching service requests
* Notifications tied to availability and location

### Reviews & Ratings

* Customers & providers can review each other post service
* 5-star rating system with feedback
* Used for quality tracking

### Audit Logs & Reports

* Full audit logging for:

  * Transactions
  * Sensitive user operations
* Admin reporting: Active users, service requests, earnings, and metrics

---

## Tech Stack

| Category        | Technology                       |
| --------------- | -------------------------------- |
| Backend         | Java 21, Spring Boot             |
| Auth & Security | Spring Security, JWT             |
| Database        | PostgreSQL, Hibernate JPA        |
| Logging         | Logback, Custom Audit Tables     |
| API Docs        | Swagger UI via springdoc-openapi |
| Build Tool      | Gradle                           |
| Testing         | JUnit, Mockito                   |
| API Testing     | Postman (Collection included)    |


---


## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── service_provider_management/
│   │               ├── UrbanConnectApplication.java
│   │               ├── auth/
│   │               │   ├── AuthService.java
│   │               │   ├── JwtFilter.java
│   │               │   └── JwtUtil.java
│   │               ├── audit/
│   │               │   └── AuditService.java
│   │               ├── calendar/
│   │               │   └── AvailabilityService.java
│   │               ├── config/
│   │               │   ├── SecurityConfig.java
│   │               │   └── SwaggerConfig.java
│   │               ├── controller/
│   │               │   ├── AuthController.java
│   │               │   ├── AvailabilityController.java
│   │               │   ├── NotificationController.java
│   │               │   ├── ReviewController.java
│   │               │   ├── ServiceRequestController.java
│   │               │   └── WalletController.java
│   │               ├── model/
│   │               │   ├── AuditLog.java
│   │               │   ├── Availability.java
│   │               │   ├── LoginRequest.java
│   │               │   ├── Notification.java
│   │               │   ├── RegisterRequest.java
│   │               │   ├── RequestStatus.java
│   │               │   ├── Review.java
│   │               │   ├── Role.java
│   │               │   ├── ServiceRequest.java
│   │               │   ├── Transaction.java
│   │               │   ├── User.java
│   │               │   └── Wallet.java
│   │               ├── notification/
│   │               │   └── NotificationService.java
│   │               ├── repository/
│   │               │   ├── AuditLogRepository.java
│   │               │   ├── AvailabilityRepository.java
│   │               │   ├── NotificationRepository.java
│   │               │   ├── ReviewRepository.java
│   │               │   ├── ServiceRequestRepository.java
│   │               │   ├── TransactionRepository.java
│   │               │   ├── UserRepository.java
│   │               │   └── WalletRepository.java
│   │               ├── review/
│   │               │   └── ReviewService.java
│   │               └── service/
│   │                   ├── ServiceRequestService.java
│   │                   └── WalletService.java
│   └── resources/
│       ├── application.yml
│       ├── logback-spring.xml
│       └── static/

```


