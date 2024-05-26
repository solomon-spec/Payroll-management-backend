package org.example.payroll_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for Salary Payment Status entity")
public class SalaryPaymentStatusDTO {

    @Schema(description = "Unique identifier of the salary payment status", type = "integer", example = "1")
    private Long id;

    @Schema(description = "Status of the salary payment", type = "string", example = "PAID")
    @NotNull(message = "Status is mandatory")
    private String status;

    @Schema(description = "Date of payment", type = "string", format = "date", example = "2022-01-01")
    @NotNull(message = "Date of payment is mandatory")
    @PastOrPresent(message = "Date of payment can not be in the future")
    private LocalDate dateOfPayment;

    @Schema(description = "Salary ID associated with the payment status", type = "integer", example = "1")
    @NotNull(message = "Salary ID is mandatory")
    private Long salaryId;

}