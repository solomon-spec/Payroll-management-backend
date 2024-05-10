
# Dependencies Explanation

This document provides a brief explanation of the dependencies used in the PAYROLL-MANAGEMENT-SYSTEM project.

## Hibernate ORM 6.5.0.Final

[Hibernate ORM](https://hibernate.org/orm/documentation/6.5/) is a library providing Object/Relational Mapping (ORM) support to our application. It simplifies database access by converting Java objects into a format that can be easily stored and retrieved from the database. In our project, we use Hibernate to map our Java model classes to database tables and to manage transactions.

## MySQL Connector/J 8.0.26

[MySQL Connector/J](https://dev.mysql.com/doc/connector-j/8.0/en/) is a JDBC Type 4 driver that provides database connectivity through the standard JDBC application program interfaces (APIs) available in the Java Platform, Enterprise Editions. It allows our application to interact with the MySQL database. We use this connector to establish a connection between our Spring Boot application and the MySQL database.

## Spring Boot Starter Security

[Spring Boot Starter Security](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/#boot-features-security) is a starter for using Spring Security, which provides authentication and authorization support for our application. It helps to protect our application from common security exploits like CSRF attacks. In our project, we use Spring Security to handle user authentication and authorization, ensuring that only authenticated and authorized users can access certain parts of our application.

## Spring Boot Starter Web

[Spring Boot Starter Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/#boot-features-developing-web-applications) is a starter for building web applications, including RESTful applications, using Spring MVC. It uses Tomcat as the default embedded container. In our project, we use this starter to build our RESTful APIs and to serve static and dynamic content to the client.

## Spring Boot Starter Test

[Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/#boot-features-testing) is a starter for testing Spring Boot applications with libraries including JUnit, Hamcrest and Mockito. It simplifies the setup of tests in Spring Boot applications. We use this starter to write unit tests for our application, ensuring that our code behaves as expected.

## Spring Security Test

[Spring Security Test](https://docs.spring.io/spring-security/site/docs/5.5.3/reference/html5/#test) is a library that provides support for testing Spring Security. It includes features like request post-processing for testing secured web requests and method-based security. We use this library to write tests for our security configurations, ensuring that our security rules are correctly applied.

## Spring Data JPA

[Spring Data JPA](https://spring.io/projects/spring-data-jpa) is a part of the larger Spring Data family. It makes it easy to easily implement JPA based repositories. It provides a way to reduce the boilerplate code required to implement data access layers for various persistence stores. In our project, we use Spring Data JPA to simplify the data access layer. It provides us with ready-to-use interfaces for CRUD operations, so we don't have to write these methods ourselves.

## Lombok

[Lombok](https://projectlombok.org/) is a Java library that helps to reduce boilerplate code in your application. It provides annotations that you can use to automatically generate methods like getters, setters, constructors, `toString`, `equals`, and `hashCode` methods, among others. In our project, we use Lombok to simplify our model classes by automatically generating these methods, which helps to keep our code clean and readable.