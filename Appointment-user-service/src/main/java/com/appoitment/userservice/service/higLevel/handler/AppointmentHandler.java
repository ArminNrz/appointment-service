package com.appoitment.userservice.service.higLevel.handler;

import com.appoitment.userservice.controller.appointment.dto.RegisterAppointmentDTO;
import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.appointment.AppointmentService;
import com.appoitment.userservice.service.thirdparty.appointmentService.appointment.dto.AppointmentRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppointmentHandler {

    private final AppointmentService appointmentService;

    public AppointmentModel register(RegisterAppointmentDTO dto, String accountId) {

        log.debug("register appointment, dto: {}, accountId: {}", dto, accountId);

        AppointmentRegisterDTO appointmentRegisterDTO = new AppointmentRegisterDTO(dto, accountId);

        return appointmentService.register(appointmentRegisterDTO);
    }

    public List<AppointmentModel> getAppointments(String accountId, String date) {

        log.debug("getAppointments, accountId: {}, date: {}", accountId, date);

        return appointmentService.getAppointments(accountId, date);
    }
}
