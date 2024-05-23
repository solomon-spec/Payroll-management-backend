package org.example.payroll_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendancePolicyDTO {

    private Long id;
    private String workStart;
    private String workEnd;
    private String lateThreshold;
    private String earlyLeaveThreshold;
    private String absenceThreshold;
}