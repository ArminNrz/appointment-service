package ir.appointment.controller.dto.job;

import ir.appointment.domain.enumaration.JobStatus;
import lombok.Data;

@Data
public class JobDTO {
    private String id;
    private String name;
    private JobStatus status;
    private String accountId;

    public JobDTO(JobCreateDTO dto) {
        this.name = dto.getName();
        this.status = JobStatus.ACTIVE;
        this.accountId = dto.getAccountId();
    }

    public JobDTO(JobUpdateDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.accountId = dto.getAccountId();
    }
}
