package org.example.payroll_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDTO {

    private Long id;

    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;

    @NotNull(message = "Basic salary is mandatory")
    @Positive(message = "Basic salary must be positive")
    private Double basicSalary;

    @NotNull(message = "House allowance is mandatory")
    @Min(value = 0, message = "House allowance must be zero or positive")
    private Double houseAllowance;

    @NotNull(message = "Transport allowance is mandatory")
    @Min(value = 0, message = "Transport allowance must be zero or positive")
    private Double transportAllowance;

    @NotNull(message = "Medical allowance is mandatory")
    @Min(value = 0, message = "Medical allowance must be zero or positive")
    private Double medicalAllowance;

    @NotNull(message = "Tax deduction is mandatory")
    @Min(value = 0, message = "Tax deduction must be zero or positive")
    private Double taxDeduction;

    @NotNull(message = "Insurance deduction is mandatory")
    @Min(value = 0, message = "Insurance deduction must be zero or positive")
    private Double insuranceDeduction;

    @NotNull(message = "Bonus is mandatory")
    @Min(value = 0, message = "Bonus must be zero or positive")
    private Double bonus;

}