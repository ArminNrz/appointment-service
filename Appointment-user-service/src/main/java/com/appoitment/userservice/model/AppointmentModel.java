package com.appoitment.userservice.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentModel {
    private String id;
    private String providerName;
    private String jobName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String ownerName;
}
