package org.example.payroll_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryPaymentStatusDTO {

    private Long id;
    private String status;
    private LocalDate dateOfPayment;
    private Long salaryId;

}