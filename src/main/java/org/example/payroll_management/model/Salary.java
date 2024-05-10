package org.example.payroll_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double basicSalary;

    private Double houseAllowance;

    private Double transportAllowance;

    private Double medicalAllowance;

    private Double taxDeduction;

    private Double insuranceDeduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    private Double bonus;
    @OneToMany(mappedBy = "salary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SalaryPaymentStatus> salaryPaymentStatuses;



}
