package ir.appointment.controller.model;

import ir.appointment.domain.query.AppointmentView;
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
    private String receiverName;
    private String ownerName;

    public AppointmentModel(AppointmentView view) {
        this.id = view.getId();
        this.providerName = view.getJobProviderName();
        this.jobName = view.getJobName();
        this.date = view.getDate();
        this.startTime = view.getStartTime();
        this.endTime = view.getEndTime();
        this.ownerName = view.getOwnerName();
        this.receiverName = view.getUsername();
    }
}
