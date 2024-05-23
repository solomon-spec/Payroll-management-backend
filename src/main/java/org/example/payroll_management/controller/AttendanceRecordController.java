package org.example.payroll_management.controller;

import org.example.payroll_management.dto.AttendanceRecordDTO;
import org.example.payroll_management.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance-record")
public class AttendanceRecordController {

    private final AttendanceRecordService attendanceRecordService;

    @Autowired
    public AttendanceRecordController(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }
    /**
     * Checks in an employee and creates a new attendance record in the system.
     *
     * @param employeeId The unique identifier of the employee checking in
     * @return JSON object representing the newly created attendance record
     */
    @PostMapping("/employees/{employeeId}/check-in")
    public ResponseEntity<AttendanceRecordDTO> checkInEmployee(@PathVariable Long employeeId) {
        AttendanceRecordDTO createdRecord = attendanceRecordService.checkInEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    /**
     * Checks out an employee and updates the corresponding attendance record.
     *
     * @param employeeId The unique identifier of the employee checking out
     * @return JSON object representing the updated attendance record
     */
    @PatchMapping("/employees/{employeeId}/check-out")
    public ResponseEntity<AttendanceRecordDTO> checkOutEmployee(@PathVariable Long employeeId) {
        AttendanceRecordDTO updatedRecord = attendanceRecordService.checkOutEmployee(employeeId);
        return ResponseEntity.ok(updatedRecord);
    }

    /**
     * Retrieves the attendance record of an employee between two dates.
        *
     * @param employeeId The unique identifier of the employee
     * @param startDate The start date of the period
     * @param endDate The end date of the period
     * @return JSON object representing the attendance record of the employee between the specified dates
     */
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<List<AttendanceRecordDTO>> getAttendanceRecord(@PathVariable Long employeeId,
                                                                         @RequestParam(required = false) LocalDate startDate,
                                                                         @RequestParam(required = false) LocalDate endDate) {
        List<AttendanceRecordDTO> records = attendanceRecordService.getAttendanceRecord(employeeId, startDate, endDate);
        return ResponseEntity.ok(records);
    }

    /**
     * Retrieves all attendance records within a specified date range.
     *
     * @param startDate The start date of the date range
     * @param endDate   The end date of the date range
     * @return JSON array containing attendance records within the specified date range
     */
    @GetMapping("/all")
    public ResponseEntity<List<AttendanceRecordDTO>> getAttendanceRecord(@RequestParam(required = false) LocalDate startDate,
                                                                         @RequestParam(required = false) LocalDate endDate) {
        List<AttendanceRecordDTO> records = attendanceRecordService.getAttendanceRecordByTime(startDate, endDate);
        return ResponseEntity.ok(records);
    }

    /**
     * Calculates the total work time of a specific employee within a specified date range.
     *
     * @param employeeId The unique identifier of the employee
     * @param startDate  The start date of the date range
     * @param endDate    The end date of the date range
     * @return JSON object containing the total work time of the employee
     */

    @GetMapping("/employees/{employeeId}/work-time")
    public String getWorkTime(@PathVariable Long employeeId,
                              @RequestParam(required = false) LocalDate startDate,
                              @RequestParam(required = false) LocalDate endDate) {
        return attendanceRecordService.getWorkTime(employeeId, startDate, endDate);
    }

    /**
     * Retrieves all attendance records with late check-ins based on the employee's attendance policy.
     *
     * @param employeeId The unique identifier of the employee
     * @return JSON array containing attendance records with late check-ins
     */
    @GetMapping("/employees/{employeeId}/records/late-check-ins")
    public ResponseEntity<List<AttendanceRecordDTO>> getLateCheckIns(@PathVariable Long employeeId) {
        List<AttendanceRecordDTO> records = attendanceRecordService.getLateCheckIns(employeeId);
        return ResponseEntity.ok(records);
    }
    /**
     * Retrieves all attendance records with absent days based on the employee's attendance policy.
     *
     * @param employeeId The unique identifier of the employee
     * @return JSON array containing attendance records with absent days
     */
    @GetMapping("/employees/{employeeId}/records/absent-days")
    public ResponseEntity<List<AttendanceRecordDTO>> getAbsentDays(@PathVariable Long employeeId) {
        List<AttendanceRecordDTO> records = attendanceRecordService.getAbsentDays(employeeId);
        return ResponseEntity.ok(records);
    }
    /**
     * Retrieves all attendance records with early leaves based on the employee's attendance policy.
     *
     * @param employeeId The unique identifier of the employee
     * @return JSON array containing attendance records with early leaves
     */
    @GetMapping("/employees/{employeeId}/records/early-leaves")
    public ResponseEntity<List<AttendanceRecordDTO>> getEarlyLeaves(@PathVariable Long employeeId) {
        List<AttendanceRecordDTO> records = attendanceRecordService.getEarlyLeaves(employeeId);
        return ResponseEntity.ok(records);
    }




}
