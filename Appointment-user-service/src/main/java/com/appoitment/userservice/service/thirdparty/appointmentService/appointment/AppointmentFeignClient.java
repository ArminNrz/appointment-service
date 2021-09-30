package com.appoitment.userservice.service.thirdparty.appointmentService.appointment;

import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.appointment.dto.AppointmentRegisterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "appointment", url = "${userservice.appointment.url}")
public interface AppointmentFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/job-receiver/appointment")
    AppointmentModel register(AppointmentRegisterDTO dto);

    @RequestMapping(method = RequestMethod.GET, value = "/job-receiver/appointment")
    List<AppointmentModel> getAppointments(@RequestParam("username") String username, @RequestParam("date") String date);
}
