package ir.appointment.controller.dto.prepareJob;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PrepareJobDTO {
    private String id;
    private String jobId;
    private String jobProviderId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double duration;
    private String creatorAccountId;
    private String serviceOwnerAccountId;

    public PrepareJobDTO(PrepareJobCreateDTO createDTO) {
        this.jobId = createDTO.getJobId();
        this.jobProviderId = createDTO.getJobProviderId();
        this.date = createDTO.getDate();
        this.startTime = createDTO.getStartTime();
        this.endTime = createDTO.getEndTime();
        this.duration = createDTO.getDuration();
        this.creatorAccountId = createDTO.getCreatorAccountId();
        this.serviceOwnerAccountId = createDTO.getServiceOwnerAccountId();
    }

    public PrepareJobDTO(PrepareJobUpdateDTO updateDTO) {
        this.id = updateDTO.getId();
        this.jobId = updateDTO.getJobId();
        this.jobProviderId = updateDTO.getJobProviderId();
        this.date = updateDTO.getDate();
        this.startTime = updateDTO.getStartTime();
        this.endTime = updateDTO.getEndTime();
        this.duration = updateDTO.getDuration();
        this.creatorAccountId = updateDTO.getUpdaterAccountId();
    }
}
