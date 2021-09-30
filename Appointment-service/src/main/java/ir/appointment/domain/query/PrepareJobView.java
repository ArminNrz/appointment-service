package ir.appointment.domain.query;

import ir.appointment.domain.command.PrepareJob;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Document("prepareJob")
@Data
@NoArgsConstructor
public class PrepareJobView {

    @Id
    private String id;
    private String jobId;
    private String jobName;
    private String jobProviderId;
    private String jobProviderName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double duration;
    private String creatorId;
    private String ownerId;

    public PrepareJobView(PrepareJob prepareJob) {
        this.id = prepareJob.getExternalId();
        this.jobId = prepareJob.getJob().getExternalId();
        this.jobName = prepareJob.getJob().getName();
        this.jobProviderId = prepareJob.getJobProvider().getExternalId();
        this.jobProviderName = prepareJob.getJobProvider().getName();
        this.date = prepareJob.getDate();
        this.startTime = prepareJob.getStartTime();
        this.endTime = prepareJob.getEndTime();
        this.duration = prepareJob.getDuration();
        this.creatorId = prepareJob.getCreatorAccountId();
        this.ownerId = prepareJob.getAccountId();
    }
}
