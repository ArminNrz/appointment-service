package ir.appointment.controller.model;

import ir.appointment.domain.query.JobView;
import lombok.Data;

@Data
public class JobModel {
    private String id;
    private String name;
    private String jobOwner;

    public JobModel(JobView jobView) {
        this.id = jobView.getId();
        this.name = jobView.getName();
        this.jobOwner = jobView.getJobOwner();
    }
}
