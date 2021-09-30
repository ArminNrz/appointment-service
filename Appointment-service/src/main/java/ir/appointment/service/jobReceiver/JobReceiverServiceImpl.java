package ir.appointment.service.jobReceiver;

import ir.appointment.controller.dto.jobReceiver.JobReceiverCreateDTO;
import ir.appointment.domain.command.JobReceiver;
import ir.appointment.repository.command.JobReceiverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobReceiverServiceImpl implements JobReceiverService {

    private final JobReceiverRepository jobReceiverRepository;

    @Override
    public void create(JobReceiverCreateDTO dto) {

        log.debug("create, dto: {}", dto);

        JobReceiver jobReceiver = new JobReceiver();
        jobReceiver.setUsername(dto.getUsername());

        jobReceiverRepository.save(jobReceiver);
        log.info("Saved jobReceiver: {}", jobReceiver);
    }

    @Override
    public JobReceiver find(String username) {

        log.debug("find, username: {}", username);

        Optional<JobReceiver> jobReceiverOptional = jobReceiverRepository.findByUsername(username);
        if (jobReceiverOptional.isEmpty()) {
            log.warn("jobReceiver with username: {}, not exist", username);
            throw Problem.valueOf(Status.BAD_REQUEST, "JobReceiver not exist");
        }

        JobReceiver returnValue = jobReceiverOptional.get();
        log.debug("found jobReceiver: {}, with username: {}", returnValue, username);
        return returnValue;
    }
}
