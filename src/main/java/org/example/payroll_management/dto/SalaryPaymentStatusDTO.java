package org.example.payroll_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryPaymentStatusDTO {

    private Long id;
    private String status;
    private String dateOfPayment;
    private Long salaryId;

}