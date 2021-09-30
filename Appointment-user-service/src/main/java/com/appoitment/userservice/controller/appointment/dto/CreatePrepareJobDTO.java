package com.appoitment.userservice.controller.appointment.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreatePrepareJobDTO {
    @NotEmpty
    private String jobId;
    @NotEmpty
    private String jobProviderId;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private Double duration;
}
