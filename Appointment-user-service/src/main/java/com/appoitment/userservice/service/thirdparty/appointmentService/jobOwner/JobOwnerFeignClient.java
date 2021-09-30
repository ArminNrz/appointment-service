package com.appoitment.userservice.service.thirdparty.appointmentService.jobOwner;

import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobOwner.dto.JobOwnerCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "jobOwner", url = "${userservice.appointment.url}")
public interface JobOwnerFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/job-owner")
    void create(JobOwnerCreateDTO createDTO);

    @RequestMapping(method = RequestMethod.GET, value = "/job-owner/appointments")
    List<AppointmentModel> getAppointments(@RequestParam("accountId") String accountId, @RequestParam("date") String date);

    @RequestMapping(method = RequestMethod.GET, value = "/job-owner/appointments/{providerId}")
    List<AppointmentModel> getAppointments(@RequestParam("accountId") String accountId, @RequestParam("date") String date, @PathVariable("providerId") String providerId);
}
