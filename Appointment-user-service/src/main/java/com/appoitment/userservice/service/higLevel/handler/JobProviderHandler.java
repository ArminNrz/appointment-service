package com.appoitment.userservice.service.higLevel.handler;

import com.appoitment.userservice.controller.appointment.dto.CreateJobProviderDTO;
import com.appoitment.userservice.controller.appointment.dto.UpdateJobProviderDTO;
import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.model.JobProviderModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.JobProviderService;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto.JobProviderCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto.JobProviderUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobProviderHandler {

    private final JobProviderService jobProviderService;

    public JobProviderModel registerJobProvider(CreateJobProviderDTO dto, String accountId) {

        log.debug("register job provider, dto: {}, accountId: {}", dto, accountId);

        JobProviderCreateDTO jobProviderCreateDTO = new JobProviderCreateDTO(dto, accountId);

        return jobProviderService.create(jobProviderCreateDTO);
    }

    public JobProviderModel updateJobProvider(UpdateJobProviderDTO dto, String accountId) {

        log.debug("update job provider, dto: {}, accountId: {}", dto, accountId);

        JobProviderUpdateDTO jobProviderUpdateDTO = new JobProviderUpdateDTO(dto, accountId);

        return jobProviderService.update(jobProviderUpdateDTO);
    }

    public List<JobProviderModel> getAllJobProviders(AppUser user, int page, int size) {

        log.debug("getAllJobProviders, user: {}, page: {}, size: {}", user, page, size);

        String accountId = user.getUsername();
        String ownerId;

        if (user.getSuperUsername() == null)
            ownerId = accountId;
        else
            ownerId = user.getSuperUsername();

        log.debug("get all job provider, with accountId: {}", ownerId);

        return jobProviderService.getAll(ownerId, page, size);
    }
}
