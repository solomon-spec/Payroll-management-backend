package org.example.payroll_management.controller;

import org.example.payroll_management.dto.AttendancePolicyDTO;
import org.example.payroll_management.service.AttendancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance-policy")
public class AttendancePolicyController {


    AttendancePolicyService attendancePolicyService;

    @Autowired
    public AttendancePolicyController(AttendancePolicyService attendancePolicyService) {
        this.attendancePolicyService = attendancePolicyService;
    }


    /**
     * Creates a new attendance policy in the system.
     *
     * @param attendancePolicy JSON object containing name, description, lateThreshold, absenceThreshold, and earlyLeaveThreshold
     * @return JSON object representing the newly created attendance policy
     */
    @PostMapping("")
    public ResponseEntity<AttendancePolicyDTO> createAttendancePolicy(@RequestBody AttendancePolicyDTO attendancePolicy) {
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
    public ResponseEntity<AttendancePolicyDTO> updateAttendancePolicy(@PathVariable Long id, @RequestBody AttendancePolicyDTO attendancePolicy) {
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
    public ResponseEntity<Void> deleteAttendancePolicy(@PathVariable Long id) {
        attendancePolicyService.deleteAttendancePolicy(id);
        return ResponseEntity.noContent().build();


    }
}

