package com.appoitment.userservice.service.higLevel.handler;

import com.appoitment.userservice.controller.appointment.dto.CreateJobDTO;
import com.appoitment.userservice.controller.appointment.dto.UpdateJobDTO;
import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.model.JobModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.JobService;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobHandler {

    private final JobService jobService;

    public JobModel registerJob(CreateJobDTO dto, String accountId) {

        log.debug("register job, dto: {} with accountId: {}", dto, accountId);

        JobCreateDTO job = new JobCreateDTO(dto.getName(), accountId);

        return jobService.create(job);
    }

    public JobModel updateJob(UpdateJobDTO dto, String accountId) {

        log.debug("update job, dto: {}, with accountId: {}", dto, accountId);

        JobUpdateDTO jobUpdateDTO = new JobUpdateDTO(dto.getId(), dto.getName(), dto.getStatus(), accountId);

        return jobService.update(jobUpdateDTO);
    }

    public List<JobModel> getAllJobs(String accountId, AppUser user, int page, int size) {

        log.debug("getAllJobs with accountId: {}", accountId);
        String ownerId;

        if (user.getSuperUsername() == null)
            ownerId = accountId;
        else
            ownerId = user.getSuperUsername();

        log.debug("get all job, with accountId: {}", ownerId);

        return jobService.getAll(ownerId, page, size);
    }
}
