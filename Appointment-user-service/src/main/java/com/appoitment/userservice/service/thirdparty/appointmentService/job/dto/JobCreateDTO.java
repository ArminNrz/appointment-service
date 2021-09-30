package com.appoitment.userservice.service.thirdparty.appointmentService.job.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobCreateDTO {
    private String name;
    private String accountId;
}
