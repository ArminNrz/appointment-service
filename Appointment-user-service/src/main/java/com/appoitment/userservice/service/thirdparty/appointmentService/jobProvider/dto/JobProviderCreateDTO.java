package com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto;

import com.appoitment.userservice.controller.appointment.dto.CreateJobProviderDTO;
import com.appoitment.userservice.domain.enumaration.JobProviderStatus;
import lombok.Data;

@Data
public class JobProviderCreateDTO {
    private String name;
    private JobProviderStatus status;
    private String accountId;

    public JobProviderCreateDTO(CreateJobProviderDTO dto, String accountId) {
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.accountId = accountId;
    }
}
