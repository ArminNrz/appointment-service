package com.appoitment.userservice.exception.dto;

import lombok.Data;

@Data
public class AppointmentExceptionDTO {
    private String title;
    private String status;
    private String detail;
}
