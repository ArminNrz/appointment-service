package ir.appointment.controller.model;

import ir.appointment.domain.query.PrepareJobView;
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
    private String owner;

    public PrepareJobModel(PrepareJobView view) {
        this.id = view.getId();
        this.jobName = view.getJobName();
        this.providerName = view.getJobProviderName();
        this.date = view.getDate();
        this.startTime = view.getStartTime();
        this.endTime = view.getEndTime();
        this.duration = view.getDuration();
        this.creator = view.getCreatorId();
        this.owner = view.getOwnerId();
    }
}
