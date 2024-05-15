package org.example.payroll_management.controller;

import org.example.payroll_management.model.Salary;
import org.example.payroll_management.service.SalaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary")
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
    @GetMapping("/all")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    /**
     * Creates a new salary record in the system.
     *
     * @param salary JSON object containing employee ID, amount, and date of payment
     * @return JSON object representing the newly created salary record
     */
    @PostMapping("")
    public Salary createSalary(@RequestBody Salary salary) {
        return salaryService.createSalary(salary);
    }

    /**
     * Retrieves the details of a salary record by its ID.
     *
     * @param id The unique identifier of the salary record
     * @return JSON object representing the salary record with the specified ID
     */
    @GetMapping("/{id}")
    public Salary getSalaryById(@PathVariable Long id) {
        return salaryService.getSalaryById(id);
    }

    /**
     * Updates an existing salary record with new data.
     *
     * @param id The unique identifier of the salary record to be updated
     * @param salary JSON object containing updated amount and date of payment
     * @return JSON object representing the updated salary record
     */
    @PutMapping("/{id}")
    public Salary updateSalary(@PathVariable Long id, @RequestBody Salary salary) {
        return salaryService.updateSalary(id, salary);
    }
    /**
     * Deletes a salary record from the system.
     *
     * @param id The unique identifier of the salary record to be deleted
     * @return No content (204) if successful
     */
    @DeleteMapping("/{id}")
    public String deleteSalary(@PathVariable Long id) {
        return salaryService.deleteSalary(id);
    }

    /**
     * Retrieves all salary records associated with a specific employee.
     *
     * @param employeeId The unique identifier of the employee
     * @return JSON array containing salary records of the employee
     */
    @GetMapping("/employee/{employeeId}")
    public List<Salary> getSalariesByEmployeeId(@PathVariable Long employeeId) {
        return salaryService.getSalariesByEmployeeId(employeeId);
    }

}
