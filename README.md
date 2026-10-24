# Student Management System

A web-based **Student Management System** built with Spring Boot. It provides a secure dashboard to manage students, courses, and enrollments in one place.

## Live Demo

[Open the deployed application](https://springboot-student-management-api-4.onrender.com/)

## Demo Credentials

| Field | Value |
|---|---|
| Username | `Admin` |
| Password | `admin@123` |

## Features

- Secure admin login with Spring Security
- Dashboard for managing academic records
- Add, view, edit, and list students
- Add, view, edit, and list courses
- Create and manage student enrollments
- Form validation and error handling
- Pagination support for listing records
- Responsive UI using Thymeleaf templates

## Tech Stack

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- Maven
- Docker

## Project Structure

```text
src/
├── main/
│   ├── java/com/ojasx/StudentManagement/
│   │   ├── Controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   ├── dto/
│   │   ├── config/
│   │   └── exception/
│   └── resources/
│       ├── templates/
│       ├── static/
│       └── application.properties
└── test/
