package ir.appointment.service.jobOwner.query;

import ir.appointment.domain.command.JobOwner;
import ir.appointment.repository.command.JobOwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobOwnerQueryHandler {

    private final JobOwnerRepository jobOwnerRepository;

    public JobOwner findByAccountId(String accountId) {

        log.debug("findByAccountId, accountId: {}", accountId);

        Optional<JobOwner> jobOwnerOptional = jobOwnerRepository.findByAccountId(accountId);

        if (jobOwnerOptional.isEmpty()) {
            log.warn("There is not any jobOwner for accountId: {}", accountId);
            throw Problem.valueOf(Status.NOT_FOUND, "This jobOwner not exist");
        }

        JobOwner foundJobOwner = jobOwnerOptional.get();
        log.debug("foundJobOwner: {}", foundJobOwner);

        return foundJobOwner;
    }
}
