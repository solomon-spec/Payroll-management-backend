package org.example.payroll_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.payroll_management.dto.SalaryDTO;
import org.example.payroll_management.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
@Tag(name = "Salary Controller", description = "Controller for managing salary records")
@SecurityRequirement(name = "payroll-management")
public class SalaryController {
    public final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    /**
     * Retrieves all salary records in the system.
     *
     * @return JSON array containing all salary records
     */
    @GetMapping("")
    @Operation(summary = "Get all salaries", description = "Retrieves all salary records in the system")
    public ResponseEntity<List<SalaryDTO>> getAllSalaries() {
        List<SalaryDTO> salaries = salaryService.getAllSalaries();
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

    /**
     * Creates a new salary record in the system.
     *
     * @param salary JSON object containing employee ID, amount, and date of payment
     * @return JSON object representing the newly created salary record
     */
    @PostMapping("")
    @Operation(summary = "Create a salary", description = "Creates a new salary record in the system")
    public ResponseEntity<SalaryDTO> createSalary(@RequestBody SalaryDTO salary) {
        SalaryDTO createdSalary = salaryService.createSalary(salary);
        return new ResponseEntity<>(createdSalary, HttpStatus.CREATED);
    }

    /**
     * Retrieves the details of a salary record by its ID.
     *
     * @param id The unique identifier of the salary record
     * @return JSON object representing the salary record with the specified ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a salary by ID", description = "Retrieves the details of a salary record by its ID")
    public ResponseEntity<SalaryDTO> getSalaryById(@PathVariable Long id) {
        SalaryDTO salary = salaryService.getSalaryById(id);
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    /**
     * Updates an existing salary record with new data.
     *
     * @param id The unique identifier of the salary record to be updated
     * @param salary JSON object containing updated amount and date of payment
     * @return JSON object representing the updated salary record
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a salary", description = "Updates an existing salary record with new data")
    public ResponseEntity<SalaryDTO> updateSalary(@PathVariable Long id, @RequestBody SalaryDTO salary) {
        SalaryDTO updatedSalary = salaryService.updateSalary(id, salary);
        return new ResponseEntity<>(updatedSalary, HttpStatus.OK);
    }
    /**
     * Deletes a salary record from the system.
     *
     * @param id The unique identifier of the salary record to be deleted
     * @return No content (204) if successful
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a salary", description = "Deletes a salary record from the system")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        salaryService.deleteSalary(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves all salary records associated with a specific employee.
     *
     * @param employeeId The unique identifier of the employee
     * @return JSON array containing salary records of the employee
     */
    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get salaries by employee ID", description = "Retrieves all salary records associated with a specific employee")
    public ResponseEntity<List<SalaryDTO>> getSalariesByEmployeeId(@PathVariable Long employeeId) {
        List<SalaryDTO> salaries = salaryService.getSalariesByEmployeeId(employeeId);
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }

}
