package com.appoitment.userservice.controller.appointment.dto;

import com.appoitment.userservice.domain.enumaration.JobStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateJobDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    private JobStatus status;
}
