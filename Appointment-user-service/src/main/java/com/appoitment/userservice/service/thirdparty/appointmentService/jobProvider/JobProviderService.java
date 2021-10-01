package com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider;

import com.appoitment.userservice.model.JobProviderModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.AppointmentFeignClient;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto.JobProviderCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto.JobProviderUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobProviderService {

    private final AppointmentFeignClient feignClient;

    public JobProviderModel create(JobProviderCreateDTO dto) {

        log.debug("Try to create job provider for JobProviderCreateDTO: {}", dto);

        JobProviderModel model;

        try {
            model = feignClient.createJobProvider(dto);
            log.info("Register jobProvider: {}, for user: {}", model, dto.getAccountId());
        } catch (Exception e) {
            throw Problem.valueOf(Status.BAD_REQUEST, "There is an error with appointment service");
        }

        return model;
    }

    public JobProviderModel update(JobProviderUpdateDTO dto) {

        log.debug("Try to update job provider for JobProviderUpdateDTO: {}", dto);

        JobProviderModel model;

        try {
            model = feignClient.updateJobProvider(dto);
            log.info("Updated jobProvider: {}", model);
        } catch (Exception e) {
            throw Problem.valueOf(Status.BAD_REQUEST, "There is an error with appointment service");
        }

        return model;
    }

    public List<JobProviderModel> getAll(String accountId, int page, int size) {

        log.debug("Try to get all job provider with accountId: {}", accountId);

        List<JobProviderModel> models;

        try {
            models = feignClient.getAllJobProviders(accountId, page, size);
            log.info("Get all jobProviders: {}", models);
        } catch (Exception e) {
            throw Problem.valueOf(Status.BAD_REQUEST, "There is an error with appointment service");
        }

        return models;
    }
}
