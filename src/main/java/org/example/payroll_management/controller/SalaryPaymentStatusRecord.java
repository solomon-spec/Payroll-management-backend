package org.example.payroll_management.controller;

import org.example.payroll_management.model.SalaryPaymentStatus;
import org.example.payroll_management.service.SalaryPaymentStatusService;
import org.example.payroll_management.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/salary-status")
public class SalaryPaymentStatusRecord {
    private final SalaryPaymentStatusService salaryPaymentStatusService;

    @Autowired
    public SalaryPaymentStatusRecord(SalaryPaymentStatusService salaryPaymentStatusService){
        this.salaryPaymentStatusService = salaryPaymentStatusService;

    }
    /**
     * Retrieves all salary payment statuses for a specific employee.
     *
     * @param employeeId The unique identifier of the employee
     * @return JSON array containing salary payment statuses for the specified employee
     */
    @GetMapping("/employee/{employeeId}")
    public List<SalaryPaymentStatus> getEmployeeSalaryStatus(@PathVariable Long employeeId){
        return salaryPaymentStatusService.findByEmployeeId(employeeId);
    }
    /**
     * Retrieves all salary payment statuses within a specified date range.
     *
     * @param startDate The start date of the date range
     * @param endDate   The end date of the date range
     * @return JSON array containing salary payment statuses within the specified date range
     */
    @GetMapping("/time")
    public List<SalaryPaymentStatus> getSalaryBetweenStartDateAndEndDate(@RequestParam LocalDate startDate, LocalDate endDate){

        return salaryPaymentStatusService.findByStartAndEndDate(startDate, endDate);
    }

    /**
     * Retrieves all salary payment statuses with a specific status (e.g., paid, pending).
     *
     * @param status The status of the salary payment (e.g., paid, pending)
     * @return JSON array containing salary payment statuses with the specified status
     */
    @GetMapping("/status")
     public List<SalaryPaymentStatus> getSalaryPaymentStatusByStatus(@RequestParam String status){

        return salaryPaymentStatusService.findByStatus(status);
    }

    /**
     * Creates a new salary payment status record.
     *
     * @param salaryPaymentStatus The salary payment status object to be created
     * @return JSON object representing the newly created salary payment status record
     */
    @PostMapping("/{id}")
    public SalaryPaymentStatus updateSalaryPaymentStatus(@PathVariable Long id, @RequestBody SalaryPaymentStatus salaryPaymentStatus){
        return salaryPaymentStatusService.update(id, salaryPaymentStatus);
    }



    /**
     * Updates an existing salary payment status record.
     *
     * @param id   The unique identifier of the salary payment status record to be updated
     * @param salaryPaymentStatus The salary payment status object with updated details
     * @return JSON object representing the updated salary payment status record
     */
    public SalaryPaymentStatus createSalaryPaymentStatus(@RequestBody SalaryPaymentStatus salaryPaymentStatus){
        return salaryPaymentStatusService.save(salaryPaymentStatus);
    }



}
