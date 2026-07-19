# Inventory Management System Backend

## Overview

The Inventory Management System Backend is a Java-based application designed to manage products, users, inventory, orders, delivery workflows, and business analytics.

This backend was developed using:

- Java
- JDBC
- MySQL
- Object-Oriented Programming
- Data Structures and Algorithms

The project serves as both a complete inventory management solution and as an academic demonstration of OOP, DSA, and Database Management concepts.

The backend has been fully refactored and prepared for JavaFX integration. Business logic has been separated from database operations using the DAO (Data Access Object) pattern.

---

# Project Objectives

The system was designed to provide:

- Inventory management
- Product tracking
- Order management
- Salesman assignment and delivery management
- User authentication
- Profit and sales reporting
- Database persistence
- DSA implementation through practical use cases

---

# Technologies Used

## Programming Language

- Java

## Database

- MySQL

## Connectivity

- JDBC

## Design Principles

- Object-Oriented Programming
- Encapsulation
- Inheritance
- Polymorphism
- Composition
- Layered Architecture

## Data Structures

- Linked List
- Queue
- ArrayList

---

# System Architecture

The application follows a layered architecture model.

```text
┌────────────────────────────┐
│        JavaFX GUI          │
└─────────────┬──────────────┘
              │
              ▼
┌────────────────────────────┐
│      Business Layer        │
│                            │
│ User                       │
│ Admin                      │
│ Owner                      │
│ Salesman                   │
│ Product                    │
│ Order                      │
└─────────────┬──────────────┘
              │
              ▼
┌────────────────────────────┐
│         DAO Layer          │
│                            │
│ UserDAO                    │
│ ProductDAO                 │
│ OrderDAO                   │
│ ReportDAO                  │
└─────────────┬──────────────┘
              │
              ▼
┌────────────────────────────┐
│       MySQL Database       │
└────────────────────────────┘
```

The goal of this architecture is to keep:

- UI independent from the database
- Business logic independent from SQL
- Database access centralized through DAOs

---

# User Hierarchy

The system uses inheritance.

```text
User
│
├── Admin
│
├── Owner
│
└── Salesman
```

The User class serves as the parent class and provides common functionality shared by all system roles.

---

# User Class

## Purpose

Acts as the base class for all users.

## Responsibilities

- Store user information
- Login
- Logout
- Password management
- Session state management

## Attributes

```java
userid
name
password
role
salary
loggedIn
```

## Methods

### login()

Authenticates the current user.

```java
login(String password)
```

### logout()

Logs the user out.

```java
logout()
```

### changePassword()

Validates the old password, updates the database, and synchronizes the object.

```java
changePassword(String oldPass, String newPass)
```

## Password Synchronization Flow

```text
User enters old password
            ↓
Validate old password
            ↓
Update database
            ↓
Update object password
            ↓
Synchronization complete
```

This ensures that both object state and database state remain consistent.

---

# Product Class

## Purpose

Represents an inventory item.

## Attributes

```java
productId
name
quantity
costPrice
sellingPrice
expiryDate
```

## Features

### Stock Reduction

```java
soldFromStock()
```

Reduces inventory quantity when products are sold.

---

### Restocking

```java
reStock()
```

Adds inventory quantity.

---

### Expiry Check

```java
checkExpiry()
```

Determines whether a product has expired.

---

### Low Stock Detection

```java
lowStock()
```

Uses a predefined threshold.

```java
THRESHOLD = 5
```

Products at or below this threshold are considered low stock.

---

# Order System

The order system underwent a complete redesign.

Initially, the project used:

```text
OrderHeader
OrderItem
```

These classes were removed and replaced with a Linked List implementation.

This redesign improved:

- DSA demonstration
- Flexibility
- JavaFX integration

---

# Linked List Implementation

## Order Class

The Order class serves two purposes:

1. Individual Order Node
2. Linked List Cart Manager

Each item inside an order acts as a node.

```java
Order next;
```

---

## Structure

```text
Head
 │
 ▼
Item 1
 │
 ▼
Item 2
 │
 ▼
Item 3
 │
 ▼
null
```

---

## Why Linked List?

The number of products inside a cart is unknown beforehand.

