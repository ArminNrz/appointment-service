package com.appoitment.userservice.service.higLevel.handler;

import com.appoitment.userservice.controller.appointment.dto.CreateJobOwnerDTO;
import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.service.higLevel.data.UserContext;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobOwner.JobOwnerService;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobOwner.dto.JobOwnerCreateDTO;
import com.appoitment.userservice.utility.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobOwnerHandler {

    private final JobOwnerService jobOwnerService;
    private final Utility utility;

    public void registerJobOwner(CreateJobOwnerDTO jobOwnerDTO, AppUser user) {

        log.debug("register job owner, jobOwnerDTO: {}", jobOwnerDTO);

        JobOwnerCreateDTO dto = new JobOwnerCreateDTO(user.getName(), user.getUsername());

        jobOwnerService.create(dto);
    }

    public List<AppointmentModel> getAppointments(AppUser user, String providerId, String date) {

        log.debug("getAppointments, user: {}, providerId: {}, date: {}", user, providerId, date);

        UserContext userContext = utility.detectUserContext(user);
        List<AppointmentModel> models;

        if (providerId == null) {
            models = jobOwnerService.getAppointments(userContext.getOwnerAccountId(), date);
        } else {
            models = jobOwnerService.getAppointments(userContext.getOwnerAccountId(), date, providerId);
        }

        return models;
    }
}
