#  Finance Dashboard Backend

A robust backend system for managing financial records, users, and analytics with role-based access control. Built using **Spring Boot**, secured with **JWT authentication**, and deployed using **Docker on Render**.

---

##  Live API Documentation

👉 Swagger UI:
https://finance-dashboard-backend-gteb.onrender.com/swagger-ui/index.html

---

##  Project Overview

This project simulates a **Finance Dashboard System** where users interact with financial data based on their roles.

It demonstrates:

* Clean backend architecture
* Secure authentication & authorization
* Data processing and analytics
* Production-ready deployment practices

---

##  Authentication & Roles

The system uses **JWT (JSON Web Token)** for authentication.

### Roles:

| Role    | Permissions                             |
| ------- | --------------------------------------- |
| ADMIN   | Full access (CRUD users & transactions) |
| ANALYST | View transactions & analytics           |
| VIEWER  | View dashboard summary only             |

---

##  Features

###  User Management

* Create, update, delete users (Admin)
* Assign roles (ADMIN / ANALYST / VIEWER)
* Enable/disable users
* Pagination support

---

###  Transaction Management

* Create, update, delete transactions
* Filter by category, type, date
* Pagination support
* Income & expense tracking

---

###  Dashboard APIs

* Total income
* Total expenses
* Net balance
* Category-wise insights(for ADMIN and ANALYST only)

---

###  Security

* JWT-based authentication
* Role-based authorization using Spring Security
* Protected endpoints

---

###  Validation & Error Handling

* Input validation using annotations
* Global exception handling.
* Standard API response format

---

##  Tech Stack

* **Backend:** Spring Boot
* **Security:** Spring Security + JWT
* **Database:** H2 (for deployment/demo)
* **Build Tool:** Maven
* **API Docs:** Springdoc OpenAPI (Swagger)
* **Deployment:** Docker + Render

---

##  API Endpoints

###  Auth

* `POST /auth/login`

---

###  Users (Admin)

* `GET /users?page=0&size=5`
* `GET /users/email?email=xyz@gmail.com`
* `POST /users` (Registering Users)
* `PUT /users/{id}`
* `DELETE /users/{id}`

---

###  Transactions

* `GET /transactions?page=0&size=5`
* `POST /transactions` (Admin only)
* `PUT /transactions/{id}` (Admin only)
* `DELETE /transactions/{id}` (Admin only)

---

###  Dashboard

* `GET /dashboard`
* `GET /dashboard/insights` (Admin and Analyst)

---

##  How to Use

1. Open Swagger UI
2. Call `/auth/login` → get JWT token
3. Click **Authorize**
4. Paste:

   ```
   Bearer YOUR_TOKEN
   ```
5. Access protected APIs

---

##  Deployment

The application is containerized using Docker and deployed on Render.

### Docker Highlights:

* Multi-stage build
* Maven build inside container
* Optimized runtime image

---

##  Key Highlights

* Clean layered architecture (Controller → Service → Repository)
* Role-based access control (RBAC)
* Secure API design
* Scalable and maintainable code structure
* Real-world backend practices

---

##  Author

**Sanskar Dwivedi**

---

