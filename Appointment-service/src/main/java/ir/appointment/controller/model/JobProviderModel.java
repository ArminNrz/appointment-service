package ir.appointment.controller.model;

import ir.appointment.domain.query.JobProviderView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JobProviderModel {
    private String id;
    private String name;
    private String jobOwner;

    public JobProviderModel(JobProviderView jobProviderView) {
        this.id = jobProviderView.getId();
        this.name = jobProviderView.getName();
        this.jobOwner = jobProviderView.getJobOwner();
    }
}
