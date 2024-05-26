package org.example.payroll_management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordDTO {
    private Long id;

    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;

    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    @NotNull(message = "Check-in time is mandatory")
    private LocalTime checkInTime;

    @NotNull(message = "Check-out time is mandatory")
    private LocalTime checkOutTime;

}