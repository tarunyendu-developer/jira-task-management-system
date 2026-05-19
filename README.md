# Jira-Like Task Management System

## Project Overview

The Jira-Like Task Management System is a REST API-based backend application developed using Spring Boot 3. This project is designed to manage users, projects, tasks, comments, and activity tracking similar to Jira.

The system supports:
- User Management
- Project Management
- Task Assignment
- Task Status Tracking
- Comments System
- Audit Trail / Activity Logging
- Pagination & Search
- Dashboard Metrics
- Overdue Task Detection

---

# Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger / OpenAPI
- Lombok
- SLF4J Logging
- Postman

---

# Project Architecture

The project follows Layered Architecture:

```text
controller  -> Handles HTTP Requests
service     -> Business Logic
repository  -> Database Operations
entity      -> Database Entities
dto         -> Request/Response Objects
exception   -> Global Exception Handling
```

---

# Features Implemented

## User Management
- Create User
- Get All Users
- Get User By ID
- Validation Handling

## Project Management
- Create Project
- Get All Projects

## Task Management
- Create Task
- Update Task
- Assign Task to User
- Task Status Management
- Get Tasks By Project
- Get Tasks By User

## Task Comments
- Add Comments to Tasks

## Activity Logging
- Tracks task status changes
- Maintains audit trail

## Pagination
- Paginated task APIs

## Search
- Search tasks by title keyword

## Dashboard Metrics
- Total Tasks
- Completed Tasks
- Pending Tasks

## Overdue Detection
- Detect overdue tasks automatically

## Project Progress
- Calculates project completion percentage

## Logging
- SLF4J logging implemented in services

## Swagger Documentation
- Live API documentation using Swagger UI

---

# Database Tables

The application contains 5 main tables:

```text
users
projects
tasks
task_comments
task_activities
```

---

# Entity Relationships

## Task Relationships
- Many Tasks -> One User
- Many Tasks -> One Project

## Comment Relationships
- Many Comments -> One Task
- Many Comments -> One User

## Activity Relationships
- Many Activities -> One Task

---

# API Endpoints

## User APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /users | Create User |
| GET | /users | Get All Users |
| GET | /users/{id} | Get User By ID |

---

## Project APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /projects | Create Project |
| GET | /projects | Get All Projects |

---

## Task APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /tasks | Create Task |
| PUT | /tasks/{id} | Update Task |
| GET | /tasks/project/{projectId} | Get Tasks By Project |
| GET | /tasks/user/{userId} | Get Tasks By User |
| GET | /tasks/project/{projectId}/paged | Pagination API |
| GET | /tasks/search?keyword= | Search Tasks |
| GET | /tasks/overdue | Get Overdue Tasks |
| GET | /tasks/dashboard | Dashboard Metrics |
| GET | /tasks/project/{projectId}/progress | Project Progress |

---

## Comment APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /tasks/{taskId}/comments | Add Comment |

---

# Validation Features

The project uses Spring Validation:
- `@NotBlank`
- `@Email`
- `@Valid`

Validation errors are handled globally using:
- GlobalExceptionHandler

---

# Exception Handling

Custom Exception:
- ResourceNotFoundException

Global Exception Handling:
- Validation Errors
- Resource Not Found
- Internal Server Errors

---

# Logging

SLF4J logging implemented for:
- UserService
- ProjectService
- TaskService

Logs include:
- Create operations
- Update operations
- Search operations
- Dashboard requests
- Activity tracking

---

# Swagger UI

Swagger automatically documents all APIs.

Access Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

---

# Setup Instructions

## 1. Clone Repository

```bash
git clone https://github.com/tarunyendu-developer/jira-task-management-system.git
```

---

## 2. Open Project

Open the project in:
- IntelliJ IDEA
  OR
- VS Code

---

## 3. Create MySQL Database

```sql
CREATE DATABASE taskmanager;
```

---

## 4. Configure application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
```

---

## 5. Run Application

```bash
mvn spring-boot:run
```

OR run directly from IntelliJ.

---

# Testing

APIs tested using:
- Swagger UI
- Postman

All APIs verified successfully.

---

# Advanced Features Implemented

- Pagination
- Search & Filtering
- Dashboard Metrics
- Overdue Detection
- Activity Logging
- Audit Trail
- Project Progress Calculation

---

# Future Improvements

- JWT Authentication
- Role-Based Authorization
- File Attachments
- Email Notifications
- Task Labels
- Real-time Notifications

---

# Author

## Tarun Yendu

Full Stack Developer  
Java | Spring Boot | React | MySQL

---

# Conclusion

This project demonstrates enterprise backend development using Spring Boot 3 with:
- Clean Architecture
- REST API Design
- Database Relationships
- Validation
- Exception Handling
- Logging
- Pagination
- Search Features
- Audit Logging

The application successfully replicates core Jira-like task management functionalities.