package org.example.payroll_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalaryPaymentStatus {
    @Id
    private Long id;
    private String status;
    private LocalDate dateOfPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_id", referencedColumnName = "id")
    private Salary salary;
}
