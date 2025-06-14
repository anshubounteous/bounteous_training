# Library Management System

A full-featured Java-based Library Management System using Object-Oriented Programming (OOP), Java Collections, Generics, Exception Handling, Multithreading, and Java 8 features. It simulates the core operations of a physical library.

---

## Overview

This system allows librarians to manage books and members, issue/return books, track overdue records, and generate reports. It runs in the console and uses in-memory collections to simulate a basic library system.

---

## Project Structure

```
src/
├── main/
│   └── Main.java
├── model/
│   ├── Book.java
│   ├── Member.java
│   └── LendingRecord.java
├── repository/
│   ├── Repository.java
│   ├── BookRepo.java
│   ├── MemberRepo.java
│   └── LendingRepo.java
├── exception/
│   ├── BookNotAvailableException.java
│   ├── MemberNotFoundException.java
│   └── OverdueBookException.java
└── service/
    ├── LibraryService.java
    └── OverdueMonitor.java
```

---

## System Design & Logic

### Book

* Fields: `bookId`, `title`, `author`, `isIssued`
* Implements `Comparable<Book>` to support sorting by title

### Member

* Fields: `memberId`, `name`, `email`

### LendingRecord

* Fields: `recordId`, `book`, `member`, `issueDate`, `dueDate`, `returnDate`
* Auto-generates unique recordId and sets dueDate (7 days ahead)

### Repositories

* `Repository<T>`: Generic base class to manage `Book`, `Member`
* `BookRepo`, `MemberRepo`: Extend generic repo to store using `HashMap<String, T>`
* `LendingRepo`: Stores all `LendingRecord` using `ArrayList`

### Service Layer (`LibraryService`)

* Core business logic:

  * Add, remove, search books/members
  * Issue book: creates a `LendingRecord`, marks book as issued
  * Return book: updates the `LendingRecord` returnDate
  * Prevents issuing:

    * If book is already issued (`BookNotAvailableException`)
    * If member is not found (`MemberNotFoundException`)
    * If member has an overdue book (`OverdueBookException`)
  * Supports filtering/sorting using Java Streams

### Multithreading (`OverdueMonitor`)

* Runs as a daemon thread every 60 seconds
* Scans `LendingRecord`s where:

  * `dueDate < LocalDate.now()` and `returnDate == null`
* Logs overdue book details

---

## Sample Use-Case (Main.java)

```java
// Add books & members
library.addBook(new Book("B001", "Clean Code", "Robert C. Martin"));
library.addMember(new Member("M001", "Alice", "alice@example.com"));

// Issue book
library.issueBook("B001", "M001");

// Return book after some time
library.returnBook("B001", "M001");

// Start overdue checker in background
new OverdueMonitor(library.getLendingRecords()).start();
```

---

## System Flow Diagram

```text
+----------------+       +------------------+       +-------------------+
|   Librarian    | <---> | LibraryService   | <---> | Repositories       |
+----------------+       +------------------+       +-------------------+
        |                      |                            |
        |----> Add Book ------>|                            |
        |----> Add Member ---->|                            |
        |----> Issue Book ---->|                            |
        |                      |------> Validate ---------->|
        |                      |<------ Book & Member ------|
        |                      |                            |
        |<---- Response -------|                            |

[Background Thread]
    +-------------------------+
    |    OverdueMonitor       |
    |   (Daemon Thread)       |
    +-------------------------+
             |
             |---> Scans LendingRepo every 60s
             |---> Logs overdue records
```

---

## Getting Started


###  Run the Project

1. Clone the repository or unzip the provided source files
2. Open the project in your IDE
3. Ensure the folder structure under `src/` is maintained
4. Run `Main.java`

---

## Functional Highlights

| Feature                       | Description                      |
| ----------------------------- | -------------------------------- |
| Add Books & Members           | CLI or code-based entry          |
| Search by Author/Title        | Filter with Java Streams         |
| Sort Books by Title           | `Comparable` interface           |
| Issue/Return Book             | Lending logic with date tracking |
| Prevent Issue on Overdue      | Exception thrown on violation    |
| Multithreaded Overdue Scanner | Daemon background process        |
| Extensible Codebase           | Modular, reusable components     |
