package com.appoitment.userservice.service.thirdparty.appointmentService.job;

import com.appoitment.userservice.model.JobModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {

    private final JobFeignClient feignClient;

    public JobModel create(JobCreateDTO dto) {

        log.debug("Try to create job for jobDTO: {}", dto);

        JobModel model;

        try {
            model = feignClient.create(dto);
            log.info("Register job: {}, for user: {}", model, dto.getAccountId());
        } catch (Exception e) {
            throw Problem.valueOf(Status.BAD_REQUEST, "There is an error with appointment service");
        }

        return model;
    }

    public JobModel update(JobUpdateDTO dto) {

        log.debug("Try to update job for jobDTO: {}", dto);

        JobModel model;

        try {
            model = feignClient.update(dto);
            log.info("Update job: {}, for user: {}", model, dto.getAccountId());
        } catch (Exception e) {
            throw Problem.valueOf(Status.BAD_REQUEST, "There is an error with appointment service");
        }

        return model;
    }

    public List<JobModel> getAll(String accountId, int page, int size) {

        log.debug("Try to get all jobs with accountId: {}, page: {}, size: {}", accountId, page, size);

        List<JobModel> models;

        try {
            models = feignClient.getAll(accountId, page, size);
            log.info("Get all jobs: {}, for user: {}", models, accountId);
        } catch (Exception e) {
            throw Problem.valueOf(Status.BAD_REQUEST, "There is an error with appointment service");
        }

        return models;
    }
}
