package org.example.payroll_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object for Employee entity")

public class EmployeeDTO {
    @Schema(description = "Unique identifier of the employee", type = "integer", example = "1")
    private Long id;

    @Schema(description = "First name of the employee", type = "string", example = "John")
    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First name can only contain alphabetic characters and spaces")
    private String firstName;

    @Schema(description = "Middle name of the employee", type = "string", example = "Doe")
    @Size(max = 50, message = "Middle name must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First name can only contain alphabetic characters and spaces")
    private String middleName;

    @Schema(description = "Last name of the employee", type = "string", example = "Smith")
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First name can only contain alphabetic characters and spaces")
    private String lastName;

    @Schema(description = "Email of the employee", type = "string", format = "email", example = "john.doe@example.com")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Date of joining of the employee", type = "string", format = "date", example = "2022-01-01")
    @NotNull(message = "Date of joining is mandatory")
    @Past(message = "Date of joining must be in the past")
    private LocalDate dateOfJoining;

    @Schema(description = "Address of the employee", type = "string", example = "123 Main St")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @Schema(description = "Phone number of the employee", type = "string", example = "1234567890")
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

    @Schema(description = "Position of the employee", type = "string", example = "Manager")
    @NotBlank(message = "Position is mandatory")
    private String position;

    @Schema(description = "Bank account of the employee", type = "string", example = "1234567890")
    @NotBlank(message = "Bank account is mandatory")
    private String bankAccount;

    @Schema(description = "Manager ID of the employee", type = "integer", example = "1")
    private Long managerId;

    @Schema(description = "Current salary ID of the employee", type = "integer", example = "1")
    private Long currentSalaryId;

    @Schema(description = "Attendance policy ID of the employee", type = "integer", example = "1")
    private Long attendancePolicyId;
}