package com.appoitment.userservice.service.thirdparty.appointmentService.jobReceiver;

import com.appoitment.userservice.service.thirdparty.appointmentService.jobReceiver.dto.JobReceiverCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "jobReceiver", url = "${userservice.appointment.url}")
public interface JobReceiverFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/job-receiver")
    void create(JobReceiverCreateDTO dto);
}
