package org.example.payroll_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDTO {

    private Long id;
    private Double basicSalary;
    private Double houseAllowance;
    private Double transportAllowance;
    private Double medicalAllowance;
    private Double taxDeduction;
    private Double insuranceDeduction;
    private Long employeeId;
    private Double bonus;

}