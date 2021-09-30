package com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider;

import com.appoitment.userservice.model.JobProviderModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto.JobProviderCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto.JobProviderUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "jobProvider", url = "${userservice.appointment.url}")
public interface JobProviderFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/job-provider")
    JobProviderModel create(JobProviderCreateDTO dto);

    @RequestMapping(method = RequestMethod.PUT, value = "/job-provider")
    JobProviderModel update(JobProviderUpdateDTO dto);

    @RequestMapping(method = RequestMethod.GET, value = "/job-provider/{accountId}")
    List<JobProviderModel> getAll(@PathVariable("accountId") String accountId, @RequestParam("page") int page, @RequestParam("size") int size);
}
