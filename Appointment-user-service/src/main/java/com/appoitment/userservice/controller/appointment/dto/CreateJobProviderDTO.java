package com.appoitment.userservice.controller.appointment.dto;

import com.appoitment.userservice.domain.enumaration.JobProviderStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateJobProviderDTO {
    @NotEmpty
    private String name;
    private JobProviderStatus status;
}
