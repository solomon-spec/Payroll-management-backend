package org.example.payroll_management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryPaymentStatusDTO {

    private Long id;
    @NotNull(message = "Status is mandatory")
    private String status;

    @NotNull(message = "Date of payment is mandatory")
    @PastOrPresent(message = "Date of payment can not be in the future")
    private LocalDate dateOfPayment;

    @NotNull(message = "Salary ID is mandatory")
    private Long salaryId;

}