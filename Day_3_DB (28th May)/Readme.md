📘 [View Full Documentation on Notion](https://wary-close-2bb.notion.site/Database-Assignment-2050747c01ec80a5955be63b2c90ab81?source=copy_link)

### Question 1: Create the tables below in the database

```sql
SHOW DATABASES;
CREATE DATABASE collegeDb;
USE collegeDb;

CREATE TABLE Address(
    address_id INT PRIMARY KEY,
    street_address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20)
);

INSERT INTO Address (address_id, street_address, city, state, postal_code) VALUES
(1, '123 Elm St', 'Springfield', 'IL', '62701'),
(2, '456 Oak St', 'Decatur', 'IL', '62521'),
(3, '789 Pine St', 'Champaign', 'IL', '61820'),
(4, '102 Birch Rd', 'Peoria', 'IL', '61602'),
(5, '205 Cedar Ave', 'Chicago', 'IL', '60601'),
(6, '310 Maple Dr', 'Urbana', 'IL', '61801'),
(7, '415 Oak Blvd', 'Champaign', 'IL', '61821'),
(8, '520 Pine Rd', 'Carbondale', 'IL', '62901');

CREATE TABLE Department(
    department_id INT PRIMARY KEY,
    department_name VARCHAR(100)
);

INSERT INTO Department (department_id, department_name) VALUES
(1, 'Computer Science'),
(2, 'Mechanical Engineering'),
(3, 'Electrical Engineering'),
(4, 'Civil Engineering'),
(5, 'Mathematics'),
(6, 'Biology');

CREATE TABLE Student (
    student_id INT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    birthdate DATE,
    department_id INT,
    address_id INT,
    FOREIGN KEY(department_id) REFERENCES Department(department_id),
    FOREIGN KEY(address_id) REFERENCES Address(address_id)
);

-- DROP TABLE Student;  -- Optional cleanup

INSERT INTO Student (student_id, first_name, last_name, birthdate, department_id, address_id) VALUES
(1, 'John', 'Doe', '1995-04-15', 1, 1),
(2, 'Jane', 'Smith', '1996-07-22', 2, 2),
(3, 'Alice', 'Johnson', '1994-11-30', 3, 3),
(4, 'Michael', 'Brown', '1997-02-19', 4, 4),
(5, 'Sophia', 'Davis', '1998-01-05', 5, 5),
(6, 'Daniel', 'Wilson', '1995-06-10', 6, 6),
(7, 'Olivia', 'Martinez', '1997-11-25', 1, 7),
(8, 'Ethan', 'Miller', '1996-03-30', 2, 8);

```

### Question 2: Use sample data to insert into database

```sql
LOAD DATA INFILE 'C:/Users/Anshu/studentdata.csv'
INTO TABLE Student
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 0 LINES;

```

### Question 3: Find total number of students

```sql
SELECT COUNT(*) FROM Student;
```

### Question 4: Find which department John belongs to

```sql
SELECT
    s.first_name, s.last_name, d.department_name
FROM
    Student s
INNER JOIN
    Department d ON s.department_id = d.department_id
WHERE
    s.first_name = 'John';

```

### Question 5: List all departments with their number of students

```sql
SELECT
    d.department_id, d.department_name, COUNT(s.student_id) AS student_count
FROM
    Department d
LEFT JOIN
    Student s ON d.department_id = s.department_id
GROUP BY
    d.department_id;
```

### Question 6: Select all students with their department and address

```sql
SELECT s.first_name, s.last_name, d.department_name, a.city, a.state, a.postal_code
FROM Student s
LEFT JOIN Department d ON s.department_id = d.department_id
LEFT JOIN Address a ON s.address_id = a.address_id;
```

### Question 7: Find all students in the Computer Science department

```sql
SELECT s.*
FROM Student s
LEFT JOIN Department d ON s.department_id = d.department_id
WHERE d.department_name = 'Computer Science';
```

### Question 8: Update John's city name to New York

```sql
UPDATE Address a
JOIN Student s ON a.address_id = s.address_id
SET a.city = 'New York'
WHERE s.first_name = 'John';
```

### Question 9: Delete a student from the Student table

```sql
DELETE FROM Student
WHERE student_id = 5;
```

### Question 10: Select all students with department and address in New York

```sql
SELECT s.*, a.city, d.department_name
FROM Student s
LEFT JOIN Department d ON s.department_id = d.department_id
LEFT JOIN Address a ON s.address_id = a.address_id
WHERE a.city = 'New York';
```

### Question 11: Count students in each department

```sql
SELECT d.department_name, COUNT(s.student_id)
FROM Department d
LEFT JOIN Student s ON d.department_id = s.department_id
GROUP BY d.department_name;
```

### Question 12: Find students who live in Springfield

```sql
SELECT s.*, a.city
FROM Student s
INNER JOIN Address a ON s.address_id = a.address_id
WHERE a.city = 'Springfield';
```

### Question 13: Select students born in February

```sql
SELECT *
FROM Student
WHERE MONTH(birthdate) = 2;
```

### Question 14: Get department and address of a specific student (e.g., John)

```sql
SELECT s.first_name, d.*, a.*
FROM Student s
INNER JOIN Department d ON d.department_id = s.department_id
INNER JOIN Address a ON s.address_id = a.address_id
WHERE s.first_name = 'John';
```

### Question 15: Find students born between 1995 and 1998

```sql
SELECT *
FROM Student
WHERE YEAR(birthdate) > 1995 AND YEAR(birthdate) < 1998;
```

### Question 16: List all students with department names, sorted by department

```sql
SELECT s.first_name, s.last_name, d.department_name
FROM Student s
LEFT JOIN Department d ON s.department_id = d.department_id
ORDER BY d.department_name;
```

### Question 17: Find number of students in each department living in 'Champaign'

```sql
SELECT d.department_name, COUNT(s.student_id) AS student_count
FROM Student s
JOIN Address a ON s.address_id = a.address_id
JOIN Department d ON s.department_id = d.department_id
WHERE a.city = 'Champaign'
GROUP BY d.department_name;
```

### Question 18: Retrieve names of students who live on 'Pine' Street

```sql
SELECT s.first_name, s.last_name, a.street_address
FROM Student s
INNER JOIN Address a ON s.address_id = a.address_id
WHERE a.street_address LIKE '%Pine%';
```

### Question 19: Update department of student with student_id = 6 to 'Mechanical Engineering'

```sql
UPDATE Student
SET department_id = (
    SELECT department_id FROM Department WHERE department_name = 'Mechanical Engineering'
)
WHERE student_id = 6;
```

### Question 20: Find the student(s) who live in the city 'Chicago' and are in the 'Mathematics' department

```sql
SELECT s.*
FROM Student s
JOIN Address a ON s.address_id = a.address_id
JOIN Department d ON s.department_id = d.department_id
WHERE a.city = 'Chicago' AND d.department_name = 'Mathematics';
```

### Question 21: List all students who have an address in 'Urbana' or 'Peoria'

```sql
SELECT s.*
FROM Student s
JOIN Address a ON s.address_id = a.address_id
WHERE a.city IN ('Urbana', 'Peoria');
```

### Question 22: Find the student with the highest student_id

```sql
SELECT *
FROM Student
ORDER BY student_id DESC
LIMIT 1;
```

### Question 23: Find all students who are not in the 'Computer Science' department

```sql
SELECT s.*
FROM Student s
JOIN Department d ON s.department_id = d.department_id
WHERE d.department_name != 'Computer Science';
```

### Question 24: Count the total number of addresses in the 'Champaign' city

```sql
SELECT COUNT(*) AS address_count
FROM Address
WHERE city = 'Champaign';
```

### Question 25: Find the name of the student who lives at '520 Pine Rd'

```sql
SELECT s.first_name, s.last_name
FROM Student s
JOIN Address a ON s.address_id = a.address_id
WHERE a.street_address = '520 Pine Rd';
```

### Question 26: Get the average age of students in the 'Electrical Engineering' department

```sql
SELECT AVG(TIMESTAMPDIFF(YEAR, birthdate, CURDATE())) AS average_age
FROM Student s
JOIN Department d ON s.department_id = d.department_id
WHERE d.department_name = 'Electrical Engineering';
```

### Question 27: List the students, their department, and the city where they live, but only for those in departments starting with 'M'

```sql
SELECT s.first_name, s.last_name, d.department_name, a.city
FROM Student s
JOIN Department d ON s.department_id = d.department_id
JOIN Address a ON s.address_id = a.address_id
WHERE d.department_name LIKE 'M%';
```

### Question 28: Delete a student from the 'Mechanical Engineering' department

```sql
DELETE FROM Student
WHERE department_id = (
    SELECT department_id FROM Department WHERE department_name = 'Mechanical Engineering'
)
LIMIT 1;
```

# E-commerce Shop Database:

### Question 1. Retrieve All Orders with Their Customer Details and Current Status

```sql
SELECT o.*, c.*, s.status_name
FROM order_schema.orders o
INNER JOIN order_schema.customer c ON o.customer_id = c.customer_id
INNER JOIN order_schema.status s ON o.status_id = s.status_id;
```

![Screenshot 2025-06-01 193835.png](attachment:6805d86e-96b7-4201-8cf6-bf1a2d3bf95d:Screenshot_2025-06-01_193835.png)

### Question 2. Get the Total Value of Orders for a Given Customer in a Specific Time Period

```sql
SELECT o.customer_id, SUM(o.total_amount) AS total_value
FROM order_schema.orders o
WHERE o.order_date BETWEEN '2025-02-01' AND '2025-02-28'
GROUP BY o.customer_id;
```

![Screenshot 2025-06-01 194344.png](attachment:12d77f81-d13f-45bd-a227-53808e1e1d04:Screenshot_2025-06-01_194344.png)

### Question 3. Find the Most Expensive Order by Customer

```sql
SELECT o.customer_id, o.order_id, o.total_amount
FROM order_schema.orders o
WHERE (o.customer_id, o.total_amount) IN (
    SELECT customer_id, MAX(total_amount)
    FROM order_schema.orders
    GROUP BY customer_id
);
```

![Screenshot 2025-06-01 194409.png](attachment:aa8a3188-3ddd-402a-889b-f805e2b17333:Screenshot_2025-06-01_194409.png)

### Question 4. Find the Total Revenue for Each Product Based on Orders

```sql
SELECT p.product_id, p.product_name, SUM(oi.quantity * oi.price) AS total_revenue
FROM order_schema.order_items oi
JOIN order_schema.product p ON oi.product_id = p.product_id
GROUP BY p.product_id, p.product_name;
```

![Screenshot 2025-06-01 194432.png](attachment:08b8bfb2-1430-4312-9b7c-f7f2f2a85518:Screenshot_2025-06-01_194432.png)

### Question 5. Retrieve Order ID, Customer ID, and Total Amount (Display '0.00' if NULL)

```sql
SELECT order_id, customer_id, COALESCE(total_amount, 0.00) AS total_amount
FROM order_schema.orders;
```

![Screenshot 2025-06-01 194456.png](attachment:a894227e-a0cf-439f-a5b5-b805113d9dba:Screenshot_2025-06-01_194456.png)

### Question 6. Retrieve the Order History of a Specific Customer Along with Product Details

```sql
SELECT oh.order_id, oh.status_change_date, oh.status_description,
       p.product_id, p.product_name, oi.quantity, oi.price
FROM order_schema.order_history oh
JOIN order_schema.orders o ON oh.order_id = o.order_id
JOIN order_schema.order_items oi ON o.order_id = oi.order_id
JOIN order_schema.product p ON oi.product_id = p.product_id
WHERE o.customer_id = 1;
```

![Screenshot 2025-06-01 194528.png](attachment:3dc0b33b-d202-4bf2-96e4-29368528bdec:Screenshot_2025-06-01_194528.png)

### Question 7. Get the Average Order Value Per Customer in the Last 30 Days

```sql
SELECT customer_id, AVG(total_amount) AS avg_order_value
FROM order_schema.orders
WHERE order_date >= CURRENT_DATE - INTERVAL '30 days'
GROUP BY customer_id;
```

![Screenshot 2025-06-01 194557.png](attachment:3bf7f347-68de-47d5-8aed-11bf03f626e4:Screenshot_2025-06-01_194557.png)

### Question 8. Get the Top 5 Products with the Highest Number of Orders

```sql
SELECT p.product_id, p.product_name, COUNT(oi.order_id) AS total_orders
FROM order_schema.order_items oi
JOIN order_schema.product p ON oi.product_id = p.product_id
GROUP BY p.product_id, p.product_name
ORDER BY total_orders DESC
LIMIT 5;
```

![Screenshot 2025-06-01 194614.png](attachment:d26ce2e5-9056-4629-96f4-d85a6b062d66:Screenshot_2025-06-01_194614.png)

### Question 9. Get the Customers Who Have Not Placed Any Orders in the Last 60 Days

```sql
SELECT c.*
FROM order_schema.customer c
WHERE c.customer_id NOT IN (
    SELECT customer_id
    FROM order_schema.orders
    WHERE order_date >= CURRENT_DATE - INTERVAL '60 days'
);
```

![Screenshot 2025-06-01 194633.png](attachment:5c545ecc-1f4b-40d3-b2a4-e8d51a26fa56:Screenshot_2025-06-01_194633.png)

### Question 10. List the Orders with Products Ordered More Than Once, Sorted by Order Date

```sql
SELECT o.order_id, o.order_date, p.product_name, oi.quantity
FROM order_schema.orders o
JOIN order_schema.order_items oi ON o.order_id = oi.order_id
JOIN order_schema.product p ON oi.product_id = p.product_id
WHERE oi.quantity > 1
ORDER BY o.order_date;
```

![Screenshot 2025-06-01 194653.png](attachment:30d95385-3da7-45ec-a0c2-6e42218742b2:Screenshot_2025-06-01_194653.png)

### Question 11. Retrieve the Number of Orders and Total Revenue for Each Status

```sql
SELECT s.status_name, COUNT(o.order_id) AS total_orders, SUM(o.total_amount) AS total_revenue
FROM order_schema.status s
LEFT JOIN order_schema.orders o ON s.status_id = o.status_id
GROUP BY s.status_name;
```

![Screenshot 2025-06-01 194709.png](attachment:58bf074d-24a2-491c-81d5-b9634326e200:Screenshot_2025-06-01_194709.png)

### Question 12. Customers Who Have Ordered More Than a Specific Product (e.g., "Laptop")

```sql
SELECT c.customer_id, c.first_name, c.last_name
FROM order_schema.customer c
JOIN order_schema.orders o ON c.customer_id = o.customer_id
JOIN order_schema.order_items oi ON o.order_id = oi.order_id
JOIN order_schema.product p ON oi.product_id = p.product_id
WHERE p.product_name = 'Laptop';
```

![Screenshot 2025-06-01 194729.png](attachment:31e336f1-8341-4741-9403-00c6299b28d4:Screenshot_2025-06-01_194729.png)

### Question 13. Find the Products That Have Never Been Ordered

```sql
SELECT p.product_id, p.product_name
FROM order_schema.product p
LEFT JOIN order_schema.order_items oi ON p.product_id = oi.product_id
WHERE oi.product_id IS NULL;
```

![Screenshot 2025-06-01 194746.png](attachment:5793e83a-9c07-4644-aae5-07f1e385a829:Screenshot_2025-06-01_194746.png)

### Question 14. Get the Total Quantity of Products Ordered in the Last 7 Days

```sql
SELECT p.product_id, p.product_name, SUM(oi.quantity) AS total_quantity
FROM order_schema.order_items oi
JOIN order_schema.product p ON oi.product_id = p.product_id
JOIN order_schema.orders o ON oi.order_id = o.order_id
WHERE o.order_date >= CURRENT_DATE - INTERVAL '7 days'
GROUP BY p.product_id, p.product_name;
```

![Screenshot 2025-06-01 194804.png](attachment:7e549c89-7092-488e-be65-0b53aa1702e9:Screenshot_2025-06-01_194804.png)

### Question 15. Create a View Named product_details that Includes All Columns from Product Table

```sql
CREATE VIEW order_schema.product_details AS
SELECT *
FROM order_schema.product;
```

![Screenshot 2025-06-01 194828.png](attachment:a2a62505-d837-484f-b59a-866b3bb5fafc:Screenshot_2025-06-01_194828.png)

### Question 16. Create a View Named order_summary with order_id, customer_id, order_date, total_amount, status_name

```sql
CREATE VIEW order_schema.order_summary AS
SELECT o.order_id, o.customer_id, o.order_date, o.total_amount, s.status_name
FROM order_schema.orders o
JOIN order_schema.status s ON o.status_id = s.status_id;
```

![Screenshot 2025-06-01 194845.png](attachment:6086d389-0383-4b8f-9138-2dd055c01276:Screenshot_2025-06-01_194845.png)
