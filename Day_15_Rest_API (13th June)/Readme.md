
```markdown
# 🛠️ UrbanConnect - Service Provider & Customer Management System

UrbanConnect is a RESTful web application built using Java Spring Boot that connects service providers (e.g., electricians, plumbers) with customers who need on-demand home services — similar to Urban Company.

---

## 📌 Features

### 🔐 Authentication
- Separate registration for `CUSTOMER` and `PROVIDER`
- JWT-based login & secured endpoints
- Role-based access control

### 📄 Service Requests
- Customers post service requests (plumbing, electrical, etc.)
- Providers notified based on availability and location
- Real-time request filtering and acceptance

### 📅 Calendar Availability
- Providers manage availability slots
- Accepted request auto-blocks time slot
- No duplicate notifications once accepted

### 🪙 Wallet & Transactions
- Customers can add funds to their wallet
- Payments made through wallet post-service
- Handling fee (10–15%) deducted based on service type
- Providers can withdraw balance

### 📬 Notifications
- Real-time alerts to relevant providers
- Viewable through `/api/notification/user/{id}`

### 📝 Reviews & Ratings
- Post-service reviews by customers/providers
- Ratings tracked per user

### 📊 Admin Reports & Audit Logs
- API usage logging
- Audit log for each sensitive action (e.g., transactions)
- Metrics: Active users, service requests, financial reports

---

## 🧱 Tech Stack

| Layer             | Tech                                     |
|------------------|------------------------------------------|
| Backend           | Java 21, Spring Boot                     |
| Auth              | Spring Security, JWT                     |
| Database          | PostgreSQL, Hibernate (JPA)              |
| API Docs          | Swagger (springdoc-openapi)              |
| Logging           | Logback, Audit Log Table                 |
| Testing           | JUnit, Mockito                           |
| Build Tool        | Gradle                                   |

---

## 📁 Project Structure

```

src/
├── main/
│   ├── java/com.example.service\_provider\_management/
│   │   ├── auth/             # JWT, UserDetailsService, AuthService
│   │   ├── controller/       # REST API Controllers
│   │   ├── service/          # Business logic
│   │   ├── repository/       # Spring Data Repositories
│   │   ├── model/            # Entity definitions
│   │   ├── config/           # Security, Swagger config
│   │   ├── wallet/           # Wallet handling logic
│   │   ├── review/           # Review submission & fetch
│   │   ├── audit/            # Audit logging service
│   │   └── notification/     # Notification logic
│   └── resources/
│       ├── application.yml
│       ├── logback-spring.xml
│       └── static/
│           └── index.html (optional)

````

---

## 🔌 API Endpoints

> All secured endpoints require a `Bearer {{JWT_TOKEN}}` header

### Auth
- `POST /api/auth/register` – Register as customer or provider
- `POST /api/auth/login` – Login & receive JWT token

### Service Request
- `POST /api/request` – Customer creates request
- `GET /api/request/{id}` – View service request details

### Availability
- `POST /api/availability` – Provider adds availability

### Wallet
- `POST /api/wallet/add` – Add money to wallet
- `POST /api/wallet/transfer` – Make payment

### Notifications
- `GET /api/notification/user/{id}` – View user notifications

### Reviews
- `POST /api/review` – Submit a review
- `GET /api/review/user/{id}` – Fetch user reviews

---

## 🧪 Postman Collection

You can [download the full Postman Collection here](./urbanconnect-postman-collection.json) to test all endpoints.

---

## 🖥️ Running Locally

### Prerequisites
- Java 21+
- PostgreSQL
- Gradle

### Setup

1. Clone the repo
```bash
git clone https://github.com/your-repo/urbanconnect.git
cd urbanconnect
````

2. Update `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/urbanconnect
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

3. Run the app

```bash
./gradlew bootRun
```

4. Swagger API UI:
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📸 Architecture Diagram

> Refer to the `docs/architecture.png` or check the [attached architecture image](./A_detailed_software_architecture_diagram.png) for a complete visual.

---

## 👨‍💻 Contributors

* **Anshu** – Full Stack Developer

---

## 📜 License

This project is open-source and available under the [MIT License](LICENSE).

```

---

Let me know if you want this exported as a `.md` or `.pdf` file, or if you’d like to generate one dynamically in your GitHub README with live Swagger docs included.
```
