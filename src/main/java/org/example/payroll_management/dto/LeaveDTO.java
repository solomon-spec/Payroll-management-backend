package org.example.payroll_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Unique identifier of the leave", type = "integer", example = "1")
    private Long id;

    @Schema(description = "Employee ID associated with the leave", type = "integer", example = "1")
    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;

    @Schema(description = "Start date of the leave", type = "string", format = "date", example = "2022-01-01")
    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    @Schema(description = "End date of the leave", type = "string", format = "date", example = "2022-01-10")
    @NotNull(message = "End date is mandatory")
    private LocalDate endDate;

    @Schema(description = "Type of the leave", type = "string", example = "Annual")
    @NotNull(message = "Leave type is mandatory")
    private String leaveType;

    @Schema(description = "Status of the leave", type = "string", example = "APPROVED", allowableValues = {"APPROVED", "PENDING", "REJECTED"})
    private LeaveStatus status;
}