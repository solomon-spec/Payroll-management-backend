package org.example.payroll_management.controller;


import org.example.payroll_management.model.Employee;
import org.example.payroll_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // POST /api/employees
    // Create a new employee.
    // Request Body: JSON object containing employee details (firstName, lastName, email, departmentId, position, etc.).
    // Response: The created employee record with a generated `id`.
    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // GET /api/employees/{id}
    // Get details of a specific employee by ID.
    // Response: JSON object containing employee details.
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // PUT /api/employees/{id}
    // Update details of a specific employee.
    // Request Body: JSON object containing updated fields (firstName, lastName, email, departmentId, position, etc.).
    // Response: The updated employee record.
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    // DELETE /api/employees/{id}
    // Delete a specific employee by ID.
    // Response: Success message or status code indicating deletion.
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }


    // GET /api/employees
    // Get a list of all employees.
    // Response: JSON array containing details of all employees.
    @GetMapping("")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    // GET /api/employees/search.
    // Search for employees based on criteria such as name, email, position, phone number,  date of join
    // Request Parameters: Query parameters for search criteria.
    // Response: JSON array containing matching employee details.
    @GetMapping("/search")
    public List<Employee> searchEmployee(@RequestParam(required = false)  String name,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false)  String position,
                                         @RequestParam(required = false) String phoneNumber,
                                         @RequestParam(required = false) String dateOfJoiningMin,
                                         @RequestParam(required = false) String dateOfJoiningMax){
        return employeeService.searchEmployee(name, email, position, phoneNumber, dateOfJoiningMin, dateOfJoiningMax);
    }


    // GET /api/employees/on-leave
    // Get a list of employees who are currently on leave.
    // Response: JSON array containing details of employees on leave.
    @GetMapping("/on-leave")
    public List<Employee> getEmployeesOnLeave(){
        return employeeService.getEmployeesOnLeave();
    }

    // GET /api/employees/by-manager/{managerId}
    // Get a list of employees who report to a specific manager.
    // Response: JSON array containing details of employees reporting to the manager.
    @GetMapping("/by-manager/{managerId}")
    public List<Employee> getEmployeesByManager(@PathVariable Long managerId){
        return employeeService.getEmployeesByManager(managerId);
    }


    // PUT /api/employees/assign-manager/{employeeId}
    // Assign a manager to a specific employee.
    // Request Body: JSON object containing manager ID.
    // Response: Updated employee record with assigned manager.
    @PutMapping("/assign-manager/{employeeId}")
    public Employee assignManagerToEmployee(@PathVariable Long employeeId, @RequestBody Long managerId){
        return employeeService.assignManagerToEmployee(employeeId, managerId);
    }

}
