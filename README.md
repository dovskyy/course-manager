
# Course manager web app

The Course Management System is a web application built using Spring Boot that facilitates the management of courses, students, and teachers. The system offers user registration and login functionalities to ensure secure access for its users. Depending on the type of account (admin, teacher, or student), different sets of features are available to cater to their respective needs.
The application provides a REST API for external applications to access the system's data. 

## Features

- **User Registration and Login:** The application provides a user-friendly interface for users to register and log in with their credentials, ensuring secure access to the system.
- **Teacher Functionality:** Teachers have access to features that enable them to manage courses and students assigned to them. They can add course materials, set assignments, grade students' work, and track their progress.
- **Student Functionality:** Students can enroll in courses, access course materials, submit assignments, and view their grades. The system also provides personalized dashboards for students to track their academic progress.
- **Admin Functionality:** Administrators have privileged access to the system, allowing them to manage courses, students, and teachers. Admins can add, update, or delete courses, manage student and teacher information, and oversee the overall system operations.
- **REST API:** The application provides a REST API for external applications to access the system's data. The API supports CRUD operations for courses, students, and teachers.


## Tech Stack

**Client:** Java 11, Spring Boot, Spring MVC, Spring Web, Spring Security

**View & Frontend:** HTML & CSS, Thymeleaf, Bootstrap, jQuery 

**Testing:** JUnit 5, Mockito

**API:** REST

**Database management:** MySQL, Spring Data JPA, Hibernate

**Project management:** Maven



## Screenshots


![App Screenshot](https://github.com/dovskyy/course-manager/assets/50681454/60542365-abfb-44c4-acef-75d3e698d277)


![App Screenshot](https://github.com/dovskyy/course-manager/assets/50681454/9924689f-191e-499a-b76e-2f72677d4938)


![App Screenshot](https://github.com/dovskyy/course-manager/assets/50681454/34fe93bc-8aaf-410f-8a43-6b4a73195208)


## Example API Requests

#### Get all courses

```http
GET /api/courses/getCourses
Host: localhost:8080
```

#### Get course by id

```http
GET /api/courses/getCourseById?id=1
Host: localhost:8080
```

#### Get students from given course

```http
GET /api/courses/getStudentsFromCourse?courseId=1
Host: localhost:8080
```

#### Get teacher by email

```http
GET /api/teachers/getTeacherByEmail?teacherEmail=einstein@123.com
Host: localhost:8080
```

#### Update teacher information

```http
PUT /api/teachers/updateTeacher?id=1
Host: localhost:8080
Content-Type: application/json

{
    "name": "Albert Einstein",
    "email": "albert@gmail.com"
}
```

#### Add new course

```http
POST /api/courses/addCourse 
Host: localhost:8080
Content-Type: application/json

{
    "name": "Algebra",
    "teacherId": "3"
}
```

#### Delete course by id

```http
DELETE /api/courses/deleteCourse?id=1
Host: localhost:8080
```

Full API documentation is available at http://localhost:8080/swagger-ui.html


## Installation

- Ensure you have JDK 11 or later installed on your system.
- Clone this repository and navigate to the project directory.
- Configure the MySQL database settings in application.properties.
- Build and run the application using Maven or your preferred IDE.

Use this MySQL configuration in ```application.properties```

```
spring.datasource.url=jdbc:mysql://localhost:3306/student_manager?createDatabaseIfNotExist=true
spring.datasource.username=[username]
spring.datasource.password=[password]
spring.jpa.database=mysql
spring.jpa.hibernate.ddl-auto=update
```

Change the MySQL server address, username, and password to match your configuration.
    
