package org.example.payroll_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.payroll_management.dto.LeaveDTO;
import org.example.payroll_management.model.LeaveStatus;
import org.example.payroll_management.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leave")
@Tag(name = "Leave Controller", description = "Controller for managing leave records")
@SecurityRequirement(name = "payroll-management")
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
    @Operation(summary = "Get all leave records", description = "Retrieves all leave records in the system")
    public ResponseEntity<List<LeaveDTO>> getAllLeaveRecords() {
        List<LeaveDTO> records = leaveService.getAllLeaveRecords();
        return ResponseEntity.ok(records);
    }
    // POST /api/leave
    // Create a new leave record for an employee.
    // Request Body: JSON object containing details such as `employeeId`, `startDate`, `endDate`, `type`.
    // Response: The created leave record with a generated `id`.
    @PostMapping("")
    @Operation(summary = "Create a leave record", description = "Creates a new leave record for an employee")
    public ResponseEntity<LeaveDTO> createLeaveRecord(@RequestBody LeaveDTO leaveDTO) {
        LeaveDTO createdRecord = leaveService.createLeaveRecord(leaveDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    // GET /api/leave/{id}
    // Get details of a specific leave record by its ID.
    // Response: JSON object containing leave record details.
    @GetMapping("/{id}")
    @Operation(summary = "Get a leave record by ID", description = "Retrieves the details of a specific leave record by its ID")
    public ResponseEntity<LeaveDTO> getLeaveRecordById(@PathVariable Long id) {
        LeaveDTO record = leaveService.getLeaveRecordById(id);
        return ResponseEntity.ok(record);
    }

    // PUT /api/leave/{id}
    // Update details of a specific leave record.
    // Request Body: JSON object containing updated fields such as `startDate`, `endDate`, `type`.
    // Response: The updated leave record.
    @PutMapping("/{id}")
    @Operation(summary = "Update a leave record", description = "Updates the details of a specific leave record")
    public ResponseEntity<LeaveDTO> updateLeaveRecord(@PathVariable Long id, @RequestBody LeaveDTO leaveDTO) {
        LeaveDTO updatedRecord = leaveService.updateLeaveRecord(id, leaveDTO);
        return ResponseEntity.ok(updatedRecord);
    }

    // DELETE /api/leave/{id}
    // Delete a specific leave record by its ID.
    // Response: Success message or status code indicating deletion.
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a leave record", description = "Deletes a specific leave record by its ID")
    public ResponseEntity<Void> deleteLeaveRecord(@PathVariable Long id) {
        leaveService.deleteLeaveRecord(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/leave/employee/{employeeId}
    // Get all leave records for a specific employee.
    // Response: JSON array containing leave records associated with the employee.
    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get leave records by employee ID", description = "Retrieves all leave records for a specific employee")
    public ResponseEntity<List<LeaveDTO>> getLeaveRecordsByEmployeeId(@PathVariable Long employeeId) {
        List<LeaveDTO> records = leaveService.getLeaveRecordsByEmployeeId(employeeId);
        return ResponseEntity.ok(records);
    }

    // PUT /api/leave/{id}/cancel.
    // Cancel a pending or approved leave request.
    // Request Body: Optional, depending on your system's logic (e.g., reason for cancellation).
    // Response: Updated leave record with status indicating cancellation.
    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel a leave request", description = "Cancels a pending or approved leave request")
    public ResponseEntity<LeaveDTO> cancelLeaveRequest(@PathVariable Long id) {
        LeaveDTO updatedRecord = leaveService.cancelLeaveRequest(id);
        return ResponseEntity.ok(updatedRecord);
    }

    // PUT /api/leave/{id}/approve.
    // Approve a pending leave request.
    // Request Body: Optional, depending on your system's logic (e.g., reason for approval).
    // Response: Updated leave record with status indicating approval.
    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve a leave request", description = "Approves a pending leave request")
    public ResponseEntity<LeaveDTO> approveLeaveRequest(@PathVariable Long id) {
        LeaveDTO updatedRecord = leaveService.changeLeaveStatus(id, LeaveStatus.APPROVED);
        return ResponseEntity.ok(updatedRecord);
    }

    // PUT /api/leave/{id}/reject.
    // Reject a pending leave request.
    // Request Body: Optional, (e.g., reason for rejection).
    // Response: Updated leave record with status indicating rejection.
    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject a leave request", description = "Rejects a pending leave request")
    public ResponseEntity<LeaveDTO> rejectLeaveRequest(@PathVariable Long id) {
        LeaveDTO updatedRecord = leaveService.changeLeaveStatus(id, LeaveStatus.REJECTED);
        return ResponseEntity.ok(updatedRecord);
    }

    // GET /api/leave/balance/{employeeId}
    // Get the remaining leave balance for an employee.
    // Response: JSON object containing leave balance details (e.g., remaining sick leave, vacation leave).
    // Note: The leave balance can be calculated based on the employee's leave policy and existing leave records.
    @GetMapping("/balance/{employeeId}")
    @Operation(summary = "Get leave balance", description = "Retrieves the remaining leave balance for an employee")
    public int getLeaveBalance(@PathVariable Long employeeId) {
        return leaveService.getLeaveBalance(employeeId);
    }



    // GET /api/leave/search.
    // Search for leave records based on criteria such as employee ID, status, date range, etc.
    // Request Parameters: Query parameters for search criteria.
    // Response: JSON array containing matching leave records.
    @GetMapping("/search")
    @Operation(summary = "Search for leave records", description = "Searches for leave records based on criteria such as employee ID, status, date range, etc.")
    public ResponseEntity<List<LeaveDTO>> searchLeaves(@RequestParam(required = false) Long employeeId,
                                                       @RequestParam(required = false) LeaveStatus status,
                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<LeaveDTO> records = leaveService.searchLeave(employeeId, startDate, endDate, status);
        return ResponseEntity.ok(records);
    }

}
