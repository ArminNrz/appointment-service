package ir.appointment.domain.query;

import ir.appointment.domain.command.Job;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("job")
@Data
@NoArgsConstructor
public class JobView {

    @Id
    private String id;
    private Long jobId;
    private String name;
    private String jobOwner;
    private String accountId;

    public JobView(Job job, String accountId) {
        this.id = job.getExternalId();
        this.jobId = job.getId();
        this.name = job.getName();
        this.jobOwner = job.getJobOwner().getName();
        this.accountId = accountId;
    }
}
