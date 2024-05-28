package org.example.payroll_management.service;

import org.example.payroll_management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("webSecurity")
public class WebSecurity {

    private final EmployeeService employeeService;
    @Autowired
   public WebSecurity(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public boolean checkUserId(Authentication authentication, String employeeId) {
        // Get the current user's email from the Authentication object
        String currentUserEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

        Employee employee = employeeService.getEmployeeByEmail(currentUserEmail);


        return employee != null && employee.getId().equals(Long.parseLong(employeeId));
    }
}