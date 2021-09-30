package ir.appointment.domain.query;

import ir.appointment.domain.command.JobProvider;
import ir.appointment.domain.enumaration.JobProviderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("JobProvider")
@Data
@NoArgsConstructor
public class JobProviderView {

    @Id
    private String id;
    private Long jobProviderId;
    private String name;
    private JobProviderStatus status;
    private String jobOwner;
    private String accountId;

    public JobProviderView(JobProvider jobProvider, String accountId) {
        this.id = jobProvider.getExternalId();
        this.jobProviderId = jobProvider.getId();
        this.name = jobProvider.getName();
        this.status = jobProvider.getStatus();
        this.jobOwner = jobProvider.getJobOwner().getName();
        this.accountId = accountId;
    }
}
