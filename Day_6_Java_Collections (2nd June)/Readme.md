# 🛒 Java Product Catalog Console App

This project is a **Java-based console application** for managing a product catalog. It allows users to add, retrieve, update, delete, sort, and filter products. Built using an interface-model-service architecture, it showcases the use of Java Collections, Object-Oriented Programming (OOP), and Stream API.

---

## 📦 Overview

### ✨ Features

* Add new products with unique IDs
* Retrieve all or specific products by ID or name
* Update product details
* Delete products
* Sort products by price, name, or rating
* Filter products by category or price range
* Prevent duplicate products

---

## 🧠 System Design & Logic

### 🔌 Architecture Layers

```
+------------------+
|   Main.java      |  <-- CLI Interaction Layer
+------------------+
        |
        v
+------------------+
| ProductService   |  <-- Business Logic (interface & impl)
+------------------+
        |
        v
+------------------+
|   Product.java   |  <-- Data Model
+------------------+
```

---

## 📁 Project Structure

```
src/
├── model/
│   └── Product.java
├── service/
│   ├── ProductService.java
│   └── ProductServiceImpl.java
└── main/
    └── Main.java
```

### 🔹 Product.java

Represents a product with:

* ID
* Name
* Category
* Price
* Rating

### 🔹 ProductService

Interface that defines:

* `addProduct()`
* `getProductById()` / `getProductByName()`
* `getAllProducts()`
* `deleteProductById()`
* `updateProduct()`
* `sortBy()` / `filterBy()`

### 🔹 ProductServiceImpl

* Implements all product operations
* Uses `HashMap<String, Product>` to store products
* Uses Stream API for filtering and sorting

---

## 🔢 Sample Use Case

```
Welcome to Product Catalog System!
1. Add Product
2. View All Products
3. View Product by ID
4. Update Product
5. Delete Product
6. Sort by Price
7. Filter by Category

Enter choice: 1
Enter ID: P101
Enter Name: Laptop
Enter Category: Electronics
Enter Price: ₹55000
Enter Rating: 4.5

Product added successfully!
```

---

## 📊 System Flow Diagram

```text
+------------+
|  Main.java |
+------------+
      |
      v
+---------------------+
| ProductServiceImpl  |
+---------------------+
      |
      v
+---------------+
|  Product.java  |
+---------------+
```

---

## 🚀 Getting Started

### ✅ Prerequisites

* Java 8+
* IDE (IntelliJ, Eclipse) or terminal with `javac`

### ▶️ Run Instructions

1. Download or clone this project
2. Navigate to `src/` folder
3. Compile and run:

```bash
javac model/*.java service/*.java main/Main.java
java main.Main
```

---

## 💡 Key Concepts Used

| Concept            | Usage                               |
| ------------------ | ----------------------------------- |
| OOP Principles     | Classes, Encapsulation, Inheritance |
| Interfaces         | `ProductService` defines contract   |
| Collections        | `HashMap` for product storage       |
| Java Streams       | Sorting, filtering operations       |
| Exception Handling | Preventing duplicate IDs            |

---

## 🔮 Future Enhancements

* Store products in a file or database
* Add discount logic and stock management
* Export product list as CSV/JSON
* Add GUI using JavaFX or Swing

---
---
---

# 📦 Java Product Management Console App

This **Java-based product management system** is a console application built using object-oriented principles, interfaces, and the Java Collections framework. It enables the management of products with full CRUD operations, filtering, sorting, and duplicate prevention.

---

## 🔍 Project Summary

### ✨ Key Features

* Add new products with unique names
* Update or delete existing products
* Retrieve all products or a specific one by name
* Sort products by name, price, or category
* Filter products by category or price range
* Prevent duplicate product names

---

## 🧠 System Design & Architecture

This application uses the following architecture layers:

```
+------------------+
|    Main.java     |  <-- Entry point (CLI interaction)
+------------------+
        |
        v
+---------------------------+
|   ProductServiceImpl.java |
| (implements ProductService)|
+---------------------------+
        |
        v
+------------------+
|  Product.java     |  <-- Model class
+------------------+
```

### 📁 Folder Structure

```
src/
├── model/
│   └── Product.java
├── service/
│   ├── ProductService.java
│   └── ProductServiceImpl.java
└── main/
    └── Main.java
```

---

## 🔩 Class Responsibilities

### ✅ `Product.java`

Represents a single product with:

* `String name`
* `double price`
* `String category`

### ✅ `ProductService`

Defines the operations allowed:

* `addProduct(Product)`
* `getProductByName(String)`
* `getAllProducts()`
* `updateProduct(Product)`
* `deleteProduct(String name)`
* `sortProductsBy(String field)`
* `filterByCategory(String)`
* `filterByPriceRange(double min, double max)`

### ✅ `ProductServiceImpl`

Implements logic and stores products using `HashMap<String, Product>`.
Uses Stream API for sorting and filtering operations.
Prevents addition of duplicate product names.

---

## 🔢 Example Flow

```bash
Welcome to Product Manager
1. Add Product
2. View All Products
3. Get Product by Name
4. Update Product
5. Delete Product
6. Sort by Price
7. Filter by Category
Enter your choice: 1

Enter product name: Mobile
Enter category: Electronics
Enter price: 30000
Product added successfully.
```

---

## 📊 System Flow Diagram

```text
+-------------+
|  Main.java  |
+-------------+
      |
      v
+------------------------+
| ProductServiceImpl.java|
+------------------------+
      |
      v
+--------------+
| Product.java |
+--------------+
```

---

## 🚀 How to Run This App

### ✅ Requirements

* Java 8 or higher
* Any IDE (IntelliJ, Eclipse) or terminal

### ▶️ Steps

1. Extract project ZIP
2. Navigate to `src/`
3. Compile and run:

```bash
javac model/*.java service/*.java main/Main.java
java main.Main
```

---

## 📚 Concepts Demonstrated

| Concept                | Applied In                           |
| ---------------------- | ------------------------------------ |
| OOP                    | Encapsulation (Product), Abstraction |
| Interface-based Design | `ProductService` for loose coupling  |
| Java Collections       | HashMap to store unique products     |
| Java Streams           | Sorting and filtering logic          |
| Exception Handling     | Prevent duplicate product names      |

---

## 🔮 Future Enhancements

* Add persistent file or database storage
* JSON/CSV product export
* Web version using Spring Boot
* REST API integration

---

## 👨‍💻 Author

**Anshu**
