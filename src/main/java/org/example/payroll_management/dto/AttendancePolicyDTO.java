package org.example.payroll_management.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendancePolicyDTO {

    private Long id;
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Work start time cannot be null")
    @ApiModelProperty(example = "13:45:00")
    private LocalTime workStart;

    @NotNull(message = "Work end time cannot be null")
    private LocalTime workEnd;

    @NotNull(message = "Late threshold cannot be null")
    private LocalTime lateThreshold;

    @NotNull(message = "Early leave threshold cannot be null")
    private LocalTime earlyLeaveThreshold;

    @NotNull(message = "Absence threshold cannot be null")
    private LocalTime absenceThreshold;
}