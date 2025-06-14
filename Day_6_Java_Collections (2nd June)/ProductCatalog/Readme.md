# Java Product Catalog Console App

This project is a **Java-based console application** for managing a product catalog. It allows users to add, retrieve, update, delete, sort, and filter products. Built using an interface-model-service architecture, it showcases the use of Java Collections, Object-Oriented Programming (OOP), and Stream API.

---

## Overview

### Features

* Add new products with unique IDs
* Retrieve all or specific products by ID or name
* Update product details
* Delete products
* Sort products by price, name, or rating
* Filter products by category or price range
* Prevent duplicate products

---

### Architecture Layers

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

## Project Structure

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

### Product.java

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

## Sample Use Case

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

## System Flow Diagram

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

## Getting Started

### Run Instructions

1. Download or clone this project
2. Navigate to `src/` folder
3. Compile and run:

```bash
javac model/*.java service/*.java main/Main.java
java main.Main
```

---

## Key Concepts Used

| Concept            | Usage                               |
| ------------------ | ----------------------------------- |
| OOP Principles     | Classes, Encapsulation, Inheritance |
| Interfaces         | `ProductService` defines contract   |
| Collections        | `HashMap` for product storage       |
| Java Streams       | Sorting, filtering operations       |
| Exception Handling | Preventing duplicate IDs            |

---
