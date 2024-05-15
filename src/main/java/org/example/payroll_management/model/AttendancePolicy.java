package org.example.payroll_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalTime lateThreshold;

    private LocalTime absenceThreshold;

    private LocalTime earlyLeaveThreshold;

    private LocalTime workStart;

    private LocalTime workEnd;
}