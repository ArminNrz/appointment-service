package com.appoitment.userservice.service.thirdparty.appointmentService.jobOwner;

import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.AppointmentFeignClient;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobOwner.dto.JobOwnerCreateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobOwnerService {

    private final AppointmentFeignClient feignClient;

    public void create(JobOwnerCreateDTO dto) {

        log.debug("Try to create job owner for jobOwnerDTO: {}", dto);

        feignClient.createJobOwner(dto);
        log.info("Register jobOwner: {} for user: {}", dto.getName(), dto.getAccountId());
    }

    public List<AppointmentModel> getAppointments(String accountId, String date) {

        log.debug("Try to get appointments of accountId: {}, in date: {}", accountId, date);
        List<AppointmentModel> models;

        models = feignClient.getOwnerAppointments(accountId, date);
        log.info("Appointments of accountId: {}, in date: {}, is: {}", accountId, date, models);

        return models;
    }

    public List<AppointmentModel> getAppointments(String accountId, String date, String providerId) {

        log.debug("Try to get appointments of accountId: {}, with providerId: {}, in date: {}", accountId, providerId, date);
        List<AppointmentModel> models;

        models = feignClient.getOwnerAppointmentsByProviderId(accountId, date, providerId);
        log.info("Appointments of accountId: {}, with providerId: {} in date: {}, is: {}", accountId, providerId, date, models);

        return models;
    }
}
