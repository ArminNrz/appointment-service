package com.appoitment.userservice.service.thirdparty.appointmentService.jobOwner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobOwnerCreateDTO {
    private String name;
    private String accountId;
}
