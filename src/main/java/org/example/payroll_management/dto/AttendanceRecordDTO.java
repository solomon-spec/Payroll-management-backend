package org.example.payroll_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordDTO {
    private Long id;
    private Long employeeId;
    private String date;
    private String checkInTime;
    private String checkOutTime;

}