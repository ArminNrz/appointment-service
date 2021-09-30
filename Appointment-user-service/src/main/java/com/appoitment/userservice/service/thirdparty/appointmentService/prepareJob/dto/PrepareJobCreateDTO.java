package com.appoitment.userservice.service.thirdparty.appointmentService.prepareJob.dto;

import com.appoitment.userservice.controller.appointment.dto.CreatePrepareJobDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PrepareJobCreateDTO {
    private String jobId;
    private String jobProviderId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double duration;
    private String creatorAccountId;
    private String serviceOwnerAccountId;

    public PrepareJobCreateDTO(CreatePrepareJobDTO dto, String creatorAccountId, String serviceOwnerAccountId) {
        this.jobId = dto.getJobId();
        this.jobProviderId = dto.getJobProviderId();
        this.date = dto.getDate();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
        this.duration = dto.getDuration();
        this.creatorAccountId = creatorAccountId;
        this.serviceOwnerAccountId = serviceOwnerAccountId;
    }
}
