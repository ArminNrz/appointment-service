package com.appoitment.userservice.controller.appointment.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RegisterAppointmentDTO {
    @NotEmpty
    private String prepareJobId;
    private LocalDate date;
    private LocalTime time;
}
