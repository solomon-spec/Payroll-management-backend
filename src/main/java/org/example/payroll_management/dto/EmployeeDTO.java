package org.example.payroll_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate dateOfJoining;
    private String address;
    private String phoneNumber;
    private String position;
    private String bankAccount;
    private Long managerId;
    private Long currentSalaryId;
    private Long attendancePolicyId;
}