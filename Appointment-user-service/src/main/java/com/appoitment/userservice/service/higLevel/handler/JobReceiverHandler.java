package com.appoitment.userservice.service.higLevel.handler;

import com.appoitment.userservice.service.thirdparty.appointmentService.jobReceiver.JobReceiverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobReceiverHandler {

    private final JobReceiverService jobReceiverService;

    public void register(String accountId) {
        jobReceiverService.create(accountId);
    }
}
