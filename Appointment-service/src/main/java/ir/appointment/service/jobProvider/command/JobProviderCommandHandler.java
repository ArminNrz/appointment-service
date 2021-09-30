package ir.appointment.service.jobProvider.command;

import ir.appointment.domain.command.JobOwner;
import ir.appointment.domain.command.JobProvider;
import ir.appointment.domain.query.JobProviderView;
import ir.appointment.repository.command.JobProviderRepository;
import ir.appointment.repository.query.JobProviderViewRepository;
import ir.appointment.controller.dto.jobProvider.JobProviderDTO;
import ir.appointment.service.jobProvider.query.JobProviderQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobProviderCommandHandler {

    private final JobProviderRepository jobProviderRepository;
    private final JobProviderViewRepository jobProviderViewRepository;
    private final JobProviderQueryHandler jobProviderQueryHandler;

    public JobProviderView update(JobProviderDTO dto, JobOwner jobOwner) {

        log.debug("update, jobProviderDTO: {}, jobOwner: {}", dto, jobOwner);

        JobProviderView foundJobProviderView = jobProviderQueryHandler.find(dto.getId());
        JobProvider jobProvider = new JobProvider(foundJobProviderView.getJobProviderId(), dto.getName(), dto.getStatus(), jobOwner, Set.of(), dto.getId());
        JobProviderView updatedJobProviderView = new JobProviderView(jobProvider, dto.getAccountId());

        saveJobProvider(jobProvider);
        saveJobProviderView(updatedJobProviderView);

        return updatedJobProviderView;
    }

    public JobProviderView save(JobProvider jobProvider, String accountId) {

        log.debug("Save jobProvider: {}, accountId: {}", jobProvider, accountId);

        saveJobProvider(jobProvider);
        JobProviderView jobProviderView = new JobProviderView(jobProvider, accountId);
        saveJobProviderView(jobProviderView);

        return jobProviderView;
    }

    private void saveJobProvider(JobProvider jobProvider) {
        jobProviderRepository.save(jobProvider);
        log.info("Saved jobProvider: {}, in command DB", jobProvider);
    }

    private void saveJobProviderView(JobProviderView jobProviderView) {
        jobProviderViewRepository.save(jobProviderView);
        log.info("Saved jobProviderView: {}, in query DB", jobProviderView);
    }
}