A Linked List allows:

- Dynamic growth
- Efficient insertion
- Traversal of items
- Practical DSA implementation

---

## Cart Features

### Add Product

```java
addProduct()
```

Adds products to the linked list.

---

### Display Cart

```java
displayCart()
```

Returns a formatted String containing cart information.

Used by:

- Console
- JavaFX TextArea
- Reports

---

### Calculate Total Price

```java
getTotalOrderPrice()
```

Traverses the linked list and calculates:

```text
Σ(quantity × sellingPrice)
```

---

### Calculate Total Profit

```java
getTotalOrderProfit()
```

Traverses the linked list and calculates:

```text
Σ((sellingPrice - costPrice) × quantity)
```

---

### Empty Cart

```java
clearCart()
```

Removes all nodes from the linked list.

---

# Queue Implementation

The Salesman class implements a Queue.

## Purpose

Manage delivery orders using FIFO behavior.

---

## Queue Declaration

```java
Queue<Integer> assignedOrders;
```

Implemented using:

```java
LinkedList<Integer>
```

---

## Why Queue?

Orders should be delivered in the same sequence they were assigned.

FIFO naturally models this behavior.

---

## Example

```text
Order 101
Order 102
Order 103
```

Delivery Order:

```text
Order 101
Order 102
Order 103
```

---

## Queue Operations

### Load Orders

```java
loadAssignedOrders()
```

Loads pending orders into memory.

---

### View Next Order

```java
viewNextOrder()
```

Uses:

```java
peek()
```

---

### Deliver Order

```java
deliverNextOrder()
```

Uses:

```java
poll()
```

after successful delivery.

---

# ArrayList Usage

ArrayList is used throughout the project.

Examples:

```java
ArrayList<Product>
ArrayList<Integer>
```

Used for:

- Inventory retrieval
- Report generation
- Database query results

---

# Admin Module

The Admin role manages day-to-day business operations.

---

## Responsibilities

### Add Salesman

```java
addSalesMan()
```

Creates new salesman accounts.

---

### Add Product

```java
addProduct()
```

Stores products inside the database inventory.

---

### Create Order

```java
addOrder()
```

Creates a Linked List cart and persists it.

---

### Assign Orders

```java
assignOrderToSalesman()
```

Assigns pending orders to salesmen.

---

### Inventory Access

```java
checkInventory()
```

Returns:

```java
ArrayList<Product>
```

Designed specifically for JavaFX compatibility.

---

### Delete Operations

```java
deleteProduct()
deleteOrder()
deleteUser()
```

Administrative cleanup operations.

---

# Salesman Module

The Salesman role manages deliveries.

---

## Responsibilities

### Load Orders

```java
loadAssignedOrders()
```

Retrieves assigned pending orders.

---

### View Next Order

```java
viewNextOrder()
```

Returns:

```java
Integer orderId
```

---

### Deliver Orders

```java
deliverNextOrder()
```

Updates:

```text
PENDING → DELIVERED
```

within the database.

---

### View Delivered Orders

```java
viewDeliveredOrders()
```

Returns delivered order IDs.

---

### Queue Management

Uses Queue operations:

```java
offer()
peek()
poll()
```

---

# Owner Module

The Owner acts as the reporting and analytics role.

---

## Responsibilities

### Daily Reports

```java
dailySales()
```

Displays:

- Today's sales
- Today's profit

---

### Overall Reports

```java
totalSales()
totalProfit()
totalOrders()
```

---

### Product Analytics

```java
mostSellingProduct()
leastSellingProduct()

mostProfitableProduct()
leastProfitableProduct()
```

---

### Inventory Monitoring

```java
inventory()
```

Retrieves inventory data.

---

# DAO Layer

DAO stands for:

```text
Data Access Object
```

DAOs isolate SQL from business logic.

No business class directly executes SQL queries.

This improves:

- Maintainability
- Testing
- Future GUI integration

---

# UserDAO

Responsible for all user-related operations.

## Features

```java
registerUser()
loginUser()
changePassword()
deleteUser()
getRole()
getId()
```

---

# ProductDAO

Responsible for inventory persistence.

## Features

