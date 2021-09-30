package ir.appointment.service.job;

import ir.appointment.controller.model.JobModel;
import ir.appointment.domain.command.Job;
import ir.appointment.controller.dto.job.JobDTO;
import ir.appointment.domain.query.JobView;

import java.util.List;

public interface JobService {

    JobView create(JobDTO dto);

    JobView update(JobDTO dto);

    Job findByExternalId(String id);

    JobView find(String id);

    List<JobModel> findByAccountId(String accountId, int page, int size);
}
