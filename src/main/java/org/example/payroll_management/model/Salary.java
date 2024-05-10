package org.example.payroll_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Double bonus;


}
