package ir.appointment.service.jobProvider.query;

import ir.appointment.controller.model.JobProviderModel;
import ir.appointment.domain.command.JobProvider;
import ir.appointment.domain.query.JobProviderView;
import ir.appointment.repository.command.JobProviderRepository;
import ir.appointment.repository.query.JobProviderViewRepository;
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
public class JobProviderQueryHandler {

    private final JobProviderRepository jobProviderRepository;
    private final JobProviderViewRepository jobProviderViewRepository;

    public JobProvider findByExternalId(String externalId) {

        log.debug("findByExternalId, externalId: {}", externalId);

        Optional<JobProvider> jobProviderOptional = jobProviderRepository.findByExternalId(externalId);

        if (jobProviderOptional.isEmpty()) {
            log.warn("jobProvider with externalId: {}, not exist", externalId);
            throw Problem.valueOf(Status.BAD_REQUEST, "This jobProvider not exist");
        }

        JobProvider foundJobProvider = jobProviderOptional.get();
        log.debug("jobProvider: {}, found by id: {}", foundJobProvider, externalId);

        return foundJobProvider;
    }

    public JobProviderView find(String id) {
        log.debug("findById: {}", id);

        Optional<JobProviderView> viewOptional = jobProviderViewRepository.findById(id);
        if (viewOptional.isEmpty()) {
            log.warn("No job provider view with id: {}", id);
            throw Problem.valueOf(Status.NOT_FOUND, "There is not any job provider with this id");
        }

        JobProviderView view = viewOptional.get();
        log.debug("found jobProviderView: {}", view);
        return view;
    }

    public JobProviderView findByJobProviderId(Long jobProviderId) {
        Optional<JobProviderView> jobProviderViewOptional = jobProviderViewRepository.findByJobProviderId(jobProviderId);
        if (jobProviderViewOptional.isEmpty()) {
            log.warn("No jobProviderView found for jobId: {}", jobProviderId);
            throw Problem.valueOf(Status.NOT_FOUND, "This job provider viw not exist");
        }

        JobProviderView foundJobProviderView = jobProviderViewOptional.get();
        log.debug("foundJobProviderView: {}", foundJobProviderView);

        return foundJobProviderView;
    }

    public List<JobProviderView> findByAccountId(String accountId, Pageable pageable) {

        log.debug("Try to find jobProviderView by accountId: {}", accountId);

        Optional<List<JobProviderView>> jobProviderViewsOptional = jobProviderViewRepository.findAllByAccountId(accountId, pageable);
        if (jobProviderViewsOptional.isEmpty()) {
            log.warn("No jobProviderView found with accountId: {}", accountId);
            throw Problem.valueOf(Status.NOT_FOUND, "This job provider viw not exist");
        }

        List<JobProviderView> foJobProviderViews = jobProviderViewsOptional.get();
        log.info("Found jobProviderViews: {}", foJobProviderViews);

        return foJobProviderViews;
    }
}
