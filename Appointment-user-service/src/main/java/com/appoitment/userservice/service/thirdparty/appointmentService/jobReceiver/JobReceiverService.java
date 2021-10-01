package com.appoitment.userservice.service.thirdparty.appointmentService.jobReceiver;

import com.appoitment.userservice.service.thirdparty.appointmentService.AppointmentFeignClient;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobReceiver.dto.JobReceiverCreateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobReceiverService {

    private final AppointmentFeignClient feignClient;

    public void create(String accountId) {

        log.debug("Try to create joReceiver with accountId: {}", accountId);
        JobReceiverCreateDTO dto = new JobReceiverCreateDTO(accountId);

        try {
            feignClient.createJobReceiver(dto);
            log.info("Registered jobReceiver: {}", dto);
        } catch (Exception exception) {
            throw Problem.valueOf(Status.BAD_REQUEST, "There is an error with appointment service");
        }
    }
}
