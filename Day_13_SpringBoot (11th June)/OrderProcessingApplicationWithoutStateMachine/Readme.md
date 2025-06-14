# Order Processing Application (Without State Machine)

A Spring Boot application that simulates an **Order State Machine** without using the Spring State Machine library. It manages orders through controlled state transitions like NEW → PROCESSING → SHIPPED → DELIVERED or CANCELLED.

---

## System Architecture Diagram

![diagram-export-6-15-2025-2_32_42-AM](https://github.com/user-attachments/assets/80a40243-acf9-421f-9ccb-adef827b1d48)


## Project Logic

This application models an `Order` entity with the following properties:

* `id`: Unique order ID
* `description`: Order description
* `state`: Current order state
* `history`: List of states the order has passed through

### State Transitions

| Current State | Valid Events        | Next State                |
| ------------- | ------------------- | ------------------------- |
| `NEW`         | `PROCESS`, `CANCEL` | `PROCESSING`, `CANCELLED` |
| `PROCESSING`  | `SHIP`, `CANCEL`    | `SHIPPED`, `CANCELLED`    |
| `SHIPPED`     | `DELIVER`, `CANCEL` | `DELIVERED`, `CANCELLED`  |
| `DELIVERED`   | `CANCEL`            | `CANCELLED`               |
| `CANCELLED`   | —                   | — (Terminal state)        |

If an invalid event is triggered, the app throws a custom `InvalidStateException`.

---

## Project Structure

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

## How to Run

1. Clone the repository:

```bash
git clone <url>
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

## API Endpoints

### 1. Create a New Order

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

![Screenshot 2025-06-15 021535](https://github.com/user-attachments/assets/4f641513-1818-4a74-97a6-3b91d56a8700)



### 2. Get Order by ID

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

![Screenshot 2025-06-15 021609](https://github.com/user-attachments/assets/5947b4c5-a2c0-4ddb-badd-3c3e8d7aceb5)


### 3. Get All Orders

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

![Screenshot 2025-06-15 021705](https://github.com/user-attachments/assets/2478a6b4-39fd-4740-b49f-7f3dbd55cd28)


### 4. Trigger Event (Change Order State)

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

![Screenshot 2025-06-15 021809](https://github.com/user-attachments/assets/c67c2ef3-3f89-43ef-8547-7e3a53136bc8)


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

### 5. Get Current State of an Order

**URL:** `GET /orders/{id}/state`

**Example:** `GET /orders/1/state`

**Response:**

```json
"PROCESSING"
```

![Screenshot 2025-06-15 021826](https://github.com/user-attachments/assets/f2efb3b7-6b37-4125-a457-e19a12ed29d3)


### 6. Get Allowed Events (From Current State)

**URL:** `GET /orders/{id}/allowed-events`

**Example:** `GET /orders/1/allowed-events`

**Response:**

```json
["SHIP", "CANCEL"]
```

![Screenshot 2025-06-15 021843](https://github.com/user-attachments/assets/69b71bbb-c4ed-449c-80f6-092addfd2ba3)


### 7. Get Transition History

**URL:** `GET /orders/{id}/history`

**Example:** `GET /orders/1/history`

**Response:**

```json
["NEW", "PROCESSING"]
```

![Screenshot 2025-06-15 021857](https://github.com/user-attachments/assets/ff1f8f40-9807-4531-a859-d6b28e562351)


---