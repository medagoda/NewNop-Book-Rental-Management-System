# Rental Management System

A **Rental Management System** built with **React** (frontend) and **Spring Boot** (backend) for managing books, rentals, and returns.

---

## Table of Contents
- [Project Overview](#project-overview)
- [Backend Setup](#backend-setup)
- [API Endpoints](#api-endpoints)
- [Assumptions & Additional Features](#assumptions--additional-features)


---

## Project Overview
This system allows users to:
- View all books with availability status.
- Add, update, and delete books (only if not rented).
- Rent books.
- Search and filter books.
- See toast notifications for success/error actions.
- Use a responsive UI styled with Tailwind CSS.
- Open modals for update operations.

---

## Frontend Setup
```bash
# Clone the frontend repository
git clone <your-frontend-repo-url>
cd frontend

# Install dependencies
npm install
```

## Backend Setup
```
# Clone the backend repository
git clone <your-backend-repo-url>
cd backend

# Configure application.properties or application.yml
# Set database URL, username, password, and server port

# Build the project
mvn clean install

# Run the backend server
mvn spring-boot:run

```

| Method | Endpoint             | Description                              |
| ------ | -------------------- | ---------------------------------------- |
| GET    | `/books`             | Get all books                            |
| POST   | `/books`             | Add a new book                           |
| PUT    | `/books/{id}`        | Update a book by ID                      |
| DELETE | `/books/{id}`        | Delete a book by ID (only if not rented) |
| GET    | `/rent`              | Get all rentals                          |
| POST   | `/rent`              | Rent a book                              |
| POST   | `/return/{rentalId}` | Return a rented book                     |
| PUT    | `/rentals/{id}`      | Update a rental                          |


## Assumptions & Additional Features

- Books cannot be deleted if they are currently rented.
- Rental period is fixed at 30 days.
- Toast notifications for success and error messages in the frontend.
- Frontend is responsive and styled with Tailwind CSS.
- Backend uses DTO-Entity transformers for safe data transfer.
- Search functionality supports title, author, or genre.
- Modal popups for update actions.
- Backend validation and error handling for create/update/delete operations.
- Junit 5 test cases

  ## Special
- Didn't make any constraints like QA 
