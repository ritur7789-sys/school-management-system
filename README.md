# School Management System

This is a backend project built using Spring Boot. It includes features for managing students and teachers in a school system.

### Features:
- CRUD operations for Students and Teachers
- Login system for both students and teachers using Spring Security
- Password encryption using BCrypt
- Redis used for caching GET APIs
- Kafka integrated for event-driven messaging when a student or teacher is created
- A simple scheduler task that runs every 30 seconds

### Tools & Technologies:
- Java 17
- Spring Boot
- PostgreSQL
- Redis
- Kafka
- Spring Security
- Maven

### APIs:
**Student APIs**
- POST /api/students
- GET /api/students
- GET /api/students/{id}
- PUT /api/students/{id}
- DELETE /api/students/{id}

**Teacher APIs**
- POST /api/teachers
- GET /api/teachers
- GET /api/teachers/{id}
- PUT /api/teachers/{id}
- DELETE /api/teachers/{id}

### How to Run:
1. Start Redis and Kafka server on your system.
2. Make sure PostgreSQL is running and database is created.
3. Run the application using:
   ```bash
   ./mvnw spring-boot:run
4. Use Postman to test the APIs. Basic Auth is enabled for security.

Notes:
The scheduler runs every 30 seconds and prints a message to the console.
When a student or teacher is created, a Kafka event is published and consumed.   
