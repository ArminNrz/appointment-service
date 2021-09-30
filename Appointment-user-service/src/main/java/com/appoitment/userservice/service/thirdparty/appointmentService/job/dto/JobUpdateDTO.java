package com.appoitment.userservice.service.thirdparty.appointmentService.job.dto;

import com.appoitment.userservice.domain.enumaration.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobUpdateDTO {
    private String id;
    private String name;
    private JobStatus status;
    private String accountId;
}
