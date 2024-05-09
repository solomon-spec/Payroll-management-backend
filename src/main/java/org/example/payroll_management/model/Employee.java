package org.example.payroll_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private LocalDate dateOfJoining;

    private Double salary;

    private String address;

    private String phoneNumber;

    private String position;

    private String bankAccount;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttendanceRecord> attendanceRecords;
}