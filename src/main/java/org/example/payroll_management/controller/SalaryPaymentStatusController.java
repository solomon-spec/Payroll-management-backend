package org.example.payroll_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.payroll_management.dto.SalaryPaymentStatusDTO;
import org.example.payroll_management.service.SalaryPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/salary-status")
@Tag(name = "Salary Payment Status Controller", description = "Controller for managing salary payment status records")
public class SalaryPaymentStatusController{
    private final SalaryPaymentStatusService salaryPaymentStatusService;

    @Autowired
    public SalaryPaymentStatusController(SalaryPaymentStatusService salaryPaymentStatusService){
        this.salaryPaymentStatusService = salaryPaymentStatusService;

    }
    /**
     * Retrieves all salary payment statuses for a specific employee.
     *
     * @param employeeId The unique identifier of the employee
     * @return JSON array containing salary payment statuses for the specified employee
     */
    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get salary payment statuses by employee ID", description = "Retrieves all salary payment statuses for a specific employee")
    public ResponseEntity<List<SalaryPaymentStatusDTO>> getEmployeeSalaryStatus(@PathVariable Long employeeId){
        List<SalaryPaymentStatusDTO> records = salaryPaymentStatusService.findByEmployeeId(employeeId);
        return ResponseEntity.ok(records);
    }
    /**
     * Retrieves all salary payment statuses within a specified date range.
     *
     * @param startDate The start date of the date range
     * @param endDate   The end date of the date range
     * @return JSON array containing salary payment statuses within the specified date range
     */
    @GetMapping("/time")
    @Operation(summary = "Get salary payment statuses by date range", description = "Retrieves all salary payment statuses within a specified date range")
    public ResponseEntity<List<SalaryPaymentStatusDTO>> getSalaryBetweenStartDateAndEndDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        List<SalaryPaymentStatusDTO> records = salaryPaymentStatusService.findByStartAndEndDate(startDate, endDate);
        return ResponseEntity.ok(records);
    }

    /**
     * Retrieves all salary payment statuses with a specific status (e.g., paid, pending).
     *
     * @param status The status of the salary payment (e.g., paid, pending)
     * @return JSON array containing salary payment statuses with the specified status
     */
    @GetMapping("/status")
    @Operation(summary = "Get salary payment statuses by status", description = "Retrieves all salary payment statuses with a specific status (e.g., paid, pending)")
    public ResponseEntity<List<SalaryPaymentStatusDTO>> getSalaryPaymentStatusByStatus(@RequestParam String status){
        List<SalaryPaymentStatusDTO> records = salaryPaymentStatusService.findByStatus(status);
        return ResponseEntity.ok(records);
    }

    /**
     * Creates a new salary payment status record.
     *
     * @param salaryPaymentStatusDTO The salary payment status object to be created
     * @return JSON object representing the newly created salary payment status record
     */
    @PostMapping("/")
    @Operation(summary = "Create a salary payment status", description = "Creates a new salary payment status record")
    public ResponseEntity<SalaryPaymentStatusDTO> createSalaryPaymentStatus(@RequestBody SalaryPaymentStatusDTO salaryPaymentStatusDTO){
        SalaryPaymentStatusDTO createdRecord = salaryPaymentStatusService.save(salaryPaymentStatusDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }



    /**
     * Updates an existing salary payment status record.
     *
     * @param id   The unique identifier of the salary payment status record to be updated
     * @param salaryPaymentStatusDTO The salary payment status object with updated details
     * @return JSON object representing the updated salary payment status record
     */

    @PutMapping("/{id}")
    @Operation(summary = "Update a salary payment status", description = "Updates an existing salary payment status record")
    public ResponseEntity<SalaryPaymentStatusDTO> updateSalaryPaymentStatus(@PathVariable Long id, @RequestBody SalaryPaymentStatusDTO salaryPaymentStatusDTO){
        SalaryPaymentStatusDTO updatedRecord = salaryPaymentStatusService.update(id, salaryPaymentStatusDTO);
        return ResponseEntity.ok(updatedRecord);
    }




}
