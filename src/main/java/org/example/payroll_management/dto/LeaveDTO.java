package org.example.payroll_management.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.example.payroll_management.model.LeaveStatus;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDTO {

    private Long id;
    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;

    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory")
    private LocalDate endDate;

    @NotNull(message = "Leave type is mandatory")
    private String leaveType;

    private LeaveStatus status;
}