package org.example.payroll_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendancePolicyDTO {

    private Long id;
    private String name;
    private String description;
    private LocalTime workStart;
    private LocalTime workEnd;
    private LocalTime lateThreshold;
    private LocalTime earlyLeaveThreshold;
    private LocalTime absenceThreshold;
}