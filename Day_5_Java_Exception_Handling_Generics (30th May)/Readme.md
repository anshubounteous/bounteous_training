# 🚗 Car Customization Console App (Java)

A command-line Car Customization Application that simulates selecting and modifying car models using a modular design. It demonstrates strong Object-Oriented Programming (OOP) principles, interface-model-service architecture, Java Collections, and abstraction.

---

## 📦 Overview

This application allows users to:

* Choose from various car base models (SUV, Sedan, Hatchback)
* Customize the car with additional features (e.g., sunroof, music system)
* View full configuration details and final price

---

## 🧠 System Design & Architecture

The project uses **interface-driven development** with three main layers:

1. **Model Layer**

   * Represents data entities like `Car`, `Customization`
2. **Service Layer**

   * Implements business logic like applying modifications and calculating prices
3. **App Layer (Main.java)**

   * Handles user interaction through the command line

---

## 📁 Project Structure

```
src/
├── model/
│   ├── Car.java
│   ├── SUV.java
│   ├── Sedan.java
│   ├── Hatchback.java
│   └── Customization.java
├── service/
│   ├── CarService.java (interface)
│   └── CarServiceImpl.java
└── main/
    └── Main.java
```

---

## 🧾 System Flow Diagram

```text
+-------------------+       +--------------------+       +------------------+
|     Main.java     | <---> |   CarServiceImpl    | <---> |      Model        |
+-------------------+       +--------------------+       +------------------+
        |                               |                          |
        |--> Select Car Type ---------->|
        |--> Add Features -------------->|
        |                               |--> Fetch base Car        |
        |                               |--> Apply Customizations  |
        |<-- Display Config ------------ |
```

---

## 🔧 Car Types and Features

### Base Models:

* 🚙 SUV
* 🚗 Sedan
* 🚕 Hatchback

### Possible Customizations:

* Sunroof
* Alloy Wheels
* Touchscreen Infotainment
* Music System
* Leather Seats

Each base model has a base price. Final price = base price + total cost of selected features.

---

## 🧪 Example Output

```
Welcome to the Car Customization App!
Choose car type: SUV
Base Price: ₹15,00,000

Available Add-ons:
1. Sunroof (₹30,000)
2. Music System (₹25,000)
3. Leather Seats (₹50,000)
Enter feature numbers to add (comma-separated): 1,3

Your customized SUV:
- Base: SUV
- Features: Sunroof, Leather Seats
- Final Price: ₹15,80,000
```

---

## 🚀 Getting Started

### ✅ Prerequisites

* Java JDK 8 or higher
* Any IDE (e.g., IntelliJ, Eclipse) or Terminal

### ▶️ Run the App

1. Download or clone the repository
2. Import it into your IDE or compile via command line
3. Run `Main.java`

```
$ javac model/*.java service/*.java main/Main.java
$ java main.Main
```

---

## 💡 Concepts Used

| Concept          | Description                                             |
| ---------------- | ------------------------------------------------------- |
| OOP              | Inheritance (Car subclasses), Polymorphism, Abstraction |
| Interface        | `CarService` decouples logic from implementation        |
| Java Collections | Feature storage using List/Map                          |
| Modularity       | Easy to extend with new car types or features           |

---

## 🔮 Future Enhancements

* GUI using JavaFX or Swing
* Persist configurations using JSON or DB
* Add login/user profile to save builds

---



# 💰 Java Tax Calculator CLI App

This is a **modular, interface-driven Java console application** that calculates income tax based on user's age and declared deductions. The app handles various tax slabs and deduction categories as per Indian tax rules and demonstrates key object-oriented and enterprise-grade Java concepts.

---

## 📦 Overview

### 🔧 Features

* Calculates income tax based on age group (General, Senior, Super Senior)
* Supports deductions under sections:

  * **80C** (e.g., LIC, PPF, ELSS)
  * **80D** (Health Insurance)
  * **Section 24** (Home Loan Interest)
* Validates deduction limits
* Returns tax payable with detailed summary

---

## 📁 Project Structure

```
src/
├── model/
│   ├── TaxPayer.java
│   └── Deduction.java
├── service/
│   ├── TaxCalculatorService.java
│   └── TaxCalculatorServiceImpl.java
├── util/
│   └── TaxConstants.java
├── exception/
│   ├── InvalidAgeException.java
│   └── InvalidDeductionException.java
└── main/
    └── Main.java
```

---

## 🧠 System Design & Logic

### 📌 `TaxPayer`

* Fields: name, age, annualIncome, deductions
* Age category logic:

  * < 60: General
  * 60–79: Senior Citizen
  * 80+: Super Senior Citizen

### 📌 `Deduction`

* Holds deduction values for 80C, 80D, and Section 24
* Uses validation against maximum deduction limits from `TaxConstants`

### 📌 `TaxCalculatorService`

* Interface with methods to:

  * Calculate tax
  * Apply deductions
  * Return summary details

### 📌 `TaxCalculatorServiceImpl`

* Implements logic for tax slab calculations
* Uses conditions for slab thresholds and progressive tax logic
* Applies deduction reductions from taxable income before computing tax

---

## 📊 System Flow Diagram

```text
+--------------------+
|    Main.java       |
+--------------------+
         |
         v
+---------------------------+
|  TaxCalculatorServiceImpl|
+---------------------------+
         |
         v
+--------------------+      +------------------+
|    TaxPayer.java   |<---->| Deduction.java   |
+--------------------+      +------------------+
         |
         v
+--------------------+
| Tax Summary Output |
+--------------------+
```

---

## 🔢 Sample Use Case

```
Enter name: Rahul
Enter age: 45
Enter annual income: ₹12,00,000
Enter 80C deductions: ₹1,50,000
Enter 80D deductions: ₹25,000
Enter Section 24 deduction: ₹1,50,000

----- Tax Summary -----
Name: Rahul
Category: General
Gross Income: ₹12,00,000
Total Deductions: ₹3,25,000
Taxable Income: ₹8,75,000
Tax Payable: ₹82,500
```

---

## 🚀 Getting Started

### ✅ Prerequisites

* Java 8+
* Any Java IDE (e.g., IntelliJ IDEA, Eclipse) or terminal with `javac`

### ▶️ Run the App

1. Clone the repository or extract files from the provided ZIP
2. Navigate to `src/` directory
3. Compile and run:

```bash
javac model/*.java service/*.java util/*.java exception/*.java main/Main.java
java main.Main
```

---

## 💡 Key Concepts Used

| Concept              | Usage                                             |
| -------------------- | ------------------------------------------------- |
| Interfaces           | `TaxCalculatorService` decouples business logic   |
| OOP Principles       | Encapsulation (TaxPayer), Abstraction (interface) |
| Exception Handling   | Custom exceptions for validation                  |
| Constants Management | `TaxConstants` for max deduction values           |
| User Interaction     | Console-based input/output                        |

---

## 🔮 Future Enhancements

* Add JSON file input/output for storing taxpayer records
* Export tax summaries to PDF
* RESTful API version using Spring Boot

---

## 👨‍💻 Author

**Anshu**
