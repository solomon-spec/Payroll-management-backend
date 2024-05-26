package org.example.payroll_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for Salary entity")
public class SalaryDTO {

    @Schema(description = "Unique identifier of the salary", type = "integer", example = "1")
    private Long id;

    @Schema(description = "Basic salary of the employee", type = "number", format = "double", example = "5000.0")
    @NotNull(message = "Basic salary is mandatory")
    @Positive(message = "Basic salary must be positive")
    private Double basicSalary;

    @Schema(description = "House allowance of the employee", type = "number", format = "double", example = "1000.0")
    @NotNull(message = "House allowance is mandatory")
    @Min(value = 0, message = "House allowance must be zero or positive")
    private Double houseAllowance;

    @Schema(description = "Transport allowance of the employee", type = "number", format = "double", example = "500.0")
    @NotNull(message = "Transport allowance is mandatory")
    @Min(value = 0, message = "Transport allowance must be zero or positive")
    private Double transportAllowance;

    @Schema(description = "Medical allowance of the employee", type = "number", format = "double", example = "500.0")
    @NotNull(message = "Medical allowance is mandatory")
    @Min(value = 0, message = "Medical allowance must be zero or positive")
    private Double medicalAllowance;

    @Schema(description = "Tax deduction of the employee", type = "number", format = "double", example = "500.0")
    @NotNull(message = "Tax deduction is mandatory")
    @Min(value = 0, message = "Tax deduction must be zero or positive")
    private Double taxDeduction;

    @Schema(description = "Insurance deduction of the employee", type = "number", format = "double", example = "200.0")
    @NotNull(message = "Insurance deduction is mandatory")
    @Min(value = 0, message = "Insurance deduction must be zero or positive")
    private Double insuranceDeduction;

    @Schema(description = "Employee ID associated with the salary", type = "integer", example = "1")
    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;

    @Schema(description = "Bonus of the employee", type = "number", format = "double", example = "500.0")
    @NotNull(message = "Bonus is mandatory")
    @Min(value = 0, message = "Bonus must be zero or positive")
    private Double bonus;

}