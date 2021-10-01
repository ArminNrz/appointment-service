package com.appoitment.userservice.service.thirdparty.appointmentService;

import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.model.JobModel;
import com.appoitment.userservice.model.JobProviderModel;
import com.appoitment.userservice.model.PrepareJobModel;
import com.appoitment.userservice.service.thirdparty.appointmentService.appointment.dto.AppointmentRegisterDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.job.dto.JobUpdateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobOwner.dto.JobOwnerCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto.JobProviderCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto.JobProviderUpdateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.jobReceiver.dto.JobReceiverCreateDTO;
import com.appoitment.userservice.service.thirdparty.appointmentService.prepareJob.dto.PrepareJobCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("APPOINTMENT-WS")
public interface AppointmentFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/job-owner")
    void createJobOwner(JobOwnerCreateDTO createDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/job-provider")
    JobProviderModel createJobProvider(JobProviderCreateDTO dto);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/job-provider")
    JobProviderModel updateJobProvider(JobProviderUpdateDTO dto);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/job-provider/{accountId}")
    List<JobProviderModel> getAllJobProviders(@PathVariable("accountId") String accountId, @RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/job")
    JobModel createJob(JobCreateDTO dto);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/job")
    JobModel updateJob(JobUpdateDTO dto);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/job/{accountId}")
    List<JobModel> getAllJobs(@PathVariable("accountId") String accountId, @RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/job-receiver")
    void createJobReceiver(JobReceiverCreateDTO dto);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/prepare-job")
    PrepareJobModel createPrepareJob(PrepareJobCreateDTO dto);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/prepare-job/{accountId}")
    List<PrepareJobModel> getAllPrepareJobs(@PathVariable("accountId") String ownerAccountId, @RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/job-receiver/appointment")
    AppointmentModel registerAppointment(AppointmentRegisterDTO dto);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/job-receiver/appointment")
    List<AppointmentModel> getAppointments(@RequestParam("username") String username, @RequestParam("date") String date);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/job-owner/appointments")
    List<AppointmentModel> getOwnerAppointments(@RequestParam("accountId") String accountId, @RequestParam("date") String date);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/job-owner/appointments/{providerId}")
    List<AppointmentModel> getOwnerAppointmentsByProviderId(@RequestParam("accountId") String accountId, @RequestParam("date") String date, @PathVariable("providerId") String providerId);
}
