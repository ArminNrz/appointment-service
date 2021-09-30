package ir.appointment.service.prepareJob.command;

import ir.appointment.controller.dto.prepareJob.PrepareJobDTO;
import ir.appointment.domain.command.Job;
import ir.appointment.domain.command.JobProvider;
import ir.appointment.domain.command.PrepareJob;
import ir.appointment.domain.query.JobProviderView;
import ir.appointment.domain.query.JobView;
import ir.appointment.domain.query.PrepareJobView;
import ir.appointment.repository.command.PrepareJobRepository;
import ir.appointment.repository.query.PrepareJobViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrepareJobCommandHandler {

    private final PrepareJobRepository prepareJobRepository;
    private final PrepareJobViewRepository prepareJobViewRepository;

    public PrepareJobView save(PrepareJob prepareJob) {

        log.debug("save prepareJob: {}", prepareJob);

        savePrepareJob(prepareJob);
        PrepareJobView view = new PrepareJobView(prepareJob);
        savePrepareJobView(view);

        return view;
    }

    private void savePrepareJob(PrepareJob prepareJob) {
        prepareJobRepository.save(prepareJob);
        log.info("Save prepareJob: {}, into command DB", prepareJob);
    }

    private void savePrepareJobView(PrepareJobView prepareJobView) {
        prepareJobViewRepository.save(prepareJobView);
        log.info("Save prepareJobView: {}, into query DB", prepareJobView);
    }
}
