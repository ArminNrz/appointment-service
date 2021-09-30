package com.appoitment.userservice.service.thirdparty.appointmentService.job;

import com.appoitment.userservice.model.JobModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("appointment-ws")
public interface JobFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/job")
    JobModel create(JobCreateDTO dto);

    @RequestMapping(method = RequestMethod.PUT, value = "/job")
    JobModel update(JobUpdateDTO dto);

    @RequestMapping(method = RequestMethod.GET, value = "/job/{accountId}")
    List<JobModel> getAll(@PathVariable("accountId") String accountId, @RequestParam("page") int page, @RequestParam("size") int size);
}
