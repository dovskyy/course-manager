
# Course manager web app

The Course Management System is a web application built using Spring Boot that facilitates the management of courses, students, and teachers. The system offers user registration and login functionalities to ensure secure access for its users. Depending on the type of account (admin, teacher, or student), different sets of features are available to cater to their respective needs.



## Features

- **User Registration and Login:** The application provides a user-friendly interface for users to register and log in with their credentials, ensuring secure access to the system.
- **Teacher Functionality:** Teachers have access to features that enable them to manage courses and students assigned to them. They can add course materials, set assignments, grade students' work, and track their progress.
- **Student Functionality:** Students can enroll in courses, access course materials, submit assignments, and view their grades. The system also provides personalized dashboards for students to track their academic progress.
- **Admin Functionality:** Administrators have privileged access to the system, allowing them to manage courses, students, and teachers. Admins can add, update, or delete courses, manage student and teacher information, and oversee the overall system operations.



## Tech Stack

**Client:** Java 11, Spring Boot, Spring MVC, Spring Web, Spring Security

**View & Frontend:** HTML & CSS, Thymeleaf, Bootstrap, jQuery 

**Database managment:** MySQL, Spring Data JPA, Hibernate

**Project managment:** Maven



## Screenshots


![App Screenshot](https://github.com/dovskyy/course-manager/assets/50681454/60542365-abfb-44c4-acef-75d3e698d277)


![App Screenshot](https://github.com/dovskyy/course-manager/assets/50681454/9924689f-191e-499a-b76e-2f72677d4938)


![App Screenshot](https://github.com/dovskyy/course-manager/assets/50681454/34fe93bc-8aaf-410f-8a43-6b4a73195208)



## Installation

- Ensure you have JDK 11 or later installed on your system.
- Clone this repository and navigate to the project directory.
- Configure the MySQL database settings in application.properties.
- Build and run the application using Maven or your preferred IDE.

Use this MySQL configuration in ```application.properties```

```
spring.datasource.url=jdbc:mysql://localhost:3306/student_manager?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.database=mysql
spring.jpa.hibernate.ddl-auto=update
```
    
