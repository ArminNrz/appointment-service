package com.appoitment.userservice.service.thirdparty.appointmentService.prepareJob;

import com.appoitment.userservice.model.PrepareJobModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.AppointmentFeignClient;
import com.appoitment.userservice.service.thirdparty.appointmentService.prepareJob.dto.PrepareJobCreateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrepareJobService {

    private final AppointmentFeignClient feignClient;

    public PrepareJobModel create(PrepareJobCreateDTO dto) {

        log.info("Try to create prepareJob with jobDTO: {}", dto);

        PrepareJobModel model;

        try {
            model = feignClient.createPrepareJob(dto);
            log.info("Register prepare job: {}, for user: {} by user: {}", model, dto.getServiceOwnerAccountId(), dto.getCreatorAccountId());
        } catch (Exception e) {
            throw Problem.valueOf(Status.BAD_REQUEST, e.getMessage());
        }

        return model;
    }

    public List<PrepareJobModel> getAll(String ownerAccountId, int page, int size) {

        log.info("Try to get all prepareJob for ownerAccountId: {}, page: {}, size: {}", ownerAccountId, page, size);

        List<PrepareJobModel> models;

        try {
            models = feignClient.getAllPrepareJobs(ownerAccountId, page, size);
            log.info("Get all prepareJob: {}", models);
        } catch (Exception e) {
            throw Problem.valueOf(Status.BAD_REQUEST, e.getMessage());
        }

        return models;
    }
}
