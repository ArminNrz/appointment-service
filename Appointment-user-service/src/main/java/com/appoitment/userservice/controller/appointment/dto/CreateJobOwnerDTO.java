package com.appoitment.userservice.controller.appointment.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateJobOwnerDTO {
    @NotEmpty
    private String name;
}
