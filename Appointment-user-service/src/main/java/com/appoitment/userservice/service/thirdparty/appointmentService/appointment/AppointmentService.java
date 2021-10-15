package com.appoitment.userservice.service.thirdparty.appointmentService.appointment;

import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.AppointmentFeignClient;
import com.appoitment.userservice.service.thirdparty.appointmentService.appointment.dto.AppointmentRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentFeignClient feignClient;

    public AppointmentModel register(AppointmentRegisterDTO dto) {

        log.debug("Try to register appointment with AppointmentRegisterDTO: {}", dto);

        AppointmentModel model;

        model = feignClient.registerAppointment(dto);
        log.info("Register appointments: {}, for user: {}", model, dto.getJobReceiverUsername());

        return model;
    }

    public List<AppointmentModel> getAppointments(String accountId, String date) {

        log.debug("Try to get appointments for accountId: {}, in date: {}", accountId, date);

        List<AppointmentModel> models;

        models = feignClient.getAppointments(accountId, date);
        log.info("Appointments for accountId: {}, in date: {}, is: {}", accountId, date, models);

        return models;
    }
}
