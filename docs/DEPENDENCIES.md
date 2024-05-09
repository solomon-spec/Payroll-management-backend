# Dependencies Explanation

This document provides a brief explanation of the dependencies used in the PAYROLL-MANAGEMENT-SYSTEM project.

## Hibernate ORM 6.5.0.Final

[Hibernate ORM](https://hibernate.org/orm/documentation/6.5/) is a library providing Object/Relational Mapping (ORM) support to our application. It simplifies database access by converting Java objects into a format that can be easily stored and retrieved from the database.

## MySQL Connector/J 8.0.26

[MySQL Connector/J](https://dev.mysql.com/doc/connector-j/8.0/en/) is a JDBC Type 4 driver that provides database connectivity through the standard JDBC application program interfaces (APIs) available in the Java Platform, Enterprise Editions. It allows our application to interact with the MySQL database.

## Spring Boot Starter Security

[Spring Boot Starter Security](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/#boot-features-security) is a starter for using Spring Security, which provides authentication and authorization support for our application. It helps to protect our application from common security exploits like CSRF attacks.

## Spring Boot Starter Web

[Spring Boot Starter Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/#boot-features-developing-web-applications) is a starter for building web applications, including RESTful applications, using Spring MVC. It uses Tomcat as the default embedded container.

## Spring Boot Starter Test

[Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/#boot-features-testing) is a starter for testing Spring Boot applications with libraries including JUnit, Hamcrest and Mockito. It simplifies the setup of tests in Spring Boot applications.

## Spring Security Test

[Spring Security Test](https://docs.spring.io/spring-security/site/docs/5.5.3/reference/html5/#test) is a library that provides support for testing Spring Security. It includes features like request post-processing for testing secured web requests and method-based security.
