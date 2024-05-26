package org.example.payroll_management.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First name can only contain alphabetic characters and spaces")
    private String firstName;

    @Size(max = 50, message = "Middle name must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First name can only contain alphabetic characters and spaces")

    private String middleName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First name can only contain alphabetic characters and spaces")

    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Date of joining is mandatory")
    @Past(message = "Date of joining must be in the past")
    private LocalDate dateOfJoining;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

    @NotBlank(message = "Position is mandatory")
    private String position;

    @NotBlank(message = "Bank account is mandatory")
    private String bankAccount;

    private Long managerId;
    private Long currentSalaryId;
    private Long attendancePolicyId;
}