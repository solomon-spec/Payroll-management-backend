package org.example.payroll_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for Attendance Record entity")
public class AttendanceRecordDTO {

    @Schema(description = "Unique identifier of the attendance record", type = "integer", example = "1")
    private Long id;

    @Schema(description = "Employee ID associated with the attendance record", type = "integer", example = "1")
    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;

    @Schema(description = "Date of the attendance record", type = "string", format = "date", example = "2022-01-01")
    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    @Schema(description = "Check-in time of the attendance record", type = "string", format = "time", example = "09:00:00")
    @NotNull(message = "Check-in time is mandatory")
    private LocalTime checkInTime;

    @Schema(description = "Check-out time of the attendance record", type = "string", format = "time", example = "17:00:00")
    @NotNull(message = "Check-out time is mandatory")
    private LocalTime checkOutTime;

}