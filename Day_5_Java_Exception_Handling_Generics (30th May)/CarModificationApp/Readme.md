# Car Customization Console App (Java)

A command-line Car Customization Application that simulates selecting and modifying car models using a modular design. It demonstrates strong Object-Oriented Programming (OOP) principles, interface-model-service architecture, Java Collections, and abstraction.

---

## Overview

This application allows users to:

* Choose from various car base models (SUV, Sedan, Hatchback)
* Customize the car with additional features (e.g., sunroof, music system)
* View full configuration details and final price

---

## System Design & Architecture

The project uses **interface-driven development** with three main layers:

1. **Model Layer**

   * Represents data entities like `Car`, `Customization`
2. **Service Layer**

   * Implements business logic like applying modifications and calculating prices
3. **App Layer (Main.java)**

   * Handles user interaction through the command line

---

## Project Structure

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

## System Flow Diagram

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

## Car Types and Features

### Base Models:

* SUV
* Sedan
* Hatchback

### Possible Customizations:

* Sunroof
* Alloy Wheels
* Touchscreen Infotainment
* Music System
* Leather Seats

Each base model has a base price. Final price = base price + total cost of selected features.

---

## Example Output

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

## Getting Started

### Run the App

1. Download or clone the repository
2. Import it into your IDE or compile via command line
3. Run `Main.java`

```
$ javac model/*.java service/*.java main/Main.java
$ java main.Main
```

---

## Concepts Used

| Concept          | Description                                             |
| ---------------- | ------------------------------------------------------- |
| OOP              | Inheritance (Car subclasses), Polymorphism, Abstraction |
| Interface        | `CarService` decouples logic from implementation        |
| Java Collections | Feature storage using List/Map                          |
| Modularity       | Easy to extend with new car types or features           |

---
