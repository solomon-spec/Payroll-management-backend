package org.example.payroll_management;

import org.example.payroll_management.model.Employee;
import org.example.payroll_management.service.AuthenticationService;
import org.example.payroll_management.service.JwtService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PayrollManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayrollManagementSystemApplication.class, args);
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.getTransaction().commit();
        session.close();
    }

}
