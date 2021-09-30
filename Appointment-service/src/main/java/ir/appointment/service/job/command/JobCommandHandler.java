package ir.appointment.service.job.command;

import ir.appointment.controller.dto.job.JobDTO;
import ir.appointment.domain.command.Job;
import ir.appointment.domain.command.JobOwner;
import ir.appointment.domain.query.JobView;
import ir.appointment.repository.command.JobRepository;
import ir.appointment.repository.query.JobViewRepository;
import ir.appointment.service.job.query.JobQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobCommandHandler {

    private final JobRepository jobRepository;
    private final JobViewRepository jobViewRepository;
    private final JobQueryHandler jobQueryHandler;

    public JobView update(JobOwner jobOwner, JobDTO dto) {

        log.debug("update, jobOwner: {}, jobDTO: {}", jobOwner, dto);

        JobView foundJobView = jobQueryHandler.findById(dto.getId());
        Job job = new Job(foundJobView.getJobId(), dto.getName(), dto.getStatus(), jobOwner, Set.of(), dto.getId());
        JobView updateJobView = new JobView(job, dto.getAccountId());

        saveJob(job);
        saveJobView(updateJobView);

        return updateJobView;
    }

    public JobView save(Job job, String accountId) {

        log.debug("Save job: {}", job);

        saveJob(job);
        JobView jobView = new JobView(job, accountId);
        saveJobView(jobView);

        return jobView;
    }

    private void saveJob(Job job) {

        log.debug("save, job: {}", job);
        jobRepository.save(job);
        log.info("Saved job: {}, in command DB", job);
    }

    private void saveJobView(JobView jobView) {

        log.debug("save, jobView: {}", jobView);
        jobViewRepository.save(jobView);
        log.info("Saved jobView: {}, in query DB", jobView);
    }
}
