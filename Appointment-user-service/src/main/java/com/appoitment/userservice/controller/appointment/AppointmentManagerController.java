package com.appoitment.userservice.controller.appointment;

import com.appoitment.userservice.controller.appointment.dto.*;
import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.model.JobModel;
import com.appoitment.userservice.model.JobProviderModel;
import com.appoitment.userservice.model.PrepareJobModel;
import com.appoitment.userservice.service.higLevel.AppointmentManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Slf4j
@RequiredArgsConstructor
public class AppointmentManagerController {

    private final AppointmentManagementService appointmentManagementService;

    @PostMapping("/job-owner")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN')")
    public ResponseEntity<?> createJobOwner(@RequestHeader("Authorization") String token, @Valid @RequestBody CreateJobOwnerDTO jobOwnerDTO) {
        log.info("Request to create jobOwner for Owner: {}", jobOwnerDTO.getName());
        appointmentManagementService.registerJobOwner(jobOwnerDTO, token);
        return ResponseEntity.created(URI.create("/api/job-owner")).build();
    }

    @PostMapping("/job-provider")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN')")
    public ResponseEntity<JobProviderModel> createJobProvider(@RequestHeader("Authorization") String token, @Valid @RequestBody CreateJobProviderDTO jobProviderDTO) {
        log.info("Request to create jobProvider with jobProviderDTO: {}", jobProviderDTO);
        JobProviderModel model = appointmentManagementService.registerJobProvider(jobProviderDTO, token);
        return ResponseEntity.created(URI.create("/api/job-provider")).body(model);
    }

    @PutMapping("/job-provider")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN')")
    public ResponseEntity<JobProviderModel> updateJobProvider(@RequestHeader("Authorization") String token, @Valid @RequestBody UpdateJobProviderDTO jobProviderDTO) {
        log.info("Request to update jobProvider with jobProviderDTO: {}", jobProviderDTO);
        JobProviderModel model = appointmentManagementService.updateJobProvider(jobProviderDTO, token);
        return ResponseEntity.ok().body(model);
    }

    @GetMapping("/job-provider")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN', 'ROLE_SERVICE_CLIENT')")
    public ResponseEntity<List<JobProviderModel>> getJobProvider(@RequestHeader("Authorization") String token, @RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("Request to get all jobProvider");
        return ResponseEntity.ok(appointmentManagementService.getAllJobProviders(token, page, size));
    }

    @PostMapping("/job")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN')")
    public ResponseEntity<JobModel> createJob(@RequestHeader("Authorization") String token, @Valid @RequestBody CreateJobDTO jobDTO) {
        log.info("Request to create job with jobDTO: {}", jobDTO);
        JobModel model = appointmentManagementService.registerJob(jobDTO, token);
        return ResponseEntity.created(URI.create("/api/job")).body(model);
    }

    @PutMapping("/job")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN')")
    public ResponseEntity<JobModel> updateJob(@RequestHeader("Authorization") String token, @Valid @RequestBody UpdateJobDTO jobDTO) {
        log.info("Request to update job with jobDTO: {}", jobDTO);
        JobModel model = appointmentManagementService.updateJob(jobDTO, token);
        return ResponseEntity.ok().body(model);
    }

    @GetMapping("/job")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN', 'ROLE_SERVICE_CLIENT')")
    public ResponseEntity<List<JobModel>> getJob(@RequestHeader("Authorization") String token, @RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("Request to get all job");
        return ResponseEntity.ok(appointmentManagementService.getAllJobs(token, page, size));
    }

    @PostMapping("/prepare-job")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN', 'ROLE_SERVICE_CLIENT')")
    public ResponseEntity<PrepareJobModel> createPrepareJob(@RequestHeader("Authorization") String token, @Valid @RequestBody CreatePrepareJobDTO prepareJobDTO) {
        log.info("Request to create prepareJob with prepareJobDTO: {}", prepareJobDTO);
        PrepareJobModel model = appointmentManagementService.registerPrepareJob(prepareJobDTO, token);
        return ResponseEntity.created(URI.create("/api/prepare-job")).body(model);
    }

    @GetMapping("/prepare-job")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN', 'ROLE_SERVICE_CLIENT')")
    public ResponseEntity<List<PrepareJobModel>> getPrepareJobs(@RequestHeader("Authorization") String token, @RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("Request to get all prepareJob");
        return ResponseEntity.ok(appointmentManagementService.getAllPrepareJob(token, page, size));
    }

    @GetMapping("/prepare-job/{ownerId}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<List<PrepareJobModel>> getPrepareJobsByOwnerId(@PathVariable("ownerId") String ownerId, @RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("Request to get all prepareJobs of ownerId: {}", ownerId);
        return ResponseEntity.ok(appointmentManagementService.getAllPrepareJobByOwnerId(ownerId, page, size));
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<AppointmentModel> registerAppointments(@RequestHeader("Authorization") String token, @Valid @RequestBody RegisterAppointmentDTO dto) {
        log.info("Request to register appointments");
        return ResponseEntity.created(URI.create("/appointments/register")).body(appointmentManagementService.registerAppointment(token, dto));
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_SERVICE_ADMIN', 'ROLE_SERVICE_CLIENT')")
    public ResponseEntity<List<AppointmentModel>> getAppointments(@RequestHeader("Authorization") String token, @Nullable @RequestParam("providerId") String providerId, @RequestParam("date") String date) {
        log.info("Request to get all appointments in date: {}, with providerId: {}", date, providerId);
        return ResponseEntity.ok(appointmentManagementService.getAppointment(token, date, providerId));
    }
}
