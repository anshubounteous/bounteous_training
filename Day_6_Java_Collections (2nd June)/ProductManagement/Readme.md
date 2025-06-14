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
