package ir.appointment.service.prepareJob.query;

import ir.appointment.domain.command.PrepareJob;
import ir.appointment.domain.query.PrepareJobView;
import ir.appointment.repository.command.PrepareJobRepository;
import ir.appointment.repository.query.PrepareJobViewRepository;
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
public class PrepareJobQueryHandler {

    private final PrepareJobViewRepository prepareJobViewRepository;
    private final PrepareJobRepository prepareJobRepository;

    public List<PrepareJobView> findAll(String accountId, Pageable pageable) {

        log.debug("findAll, accountId: {}, pageable: {}", accountId, pageable);

        List<PrepareJobView> foundPrepareJobViews = prepareJobViewRepository.findAllByOwnerId(accountId, pageable);
        if (foundPrepareJobViews == null || foundPrepareJobViews.size() == 0) {
            log.warn("AccountId: {} has no PrepareJob", accountId);
            throw Problem.valueOf(Status.NOT_FOUND, "This account has no prepareJob");
        }

        log.debug("found prepareJobs: {}", foundPrepareJobViews);
        return foundPrepareJobViews;
    }

    public PrepareJob findByExternalId(String externalId) {

        Optional<PrepareJob> prepareJobOptional = prepareJobRepository.findByExternalId(externalId);
        if (prepareJobOptional.isEmpty()) {
            log.warn("prepareJob with id: {} not exist", externalId);
            throw Problem.valueOf(Status.BAD_REQUEST, "This prepareJob not exist");
        }

        PrepareJob prepareJob = prepareJobOptional.get();
        log.debug("foundPrepareJob with externalId: {}, is: {}", externalId, prepareJob);
        return prepareJob;
    }

    public PrepareJobView findById(String id) {

        Optional<PrepareJobView> prepareJobViewOptional = prepareJobViewRepository.findById(id);
        if (prepareJobViewOptional.isEmpty()) {
            log.warn("Prepare job with id: {}, is not exist", id);
            throw Problem.valueOf(Status.BAD_REQUEST, "This prepareJob not exist");
        }

        PrepareJobView prepareJobView = prepareJobViewOptional.get();
        log.debug("found PrepareJobView: {}", prepareJobView);
        return prepareJobView;
    }
}
