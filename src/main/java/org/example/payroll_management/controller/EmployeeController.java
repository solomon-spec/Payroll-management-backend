package org.example.payroll_management.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.payroll_management.dto.EmployeeDTO;
import org.example.payroll_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Tag(name = "Employee Controller", description = "Controller for managing employee records")
@SecurityRequirement(name = "payroll-management")
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
    @Operation(summary = "Create a new employee", description = "Creates a new employee record in the system")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employee) {
        System.out.println(employee);
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    // GET /api/employees/{id}
    // Get details of a specific employee by ID.
    // Response: JSON object containing employee details.
    @GetMapping("/{id}")
    @Operation(summary = "Get an employee by ID", description = "Retrieves the details of an employee record by its ID")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // PUT /api/employees/{id}
    // Update details of a specific employee.
    // Request Body: JSON object containing updated fields (firstName, lastName, email, departmentId, position, etc.).
    // Response: The updated employee record.
    @PutMapping("/{id}")
    @Operation(summary = "Update an employee", description = "Updates an existing employee record with new data")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employee) {
        employee.setId(id);
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    // DELETE /api/employees/{id}
    // Delete a specific employee by ID.
    // Response: Success message or status code indicating deletion.
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee", description = "Deletes an employee record from the system")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }


    // GET /api/employees
    // Get a list of all employees.
    // Response: JSON array containing details of all employees.
    @GetMapping("/all")
    @Operation(summary = "Get all employees", description = "Retrieves all employee records in the system")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }
    // GET /api/employees/search.
    // Search for employees based on criteria such as name, email, position, phone number,  date of join
    // Request Parameters: Query parameters for search criteria.
    // Response: JSON array containing matching employee details.
    @GetMapping("/search")
    @Operation(summary = "Search for employees", description = "Searches for employees based on criteria such as name, email, position, phone number, date of joining")
    public ResponseEntity<List<EmployeeDTO>> searchEmployee(@RequestParam(required = false)  String name,
                                                            @RequestParam(required = false) String email,
                                                            @RequestParam(required = false)  String position,
                                                            @RequestParam(required = false) String phoneNumber,
                                                            @RequestParam(required = false) String dateOfJoiningMin,
                                                            @RequestParam(required = false) String dateOfJoiningMax){
        return ResponseEntity.ok(employeeService.searchEmployee(name, email, position, phoneNumber, dateOfJoiningMin, dateOfJoiningMax));
    }


    // GET /api/employees/on-leave
    // Get a list of employees who are currently on leave.
    // Response: JSON array containing details of employees on leave.
    @GetMapping("/on-leave")
    @Operation(summary = "Get employees on leave", description = "Retrieves all employees who are currently on leave")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesOnLeave(){
        return ResponseEntity.ok(employeeService.getEmployeesOnLeave());
    }

    // GET /api/employees/by-manager/{managerId}
    // Get a list of employees who report to a specific manager.
    // Response: JSON array containing details of employees reporting to the manager.
    @GetMapping("/by-manager/{managerId}")
    @Operation(summary = "Get employees on leave", description = "Retrieves all employees who are currently on leave")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByManager(@PathVariable Long managerId){
        return ResponseEntity.ok(employeeService.getEmployeesByManager(managerId));
    }


    // PUT /api/employees/assign-manager/{employeeId}
    // Assign a manager to a specific employee.
    // Request Body: JSON object containing manager ID.
    // Response: Updated employee record with assigned manager.
    @PutMapping("/assign-manager/{employeeId}")
    @Operation(summary = "Assign a manager to an employee", description = "Assigns a manager to a specific employee")
    public ResponseEntity<EmployeeDTO> assignManagerToEmployee(@PathVariable Long employeeId, @RequestBody Long managerId){
        return ResponseEntity.ok(employeeService.assignManagerToEmployee(employeeId, managerId));
    }

}
