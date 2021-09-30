package ir.appointment.service.job.query;

import ir.appointment.domain.command.Job;
import ir.appointment.domain.query.JobView;
import ir.appointment.repository.command.JobRepository;
import ir.appointment.repository.query.JobViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobQueryHandler {

    private final JobViewRepository jobViewRepository;
    private final JobRepository jobRepository;

    public JobView findById(String id) {

        log.debug("findById, id: {}", id);

        Optional<JobView> jobViewOptional = jobViewRepository.findById(id);
        if (jobViewOptional.isEmpty()) {
            log.warn("No jobView found for id: {}", id);
            throw Problem.valueOf(Status.NOT_FOUND, "This job viw not exist");
        }

        JobView foundJobView = jobViewOptional.get();
        log.debug("foundJobView: {}", foundJobView);

        return foundJobView;
    }

    public Job findByExternalId(String externalId) {

        log.debug("findByExternalId: {}", externalId);

        Optional<Job> jobOptional = jobRepository.findByExternalId(externalId);
        if (jobOptional.isEmpty()) {
            log.warn("No job with externalId: {}", externalId);
            throw Problem.valueOf(Status.BAD_REQUEST, "job is not exist");
        }

        Job foundJob = jobOptional.get();
        log.debug("foundJob: {}", foundJob);

        return foundJob;
    }

    public List<JobView> findByAccountId(String accountId, Pageable pageable) {

        log.debug("Try to find jobView by accountId: {}", accountId);

        Optional<List<JobView>> jobViewsOptional = jobViewRepository.findAllByAccountId(accountId, pageable);
        if (jobViewsOptional.isEmpty()) {
            log.warn("No jobView found with accountId: {}", accountId);
            throw Problem.valueOf(Status.NOT_FOUND, "This job viw not exist");
        }

        List<JobView> foJobViews = jobViewsOptional.get();
        log.info("Found jobViews: {}", foJobViews);

        return foJobViews;
    }
}
