package org.example.payroll_management.controller;

import org.example.payroll_management.model.Leave;
import org.example.payroll_management.model.LeaveStatus;
import org.example.payroll_management.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    // GET /api/leave
    // Get all leave records.
    // Response: JSON array containing all leave records.
    @GetMapping("/all")
    public List<Leave> getAllLeaveRecords() {
        return leaveService.getAllLeaveRecords();
    }
    // POST /api/leave
    // Create a new leave record for an employee.
    // Request Body: JSON object containing details such as `employeeId`, `startDate`, `endDate`, `type`.
    // Response: The created leave record with a generated `id`.
    @PostMapping("")
    public Leave createLeaveRecord(Leave leave) {
        return leaveService.createLeaveRecord(leave);
    }

    // GET /api/leave/{id}
    // Get details of a specific leave record by its ID.
    // Response: JSON object containing leave record details.
    @GetMapping("/{id}")
    public Leave getLeaveRecordById(@PathVariable Long id) {
        return leaveService.getLeaveRecordById(id);
    }

    // PUT /api/leave/{id}
    // Update details of a specific leave record.
    // Request Body: JSON object containing updated fields such as `startDate`, `endDate`, `type`.
    // Response: The updated leave record.
    @PutMapping("/{id}")
    public Leave updateLeaveRecord(@PathVariable Long id,@RequestBody Leave leave) {
        return leaveService.updateLeaveRecord(id, leave);
    }

    // DELETE /api/leave/{id}
    // Delete a specific leave record by its ID.
    // Response: Success message or status code indicating deletion.
    @DeleteMapping("/{id}")
    public String deleteLeaveRecord(@PathVariable Long id) {
        return leaveService.deleteLeaveRecord(id);
    }

    // GET /api/leave/employee/{employeeId}
    // Get all leave records for a specific employee.
    // Response: JSON array containing leave records associated with the employee.
    @GetMapping("/employee/{employeeId}")
    public List<Leave> getLeaveRecordsByEmployeeId(@PathVariable Long employeeId) {
        return leaveService.getLeaveRecordsByEmployeeId(employeeId);
    }

    // PUT /api/leave/{id}/cancel.
    // Cancel a pending or approved leave request.
    // Request Body: Optional, depending on your system's logic (e.g., reason for cancellation).
    // Response: Updated leave record with status indicating cancellation.
    @PutMapping("/{id}/cancel")
    public Leave cancelLeaveRequest(@PathVariable Long id) {
        return leaveService.cancelLeaveRequest(id);
    }

    // PUT /api/leave/{id}/approve.
    // Approve a pending leave request.
    // Request Body: Optional, depending on your system's logic (e.g., reason for approval).
    // Response: Updated leave record with status indicating approval.
    @PutMapping("/{id}/approve")
    public Leave approveLeaveRequest(@PathVariable Long id) {
        return leaveService.changeLeaveStatus(id, LeaveStatus.APPROVED);
    }

    // PUT /api/leave/{id}/reject.
    // Reject a pending leave request.
    // Request Body: Optional, (e.g., reason for rejection).
    // Response: Updated leave record with status indicating rejection.
    @PutMapping("/{id}/reject")
    public Leave rejectLeaveRequest(@PathVariable Long id) {
        return leaveService.changeLeaveStatus(id, LeaveStatus.REJECTED);
    }

    // GET /api/leave/balance/{employeeId}
    // Get the remaining leave balance for an employee.
    // Response: JSON object containing leave balance details (e.g., remaining sick leave, vacation leave).
    // Note: The leave balance can be calculated based on the employee's leave policy and existing leave records.
    @GetMapping("/balance/{employeeId}")
    public int getLeaveBalance(@PathVariable Long employeeId) {
        return leaveService.getLeaveBalance(employeeId);
    }



    // GET /api/leave/search.
    // Search for leave records based on criteria such as employee ID, status, date range, etc.
    // Request Parameters: Query parameters for search criteria.
    // Response: JSON array containing matching leave records.
    @GetMapping("/search")
    public List<Leave> searchLeaves(@RequestParam(required = false) Long employeeId,
                                    @RequestParam(required = false) LeaveStatus status,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return leaveService.searchLeave(employeeId,startDate, endDate, status);
    }

}
