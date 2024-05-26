package org.example.payroll_management.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendancePolicyDTO {

    @Schema(description = "Unique identifier of the attendance policy", type = "integer", example = "1")
    private Long id;

    @Schema(description = "Name of the attendance policy", type = "string", example = "Standard")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Schema(description = "Description of the attendance policy", type = "string", example = "Standard 9-5 policy")
    @NotNull(message = "Description cannot be null")
    private String description;

    @Schema(description = "Work start time of the attendance policy", type = "string", format = "time", example = "09:00:00")
    @NotNull(message = "Work start time cannot be null")
    private LocalTime workStart;

    @Schema(description = "Work end time of the attendance policy", type = "string", format = "time", example = "17:00:00")
    @NotNull(message = "Work end time cannot be null")
    private LocalTime workEnd;

    @Schema(description = "Late threshold of the attendance policy", type = "string", format = "time", example = "09:30:00")
    @NotNull(message = "Late threshold cannot be null")
    private LocalTime lateThreshold;

    @Schema(description = "Early leave threshold of the attendance policy", type = "string", format = "time", example = "16:30:00")
    @NotNull(message = "Early leave threshold cannot be null")
    private LocalTime earlyLeaveThreshold;

    @Schema(description = "Absence threshold of the attendance policy", type = "string", format = "time", example = "10:00:00")
    @NotNull(message = "Absence threshold cannot be null")
    private LocalTime absenceThreshold;
}