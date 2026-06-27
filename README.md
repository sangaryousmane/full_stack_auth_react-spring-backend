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
* PostgreSQL database support
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

AetherERP Authentication API Documentation
Base URL
```text
http://localhost:8080
```
This document explains the available authentication and profile endpoints and how to test them using Postman.
---
1. Register User
   Endpoint
```http
POST /register
```
Request
```json
{
  "name": "Ousmane Sangary",
  "email": "ousmane@gmail.com",
  "password": "password123"
}
```
Response
```json
{
  "userId": "generated-uuid",
  "name": "Ousmane Sangary",
  "email": "ousmane@gmail.com",
  "isAccountVerified": false
}
```
What happens:
- Validates request
- Encrypts password
- Stores user
- Sends welcome email
---
2. Login
   Endpoint
```http
POST /login
```
Request
```json
{
  "email": "ousmane@gmail.com",
  "password": "password123"
}
```
Response
```json
{
  "email": "ousmane@gmail.com",
  "token": "JWT_TOKEN"
}
```
What happens:
- Authenticate user
- Load user details
- Generate JWT
- Set HTTP-only cookie
- Return JWT token
---
3. Get Profile
   Endpoint
```http
GET /profile
```
Authorization
```http
Authorization: Bearer JWT_TOKEN
```
Response
```json
{
  "userId": "generated-uuid",
  "name": "Ousmane Sangary",
  "email": "ousmane@gmail.com",
  "isAccountVerified": false
}
```
---
4. Check Authentication
   Endpoint
```http
GET /is-authenticated
```
Response
```json
true
```
---
5. Send Reset OTP
   Endpoint
```http
POST /send-reset-otp?email=user@gmail.com
```
No request body.

What happens:
- Generate OTP
- Save OTP
- Save expiration time
- Send email
---
6. Reset Password
   Endpoint
```http
POST /reset-password
```
Request
```json
{
  "email": "ousmane@gmail.com",
  "otp": "482913",
  "newPassword": "newpassword123"
}
```
What happens:
- Validate OTP 
- Verify expiration 
- Encrypt new password 
- Update account
---
7. Send Verification OTP
   Endpoint
```http
POST /send-otp
```
Authorization
```http
Authorization: Bearer JWT_TOKEN
```
What happens:
- Generate verification OTP 
- Store OTP 
- Send email
---
8. Verify Email
   Endpoint
```http
POST /verify-otp
```
Request
```json
{
  "otp": "981236"
}
```
What happens:
- Validate OTP 
- Verify expiration 
- Activate account
---
9. Test Endpoint
   Endpoint
```http
GET /test
```
Response
```text
Auth is working
```
---
Postman Testing Flow 
1. Register 
2. Login 
3. Copy JWT and Add Bearer Token 
4. Test `/profile`
5. Test `/is-authenticated`
6. Send OTP 
7. Verify OTP 
8. Reset password
---

## Endpoint Summary

| Method | Endpoint | Auth Required |
|---------|----------|---------------|
| POST | /register | No |
| POST | /login | No |
| GET | /profile | Yes |
| GET | /is-authenticated | Yes |
| POST | /send-reset-otp | No |
| POST | /reset-password | No |
| POST | /send-otp | Yes |
| POST | /verify-otp | Yes |
| GET | /test | Yes |

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
