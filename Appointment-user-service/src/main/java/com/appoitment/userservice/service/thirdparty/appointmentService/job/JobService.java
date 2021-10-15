package com.appoitment.userservice.service.thirdparty.appointmentService.job;

import com.appoitment.userservice.model.JobModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.AppointmentFeignClient;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {

    private final AppointmentFeignClient feignClient;

    public JobModel create(JobCreateDTO dto) {

        log.debug("Try to create job for jobDTO: {}", dto);

        JobModel model;

        model = feignClient.createJob(dto);
        log.info("Register job: {}, for user: {}", model, dto.getAccountId());

        return model;
    }

    public JobModel update(JobUpdateDTO dto) {

        log.debug("Try to update job for jobDTO: {}", dto);

        JobModel model;

        model = feignClient.updateJob(dto);
        log.info("Update job: {}, for user: {}", model, dto.getAccountId());

        return model;
    }

    public List<JobModel> getAll(String accountId, int page, int size) {

        log.debug("Try to get all jobs with accountId: {}, page: {}, size: {}", accountId, page, size);

        List<JobModel> models;

        models = feignClient.getAllJobs(accountId, page, size);
        log.info("Get all jobs: {}, for user: {}", models, accountId);

        return models;
    }
}
