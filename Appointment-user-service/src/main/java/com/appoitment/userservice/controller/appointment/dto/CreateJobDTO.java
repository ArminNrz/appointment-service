package com.appoitment.userservice.controller.appointment.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateJobDTO {
    @NotEmpty
    private String name;
}
