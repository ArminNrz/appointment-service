package com.appoitment.userservice.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PrepareJobModel {
    private String id;
    private String jobName;
    private String providerName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double duration;
    private String creator;
}
