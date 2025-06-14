
# Java Tax Calculator CLI App

This is a **modular, interface-driven Java console application** that calculates income tax based on user's age and declared deductions. The app handles various tax slabs and deduction categories as per Indian tax rules and demonstrates key object-oriented and enterprise-grade Java concepts.

---

## Overview

### Features

* Calculates income tax based on age group (General, Senior, Super Senior)
* Supports deductions under sections:

  * **80C** (e.g., LIC, PPF, ELSS)
  * **80D** (Health Insurance)
  * **Section 24** (Home Loan Interest)
* Validates deduction limits
* Returns tax payable with detailed summary

---

## Project Structure

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

## System Design & Logic

### `TaxPayer`

* Fields: name, age, annualIncome, deductions
* Age category logic:

  * < 60: General
  * 60–79: Senior Citizen
  * 80+: Super Senior Citizen

### `Deduction`

* Holds deduction values for 80C, 80D, and Section 24
* Uses validation against maximum deduction limits from `TaxConstants`

### `TaxCalculatorService`

* Interface with methods to:

  * Calculate tax
  * Apply deductions
  * Return summary details

### `TaxCalculatorServiceImpl`

* Implements logic for tax slab calculations
* Uses conditions for slab thresholds and progressive tax logic
* Applies deduction reductions from taxable income before computing tax

---

## System Flow Diagram

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

## Sample Use Case

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

## Getting Started

### Run the App

1. Clone the repository or extract files from the provided ZIP
2. Navigate to `src/` directory
3. Compile and run:

```bash
javac model/*.java service/*.java util/*.java exception/*.java main/Main.java
java main.Main
```

---

## Key Concepts Used

| Concept              | Usage                                             |
| -------------------- | ------------------------------------------------- |
| Interfaces           | `TaxCalculatorService` decouples business logic   |
| OOP Principles       | Encapsulation (TaxPayer), Abstraction (interface) |
| Exception Handling   | Custom exceptions for validation                  |
| Constants Management | `TaxConstants` for max deduction values           |
| User Interaction     | Console-based input/output                        |
---
