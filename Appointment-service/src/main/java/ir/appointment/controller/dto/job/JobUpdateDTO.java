package ir.appointment.controller.dto.job;

import ir.appointment.domain.enumaration.JobStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JobUpdateDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    private JobStatus status;
    @NotEmpty
    private String accountId;
}
