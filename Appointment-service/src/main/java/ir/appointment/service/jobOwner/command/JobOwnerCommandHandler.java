package ir.appointment.service.jobOwner.command;

import ir.appointment.domain.command.JobOwner;
import ir.appointment.repository.command.JobOwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobOwnerCommandHandler {

    private final JobOwnerRepository jobOwnerRepository;

    public JobOwner saveAndUpdate(JobOwner jobOwner) {

        log.debug("saveAndUpdate, jobOwner: {}", jobOwner);

        try {
            jobOwnerRepository.save(jobOwner);
        } catch (DataIntegrityViolationException e) {
            log.error("This job owner with accountId: {} saved before", jobOwner.getAccountId());
            throw Problem.valueOf(Status.BAD_REQUEST, "This job owner saved before");
        }

        log.info("Saved jobOwner: {}", jobOwner);

        return jobOwner;
    }
}
