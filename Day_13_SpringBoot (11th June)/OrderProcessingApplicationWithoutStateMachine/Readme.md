# 📦 Order Processing Application (Without State Machine)

A Spring Boot application that simulates an **Order State Machine** without using the Spring State Machine library. It manages orders through controlled state transitions like NEW → PROCESSING → SHIPPED → DELIVERED or CANCELLED.

---

## 🧠 Project Logic

This application models an `Order` entity with the following properties:

* `id`: Unique order ID
* `description`: Order description
* `state`: Current order state
* `history`: List of states the order has passed through

### ⛓️ State Transitions

| Current State | Valid Events        | Next State                |
| ------------- | ------------------- | ------------------------- |
| `NEW`         | `PROCESS`, `CANCEL` | `PROCESSING`, `CANCELLED` |
| `PROCESSING`  | `SHIP`, `CANCEL`    | `SHIPPED`, `CANCELLED`    |
| `SHIPPED`     | `DELIVER`, `CANCEL` | `DELIVERED`, `CANCELLED`  |
| `DELIVERED`   | `CANCEL`            | `CANCELLED`               |
| `CANCELLED`   | —                   | — (Terminal state)        |

If an invalid event is triggered, the app throws a custom `InvalidStateException`.

---

## 🗂️ Project Structure

```
src/
├── controller/
│   └── OrderController.java
├── dto/
│   └── OrderRequest.java
├── entity/
│   └── Order.java
├── enums/
│   ├── OrderEvent.java
│   └── OrderState.java
├── exception/
│   └── InvalidStateException.java
├── service/
│   └── OrderService.java
└── OrderProcessingApplication.java
```

---

## 🧰 Tech Stack

* Java 17
* Spring Boot 3.x
* Gradle
* RESTful APIs
* In-memory storage (No DB)

---

## ▶️ How to Run

1. Clone the repository:

```bash
git clone https://github.com/your-repo/order-processing-app.git
cd order-processing-app
```

2. Build the project:

```bash
./gradlew build
```

3. Run the application:

```bash
./gradlew bootRun
```

4. Visit: [http://localhost:8080/orders](http://localhost:8080/orders)

---

## 📌 API Endpoints

### ✅ 1. Create a New Order

**URL:** `POST /orders`

**Request Body:**

```json
{
  "description": "New Order for electronics"
}
```

**Response:**

```json
{
  "id": 1,
  "description": "New Order for electronics",
  "state": "NEW",
  "history": ["NEW"]
}
```

### ✅ 2. Get Order by ID

**URL:** `GET /orders/{id}`

**Example:** `GET /orders/1`

**Response:**

```json
{
  "id": 1,
  "description": "New Order for electronics",
  "state": "NEW",
  "history": ["NEW"]
}
```

### ✅ 3. Get All Orders

**URL:** `GET /orders`

**Response:**

```json
[
  {
    "id": 1,
    "description": "New Order for electronics",
    "state": "NEW",
    "history": ["NEW"]
  },
  {
    "id": 2,
    "description": "Office chair",
    "state": "PROCESSING",
    "history": ["NEW", "PROCESSING"]
  }
]
```

### ✅ 4. Trigger Event (Change Order State)

**URL:** `POST /orders/{id}/event/{event}`

**Example:** `POST /orders/1/event/PROCESS`

**Response:**

```json
{
  "id": 1,
  "description": "New Order for electronics",
  "state": "PROCESSING",
  "history": ["NEW", "PROCESSING"]
}
```

**Invalid Transition Example:** `POST /orders/1/event/DELIVER`

**Response:**

```json
{
  "timestamp": "2025-06-14T23:42:00.001+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Event 'DELIVER' not allowed in state 'PROCESSING'",
  "path": "/orders/1/event/DELIVER"
}
```

### ✅ 5. Get Current State of an Order

**URL:** `GET /orders/{id}/state`

**Example:** `GET /orders/1/state`

**Response:**

```json
"PROCESSING"
```

### ✅ 6. Get Allowed Events (From Current State)

**URL:** `GET /orders/{id}/allowed-events`

**Example:** `GET /orders/1/allowed-events`

**Response:**

```json
["SHIP", "CANCEL"]
```

### ✅ 7. Get Transition History

**URL:** `GET /orders/{id}/history`

**Example:** `GET /orders/1/history`

**Response:**

```json
["NEW", "PROCESSING"]
```

---

## 📊 System Architecture Diagram

```
+-----------------------------+
|     OrderController         |
+-------------+---------------+
              |
              v
+-----------------------------+
|        OrderService         |
+-------------+---------------+
              |
+-------------+---------------+
| Manual State Transition Map |
+-------------+---------------+
              |
              v
+------------------------+
|       Order Entity     |
|------------------------|
| id, description, state |
| history (List<String>) |
+------------------------+
```

---

## 👨‍💻 Author

**Anshu** 
