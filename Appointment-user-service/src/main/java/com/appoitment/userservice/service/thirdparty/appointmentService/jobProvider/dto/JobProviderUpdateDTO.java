package com.appoitment.userservice.service.thirdparty.appointmentService.jobProvider.dto;

import com.appoitment.userservice.controller.appointment.dto.UpdateJobProviderDTO;
import com.appoitment.userservice.domain.enumaration.JobProviderStatus;
import lombok.Data;

@Data
public class JobProviderUpdateDTO {
    private String id;
    private String name;
    private JobProviderStatus status;
    private String accountId;

    public JobProviderUpdateDTO(UpdateJobProviderDTO dto, String accountId) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.accountId = accountId;
    }
}
