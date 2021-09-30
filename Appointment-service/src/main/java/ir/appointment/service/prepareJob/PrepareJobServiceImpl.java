package ir.appointment.service.prepareJob;

import ir.appointment.controller.dto.prepareJob.PrepareJobDTO;
import ir.appointment.controller.model.PrepareJobModel;
import ir.appointment.domain.command.Job;
import ir.appointment.domain.command.JobProvider;
import ir.appointment.domain.command.PrepareJob;
import ir.appointment.domain.query.PrepareJobView;
import ir.appointment.service.job.JobService;
import ir.appointment.service.jobProvider.JobProviderService;
import ir.appointment.service.prepareJob.command.PrepareJobCommandHandler;
import ir.appointment.service.prepareJob.query.PrepareJobQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrepareJobServiceImpl implements PrepareJobService {

    private final JobService jobService;
    private final JobProviderService jobProviderService;
    private final PrepareJobCommandHandler prepareJobCommandHandler;
    private final PrepareJobQueryHandler prepareJobQueryHandler;

    @Override
    public PrepareJobView create(PrepareJobDTO dto) {

        log.debug("create, prepareJobDTO: {}", dto);

        Job job = jobService.findByExternalId(dto.getJobId());
        JobProvider jobProvider = jobProviderService.findByExternalId(dto.getJobProviderId());

        PrepareJob prepareJob = new PrepareJob(job, jobProvider, dto);

        return prepareJobCommandHandler.save(prepareJob);
    }

    @Override
    public PrepareJobView update(PrepareJobDTO dto) {

        log.debug("update prepareJobDTO: {}", dto);

        PrepareJob formerPrepareJob = prepareJobQueryHandler.findByExternalId(dto.getId());

        formerPrepareJob.setDate(dto.getDate());
        formerPrepareJob.setStartTime(dto.getStartTime());
        formerPrepareJob.setEndTime(dto.getEndTime());
        formerPrepareJob.setDuration(dto.getDuration());
        formerPrepareJob.setCreatorAccountId(dto.getCreatorAccountId());

        Job newJob = jobService.findByExternalId(dto.getJobId());
        formerPrepareJob.setJob(newJob);

        JobProvider newJobProvider = jobProviderService.findByExternalId(dto.getJobProviderId());
        formerPrepareJob.setJobProvider(newJobProvider);

        return prepareJobCommandHandler.save(formerPrepareJob);
    }

    @Override
    public List<PrepareJobModel> getAll(String accountId, int page, int size) {

        log.debug("getAll, accountId: {}, page: {}, size: {}", accountId, page, size);

        Pageable pageable = PageRequest.of(page, size);

        List<PrepareJobView> foundPrepareJobViews = prepareJobQueryHandler.findAll(accountId, pageable);

        List<PrepareJobModel> prepareJobModels = new ArrayList<>();

        foundPrepareJobViews.forEach(prepareJobView -> {
            PrepareJobModel model = new PrepareJobModel(prepareJobView);
            prepareJobModels.add(model);
        });

        log.debug("getAll by accountId: {}, prepareJobModel: {}", accountId, prepareJobModels);
        return prepareJobModels;
    }

    @Override
    public PrepareJobView findViewById(String id) {

        log.debug("findViewById, id: {}", id);

        return prepareJobQueryHandler.findById(id);
    }

    @Override
    public PrepareJob findByExternalId(String externalId) {

        log.debug("findById, externalId: {}", externalId);

        return prepareJobQueryHandler.findByExternalId(externalId);
    }
}
