package ir.appointment.service.jobProvider;

import ir.appointment.controller.dto.jobProvider.JobProviderDTO;
import ir.appointment.controller.model.JobProviderModel;
import ir.appointment.domain.command.JobOwner;
import ir.appointment.domain.command.JobProvider;
import ir.appointment.domain.query.JobProviderView;
import ir.appointment.service.jobOwner.JobOwnerService;
import ir.appointment.service.jobProvider.command.JobProviderCommandHandler;
import ir.appointment.service.jobProvider.query.JobProviderQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class JobProviderServiceImpl implements JobProviderService {

    private final JobOwnerService jobOwnerService;
    private final JobProviderCommandHandler jobProviderCommandHandler;
    private final JobProviderQueryHandler jobProviderQueryHandler;

    @Override
    public JobProviderView create(JobProviderDTO dto) {

        log.debug("create, JobProviderDTO: {}", dto);

        JobOwner jobOwner = jobOwnerService.find(dto.getAccountId());
        JobProvider jobProvider = new JobProvider(dto, jobOwner);

        return jobProviderCommandHandler.save(jobProvider, dto.getAccountId());
    }

    @Override
    public JobProviderView update(JobProviderDTO dto) {

        log.debug("update, jobProviderDTO: {}", dto);

        JobOwner jobOwner = jobOwnerService.find(dto.getAccountId());

        return jobProviderCommandHandler.update(dto, jobOwner);
    }

    @Override
    public JobProvider findByExternalId(String externalId) {

        log.debug("findByExternalId, externalId: {}", externalId);
        return jobProviderQueryHandler.findByExternalId(externalId);
    }

    @Override
    public JobProviderView find(String id) {

        log.debug("find id: {}", id);
        return jobProviderQueryHandler.find(id);
    }

    @Override
    public List<JobProviderModel> findByAccountId(String accountId, int page, int size) {

        log.debug("findByAccountId, accountId: {}, page: {}, size: {}", accountId, page, size);

        List<JobProviderModel> returnValue = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);
        List<JobProviderView> jobProviderViews = jobProviderQueryHandler.findByAccountId(accountId, pageable);

        jobProviderViews.forEach(jobProviderView -> {
            JobProviderModel model = new JobProviderModel(jobProviderView);
            returnValue.add(model);
        });

        log.debug("foundJobProviderModels: {}", returnValue);
        return returnValue;
    }
}
