package com.appoitment.userservice.service.higLevel;

import com.appoitment.userservice.controller.appointment.dto.*;
import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.domain.Role;
import com.appoitment.userservice.model.AppointmentModel;
import com.appoitment.userservice.model.JobModel;
import com.appoitment.userservice.model.JobProviderModel;
import com.appoitment.userservice.model.PrepareJobModel;
import com.appoitment.userservice.service.entity.RoleService;
import com.appoitment.userservice.service.higLevel.handler.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentManagementService {

    private final JobHandler jobHandler;
    private final JobProviderHandler jobProviderHandler;
    private final JobOwnerHandler jobOwnerHandler;
    private final PrepareJobHandler prepareJobHandler;
    private final AppointmentHandler appointmentHandler;
    private final JobReceiverHandler jobReceiverHandler;
    private final AppointmentsUtility appointmentsUtility;
    private final RoleService roleService;

    ////////////////////////////////////
    // JOB OWNER
    ///////////////////////////////////

    public void registerJobOwner(CreateJobOwnerDTO jobOwnerDTO, String token) {

        log.debug("register job owner, jobOwnerDTO: {}", jobOwnerDTO);

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        jobOwnerHandler.registerJobOwner(jobOwnerDTO, user);
    }

    ////////////////////////////////////
    // JOB PROVIDER
    ///////////////////////////////////

    public JobProviderModel registerJobProvider(CreateJobProviderDTO dto, String token) {

        log.debug("register job provider, dto: {}", dto);

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return jobProviderHandler.registerJobProvider(dto, user.getUsername());
    }

    public JobProviderModel updateJobProvider(UpdateJobProviderDTO dto, String token) {

        log.debug("update job provider, dto: {}", dto);

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return jobProviderHandler.updateJobProvider(dto, user.getUsername());
    }

    public List<JobProviderModel> getAllJobProviders(String token, int page, int size) {

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return jobProviderHandler.getAllJobProviders(user, page, size);
    }

    ////////////////////////////////////
    // JOB
    ///////////////////////////////////

    public JobModel registerJob(CreateJobDTO dto, String token) {

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return jobHandler.registerJob(dto, user.getUsername());
    }

    public JobModel updateJob(UpdateJobDTO dto, String token) {

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return jobHandler.updateJob(dto, user.getUsername());
    }

    public List<JobModel> getAllJobs(String token, int page, int size) {

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return jobHandler.getAllJobs(user.getUsername(), user, page, size);
    }

    ////////////////////////////////////
    // PREPARE JOB
    ///////////////////////////////////

    public PrepareJobModel registerPrepareJob(CreatePrepareJobDTO dto, String token) {

        log.debug("register prepareJob, dto: {}", dto);

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return prepareJobHandler.registerPrepareJob(dto, user);
    }

    public List<PrepareJobModel> getAllPrepareJob(String token, int page, int size) {

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return prepareJobHandler.getAll(user, page, size);
    }

    public List<PrepareJobModel> getAllPrepareJobByOwnerId(String ownerId, int page, int size) {

        AppUser user = appointmentsUtility.getUserByUsername(ownerId);

        return prepareJobHandler.getAll(user, page, size);
    }

    ////////////////////////////////////
    // APPOINTMENTS
    ///////////////////////////////////

    public AppointmentModel registerAppointment(String token, RegisterAppointmentDTO dto) {

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        return appointmentHandler.register(dto, user.getUsername());
    }

    public List<AppointmentModel> getAppointment(String token, String date, String providerId) {

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        Role serviceClientRole = roleService.get("ROLE_SERVICE_CLIENT");
        Role serviceAdminRole = roleService.get("ROLE_SERVICE_ADMIN");
        List<AppointmentModel> returnValue;

        if (user.getRoles().contains(serviceClientRole) || user.getRoles().contains(serviceAdminRole)) {
            returnValue = jobOwnerHandler.getAppointments(user, providerId, date);
        } else {
            returnValue = appointmentHandler.getAppointments(user.getUsername(), date);
        }

        log.debug("find appointments: {}, for user: {}", returnValue, user);
        return returnValue;
    }

    public void registerJobReceiver(String token) {

        AppUser user = appointmentsUtility.extractUserFromToken(token);

        jobReceiverHandler.register(user.getUsername());
    }
}
