# Full Stack Authentication System

A modern full-stack authentication system built with Spring Boot for the backend and React.js for the frontend.

This project demonstrates a secure authentication workflow using JWT (JSON Web Tokens), role-based authorization, protected routes, and RESTful API communication between the frontend and backend.

---

# Features

## Backend (Spring Boot)

* Spring Security integration
* JWT authentication and authorization
* User registration and login
* Password encryption using BCrypt
* Role-based access control (RBAC)
* REST API architecture
* CORS configuration
* Exception handling
* MySQL/PostgreSQL database support
* Maven project structure

## Frontend (React)

* React functional components
* Authentication state management
* Protected routes
* Login and registration pages
* Axios API integration
* Local storage token management
* Responsive UI

---

# Tech Stack

## Backend

* Java
* Spring Boot
* Spring Security
* JWT
* Hibernate / JPA
* Maven
* MySQL or PostgreSQL

## Frontend

* React.js
* Axios
* React Router
* Tailwind CSS / CSS

---

# Project Structure

```bash
full_stack_auth_system/
│
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── application.properties
│
├── frontend/
│   ├── src/
│   ├── package.json
│   └── public/
│
├── .gitignore
└── README.md
```

---

# Installation and Setup

## 1. Clone the Repository

```bash
git clone https://github.com/sangaryousmane/full_stack_auth_system.git
cd full_stack_auth_system
```

---

# Backend Setup


## Run Backend

```bash
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

# Frontend Setup

## Navigate to Frontend

```bash
cd frontend
```

## Install Dependencies

```bash
npm install
```

## Start Frontend

```bash
npm start
```

Frontend runs on:

```text
http://localhost:3000
```

---

# API Endpoints

## Authentication

| Method | Endpoint           | Description       |
| ------ | ------------------ | ----------------- |
| POST   | /api/auth/register | Register new user |
| POST   | /api/auth/login    | Authenticate user |

## User

| Method | Endpoint          | Description            |
| ------ | ----------------- | ---------------------- |
| GET    | /api/user/profile | Get authenticated user |

---

# JWT Authentication Flow

1. User registers or logs in.
2. Backend validates credentials.
3. JWT token is generated.
4. Frontend stores token.
5. Token is sent in Authorization header.
6. Backend validates token before granting access.

---

# Environment Variables

Example frontend `.env`:

```env
REACT_APP_API_URL=http://localhost:8080/api
```

---

# Hide Files Starting With '.'

Files starting with `.` are hidden files in Linux/macOS and Git projects.
Examples:

```text
.gitignore
.env
.idea
.vscode
```

## To Ignore Them in Git

Use `.gitignore`:

```gitignore
.env
.idea/
.vscode/
node_modules/
target/
```

## To View Hidden Files

### Windows

* Open File Explorer
* Click "View"
* Enable "Hidden Items"

### Linux/macOS

```bash
ls -la
```

---

# Recommended .gitignore

```gitignore
# Node
node_modules/

# React
build/

# Java
*.class
*.jar
target/

# IDE
.idea/
.vscode/

# Environment
.env

# OS Files
.DS_Store
Thumbs.db
```

---

# Security Notes

* Never commit `.env` files.
* Never expose JWT secret keys.
* Use HTTPS in production.
* Store passwords using BCrypt.
* Configure proper CORS policies.

---

# Future Improvements

* Refresh token implementation
* OAuth2 login
* Email verification
* Password reset feature
* Docker deployment
* CI/CD pipeline
* Redis session management

---

# Author

Created by Ousmane Sangary.

GitHub Repository:

[https://github.com/sangaryousmane/full_stack_auth_system](https://github.com/sangaryousmane/full_stack_auth_system)