```java
addProduct()
getProduct()
getAllProducts()
updateQuantity()
updatePrice()
removeProduct()
```

---

# OrderDAO

Responsible for all order persistence operations.

## Features

```java
createOrder()
createOrder(LocalDate)

addOrderItem()

saveCart()

assignSalesman()

markDelivered()

deleteOrder()

getPendingOrderIds()

getDeliveredOrderIds()

orderExists()

getAssignedSalesman()
```

---

## Order Persistence Flow

```text
Admin Creates Cart
          ↓
Linked List Created
          ↓
OrderDAO.createOrder()
          ↓
saveCart()
          ↓
Store Items In order_items
          ↓
Order Saved Successfully
```

---

# ReportDAO

Handles reporting and analytics.

## Available Reports

### Sales Reports

```java
totalSales()
todaySales()
```

---

### Profit Reports

```java
totalProfit()
todayProfit()
```

---

### Order Reports

```java
totalOrders()
```

---

### Product Analytics

```java
mostSellingProduct()
leastSellingProduct()

mostProfitableProduct()
leastProfitableProduct()
```

---

# Database Design

## users

Stores system users.

```text
user_id
user_name
user_password
role
```

---

## product

Stores inventory records.

```text
product_id
name
quantity
cost_price
sale_price
expirydate
lowstock_threshold
```

---

## orders

Stores order-level information.

```text
order_id
order_date
placed_by_admin_id
assigned_salesman_id
total_price
total_profit
status
```

---

## order_items

Stores product-level order information.

```text
order_item_id
order_id
purchase_id
product_id
product_name
quantity
selling_price
cost_price
```

---

# JavaFX Readiness

A major goal during refactoring was JavaFX compatibility.

Methods that originally printed to the console were redesigned to return data.

Examples:

### Before

```java
void checkInventory()
```

### After

```java
ArrayList<Product> checkInventory()
```

---

### Before

```java
void totalProfit()
```

### After

```java
String totalProfit()
```

---

This allows direct binding to:

- Labels
- TableViews
- ListViews
- TextAreas
- Charts

without modifying backend code.

---

# Complete System Workflow

```text
Admin Login
     ↓
Add Products
     ↓
Create Linked List Cart
     ↓
Save Order
     ↓
Assign Salesman
     ↓
Salesman Loads Queue
     ↓
Salesman Delivers Order
     ↓
Order Marked Delivered
     ↓
Reports Updated
     ↓
Owner Views Analytics
```

---

# Design Decisions

## Why Linked List?

To practically demonstrate:

- Node creation
- Traversal
- Dynamic storage

while managing cart items.

---

## Why Queue?

To model real-world delivery processing using FIFO behavior.

---

## Why DAO Pattern?

To separate:

```text
Business Logic
```

from

```text
Database Logic
```

making future maintenance easier.

---

## Why Return Data Instead of Printing?

To support both:

- Console-based interaction
- JavaFX-based interaction

using the same backend.

---

# Current Project Status

| Component | Status |
|------------|---------|
| OOP Design | ✅ Complete |
| User Management | ✅ Complete |
| Product Management | ✅ Complete |
| Inventory Management | ✅ Complete |
| Order Management | ✅ Complete |
| Linked List Integration | ✅ Complete |
| Queue Integration | ✅ Complete |
| Database Integration | ✅ Complete |
| Report System | ✅ Complete |
| DAO Layer | ✅ Complete |
| Backend Refactoring | ✅ Complete |
| JavaFX Preparation | ✅ Complete |

---

# Next Phase

```text
Frontend Development
(JavaFX)
```

Planned GUI modules:

- Login Screen
- Admin Dashboard
- Owner Dashboard
- Salesman Dashboard
- Product Management
- Inventory Viewer
- Order Creation Screen
- Reporting Dashboard

---

# Milestone Achieved

### Backend Complete

Major achievements:

- Full JDBC integration
- DAO-based architecture
- Linked List order cart implementation
- Queue-based salesman workflow
- Report generation module
- Database-synchronized password management
- Removal of legacy OrderHeader and OrderItem architecture
- JavaFX-ready backend design

**Status:** ✅ Backend Complete — Ready for Frontend Development