package com.appoitment.userservice.service.thirdparty.appointmentService.appointment.dto;

import com.appoitment.userservice.controller.appointment.dto.RegisterAppointmentDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRegisterDTO {
    private String jobReceiverUsername;
    private String prepareJobId;
    private LocalDate date;
    private LocalTime startTime;

    public AppointmentRegisterDTO(RegisterAppointmentDTO dto, String accountId) {
        this.jobReceiverUsername = accountId;
        this.prepareJobId = dto.getPrepareJobId();
        this.date = dto.getDate();
        this.startTime = dto.getTime();
    }
}
