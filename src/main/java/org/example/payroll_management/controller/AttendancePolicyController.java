package org.example.payroll_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.payroll_management.dto.AttendancePolicyDTO;
import org.example.payroll_management.service.AttendancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance-policy")
@Tag(name = "Attendance Policy Controller", description = "Controller for managing attendance policies")
public class AttendancePolicyController {

    AttendancePolicyService attendancePolicyService;

    @Autowired
    public AttendancePolicyController(AttendancePolicyService attendancePolicyService) {
        this.attendancePolicyService = attendancePolicyService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all attendance policies", description = "Retrieves all attendance policies in the system")
    public ResponseEntity<List<AttendancePolicyDTO>> getAllAttendancePolicies() {
        List<AttendancePolicyDTO> allPolicies = attendancePolicyService.getAllAttendancePolicy();
        return ResponseEntity.ok(allPolicies);
    }

    /**
     * Creates a new attendance policy in the system.
     *
     * @param attendancePolicy JSON object containing name, description, lateThreshold, absenceThreshold, and earlyLeaveThreshold
     * @return JSON object representing the newly created attendance policy
     */

    @PostMapping("")
    @Operation(summary = "Create a new attendance policy", description = "Creates a new attendance policy in the system")
    public ResponseEntity<AttendancePolicyDTO> createAttendancePolicy(@Valid @RequestBody AttendancePolicyDTO attendancePolicy) {
        AttendancePolicyDTO createdAttendancePolicy = attendancePolicyService.createAttendancePolicy(attendancePolicy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttendancePolicy);
    }

    /**
     * Retrieves the details of an attendance policy by its ID.
     *
     * @param id The unique identifier of the attendance policy
     * @return JSON object representing the attendance policy with the specified ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an attendance policy by ID", description = "Retrieves the details of an attendance policy by its ID")
    public ResponseEntity<AttendancePolicyDTO> getAttendancePolicyById(@PathVariable Long id) {
        AttendancePolicyDTO attendancePolicy = attendancePolicyService.getAttendancePolicyById(id);
        if (attendancePolicy == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(attendancePolicy);
    }

    /**
     * Updates an existing attendance policy with new data.
     *
     * @param id  The unique identifier of the attendance policy to be updated
     * @param attendancePolicy JSON object containing updated name, description, lateThreshold, absenceThreshold, and earlyLeaveThreshold
     * @return JSON object representing the updated attendance policy
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an attendance policy", description = "Updates an existing attendance policy with new data")
    public ResponseEntity<AttendancePolicyDTO> updateAttendancePolicy(@PathVariable Long id,@Valid @RequestBody AttendancePolicyDTO attendancePolicy) {
        AttendancePolicyDTO updatedAttendancePolicy = attendancePolicyService.updateAttendancePolicy(id, attendancePolicy);
        if (updatedAttendancePolicy == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedAttendancePolicy);
    }

    /**
     * Deletes an attendance policy from the system.
     *
     * @param id The unique identifier of the attendance policy to be deleted
     * @return No content (204) if successful
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an attendance policy", description = "Deletes an attendance policy from the system")
    public ResponseEntity<Void> deleteAttendancePolicy(@PathVariable Long id) {
        attendancePolicyService.deleteAttendancePolicy(id);
        return ResponseEntity.noContent().build();


    }
}

