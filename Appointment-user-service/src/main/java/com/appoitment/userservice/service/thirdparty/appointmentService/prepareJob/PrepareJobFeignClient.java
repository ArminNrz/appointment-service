package com.appoitment.userservice.service.thirdparty.appointmentService.prepareJob;

import com.appoitment.userservice.model.PrepareJobModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.prepareJob.dto.PrepareJobCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "prepareJob", url = "${userservice.appointment.url}")
public interface PrepareJobFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/prepare-job")
    PrepareJobModel create(PrepareJobCreateDTO dto);

    @RequestMapping(method = RequestMethod.GET, value = "/prepare-job/{accountId}")
    List<PrepareJobModel> getAll(@PathVariable("accountId") String ownerAccountId, @RequestParam("page") int page, @RequestParam("size") int size);
}
