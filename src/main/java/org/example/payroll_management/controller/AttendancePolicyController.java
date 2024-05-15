package org.example.payroll_management.controller;

import org.example.payroll_management.model.AttendancePolicy;
import org.example.payroll_management.service.AttendancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AttendancePolicy createAttendancePolicy(@RequestBody AttendancePolicy attendancePolicy) {
        return attendancePolicyService.createAttendancePolicy(attendancePolicy);
    }

    /**
     * Retrieves the details of an attendance policy by its ID.
     *
     * @param id The unique identifier of the attendance policy
     * @return JSON object representing the attendance policy with the specified ID
     */
    @GetMapping("/{id}")
    public AttendancePolicy getAttendancePolicyById(@PathVariable Long id) {
        return attendancePolicyService.getAttendancePolicyById(id);
    }

    /**
     * Updates an existing attendance policy with new data.
     *
     * @param id The unique identifier of the attendance policy to be updated
     * @param attendancePolicy JSON object containing updated name, description, lateThreshold, absenceThreshold, and earlyLeaveThreshold
     * @return JSON object representing the updated attendance policy
     */
    @PutMapping("/{id}")
    public AttendancePolicy updateAttendancePolicy(@PathVariable Long id, @RequestBody AttendancePolicy attendancePolicy) {
        return attendancePolicyService.updateAttendancePolicy(id, attendancePolicy);
    }
    /**
     * Deletes an attendance policy from the system.
     *
     * @param id The unique identifier of the attendance policy to be deleted
     * @return No content (204) if successful
     */
    @DeleteMapping("/{id}")
    public void deleteAttendancePolicy(@PathVariable Long id) {
        attendancePolicyService.deleteAttendancePolicy(id);
    }

}

